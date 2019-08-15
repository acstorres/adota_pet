package br.com.ifma.adota.pet.pages;

import java.util.Arrays;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.BindUtils;
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
import br.com.ifma.adota.pet.model.animal.AnimalBuilder;
import br.com.ifma.adota.pet.model.animal.AnimalSessionBeanFacadeLocal;
import br.com.ifma.adota.pet.model.cliente.Cliente;
import br.com.ifma.adota.pet.model.cliente.ClienteBuilder;
import br.com.ifma.adota.pet.model.lancamento.Lancamento;
import br.com.ifma.adota.pet.model.lancamento.LancamentoSessionBeanFacadeLocal;

public class AdocaoVM {

	private Cliente cliente;

	private Animal animal, animalSelecionado;

	private List<Animal> animais;

	private AnimalSessionBeanFacadeLocal animalSessionBeanFacadeLocal;

	private LancamentoSessionBeanFacadeLocal lancamentoSessionBeanFacadeLocal;

	public AdocaoVM() {
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
		System.out.println("chegou aqui");
		if (Sessions.getCurrent().getAttribute("usuario") == null) {
			Executions.sendRedirect("index.zul");
			return;
		}

		cliente = ClienteBuilder.umNovoCliente().constroi();
		cliente = (Cliente) Sessions.getCurrent().getAttribute("cliente");

		// animais = (List<Animal>)
		// animalSessionBeanFacadeLocal.recuperarTodosAnimaisDisponiveisAdocao(cliente);
		animais = (List<Animal>) animalSessionBeanFacadeLocal.recuperarTodosAnimaisDisponiveisAdocao();

		animalSelecionado = AnimalBuilder.umNovoAnimal().constroi();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange({ "animais" })
	public void adotarPet(@BindingParam("animal") Animal animal) {
		this.animalSelecionado = animal;
//		Messagebox.show("Desejas realmente adotar este animal?", "Pergunta", Messagebox.OK | Messagebox.CANCEL,
//				Messagebox.QUESTION, new EventListener() {
//					@Override
//					public void onEvent(Event event) throws Exception {
//						if (Messagebox.ON_CANCEL.equals(event.getName()))
//							return;				
//						}
//					}
//				});
//		
		if (efetuarAdocao()) {
			if (Sessions.getCurrent().getAttribute("usuario") == null)
				Executions.sendRedirect("index.zul");

			animais.remove(animalSelecionado);

			Clients.showNotification("Adocao realizada com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null, null, 3000,
					true);

			BindUtils.postNotifyChange(null, null, this, "animais");
		}
	}

	public boolean efetuarAdocao() {
		if (this.animalSelecionado == null) {
			Clients.showNotification("Nenhum animal selecionado.", Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000,
					true);
			return Boolean.FALSE;
		}

		try {
			Lancamento lancamento = lancamentoSessionBeanFacadeLocal.extrairLancamentoAdocaoValidos(cliente,
					Arrays.asList(this.animalSelecionado));

			if (lancamento != null)
				lancamentoSessionBeanFacadeLocal.processarLancamentosAdocao(lancamento);
		} catch (BeanFacadeException e) {
			Clients.showNotification("Erro ao adotar pet:" + e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null,
					null, 3000, true);
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
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

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public Animal getAnimalSelecionado() {
		return animalSelecionado;
	}

	public void setAnimalSelecionado(Animal animalSelecionado) {
		this.animalSelecionado = animalSelecionado;
	}
}
