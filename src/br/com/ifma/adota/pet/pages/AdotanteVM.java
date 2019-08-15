package br.com.ifma.adota.pet.pages;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import br.com.ifma.adota.pet.exception.BeanFacadeException;
import br.com.ifma.adota.pet.model.animal.Animal;
import br.com.ifma.adota.pet.model.animal.AnimalSessionBeanFacadeLocal;
import br.com.ifma.adota.pet.model.cliente.Cliente;
import br.com.ifma.adota.pet.model.cliente.ClienteBuilder;
import br.com.ifma.adota.pet.model.especie.Especie;
import br.com.ifma.adota.pet.model.lancamento.Lancamento;
import br.com.ifma.adota.pet.model.lancamento.LancamentoSessionBeanFacadeLocal;
import br.com.ifma.adota.pet.model.raca.Raca;

public class AdotanteVM {

	private String conteudo = "default.zul";

	private Cliente cliente;

	private Animal animal;

	private Especie especieSelecionada;

	private Raca racaSelecionada;

	private boolean femea, macho;

	private String idade;

	private List<Animal> animais;

	private AnimalSessionBeanFacadeLocal animalSessionBeanFacadeLocal;

	private LancamentoSessionBeanFacadeLocal lancamentoSessionBeanFacadeLocal;

	public AdotanteVM() {
		try {
			InitialContext ic = new InitialContext();
			animalSessionBeanFacadeLocal = (AnimalSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + AnimalSessionBeanFacadeLocal.JNDI);

			lancamentoSessionBeanFacadeLocal = (LancamentoSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + LancamentoSessionBeanFacadeLocal.JNDI);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Init
	public void init() {
		if (Sessions.getCurrent().getAttribute("usuario") == null) {
			Executions.sendRedirect("index.zul");
			return;
		}

		cliente = ClienteBuilder.umNovoCliente().constroi();
		cliente = (Cliente) Sessions.getCurrent().getAttribute("cliente");

		animais = (List<Animal>) animalSessionBeanFacadeLocal.recuperarTodosAnimaisDisponiveisAdocao(cliente);
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange(".")
	public void irPara(@BindingParam("pagina") String pagina) {

		switch (pagina) {

		case "adotar":
			conteudo = "adocao.zul";
			break;
		case "inicio":

			conteudo = "default.zul";
		default:
			conteudo = "default.zul";
			break;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void adotarPet() {

		Messagebox.show("Desejas realmente adotar este animal?", "Pergunta", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						if (Messagebox.ON_CANCEL.equals(event.getName()))
							return;

						if (efetuarAdocao()) {
							Clients.showNotification("Adoção realizada com sucesso!", Clients.NOTIFICATION_TYPE_INFO,
									null, null, 3000, true);

							if (Sessions.getCurrent().getAttribute("usuario") == null) {
								Executions.sendRedirect("index.zul");
							}
							// Executions.getCurrent().sendRedirect("adocao.zul");
						}
					}
				});
	}

	private Boolean efetuarAdocao() {
		this.animal = animalSessionBeanFacadeLocal.recuperarPorId(Integer.valueOf(1));
		
		if (this.animal == null) {
			Clients.showNotification("Nenhum animal selecionado.", Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000,
					true);
			return Boolean.FALSE;
		}

		List<Animal> animaisLancamentoAdocao = new ArrayList<Animal>();
		animaisLancamentoAdocao.add(animal);

		try {
			Lancamento lancamento = lancamentoSessionBeanFacadeLocal.extrairLancamentoAdocaoValidos(this.cliente,
					animaisLancamentoAdocao);

			if (lancamento != null)
				lancamentoSessionBeanFacadeLocal.processarLancamentosAdocao(lancamento);
			
		} catch (BeanFacadeException e) {
			Clients.showNotification("Erro ao salvar adoção: " + e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING,
					null, null, 3000, true);
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	@Command
	public void sair() {
		Sessions.getCurrent().invalidate();
		Executions.sendRedirect("index.zul");
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	
	public Especie getEspecieSelecionada() {
		return especieSelecionada;
	}

	public void setEspecieSelecionada(Especie especieSelecionada) {
		this.especieSelecionada = especieSelecionada;
	}

	public Raca getRacaSelecionada() {
		return racaSelecionada;
	}

	public void setRacaSelecionada(Raca racaSelecionada) {
		this.racaSelecionada = racaSelecionada;
	}

	public boolean isFemea() {
		return femea;
	}

	public void setFemea(boolean femea) {
		this.femea = femea;
	}

	public boolean isMacho() {
		return macho;
	}

	public void setMacho(boolean macho) {
		this.macho = macho;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
}
