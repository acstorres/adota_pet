package br.com.ifma.adota.pet.pages;

import java.util.Collections;
import java.util.Comparator;
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
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;

import br.com.ifma.adota.pet.model.animal.Animal;
import br.com.ifma.adota.pet.model.animal.AnimalSessionBeanFacadeLocal;
import br.com.ifma.adota.pet.model.cliente.Cliente;
import br.com.ifma.adota.pet.model.cliente.ClienteBuilder;
import br.com.ifma.adota.pet.model.especie.Especie;
import br.com.ifma.adota.pet.model.especie.EspecieSessionBeanFacadeLocal;
import br.com.ifma.adota.pet.model.raca.Raca;
import br.com.ifma.adota.pet.model.raca.RacaSessionBeanFacadeLocal;
import br.com.ifma.adota.pet.util.RacaPredicates;

public class MuralPetsVM {

	private Cliente cliente;
	private Animal animal;
	private Especie especieSelecionada;
	private Raca racaSelecionada;

	private boolean femea, macho;
	private String idade;

	private List<Especie> especies;
	private List<Raca> racas, racasAll;
	private List<String> portes;
	private List<Animal> animais;

	private EspecieSessionBeanFacadeLocal especieSessionBeanFacadeLocal;
	private RacaSessionBeanFacadeLocal racaSessionBeanFacadeLocal;
	private AnimalSessionBeanFacadeLocal animalSessionBeanFacadeLocal;

	public MuralPetsVM() {

		try {
			InitialContext ic = new InitialContext();
			animalSessionBeanFacadeLocal = (AnimalSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + AnimalSessionBeanFacadeLocal.JNDI);
			especieSessionBeanFacadeLocal = (EspecieSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + EspecieSessionBeanFacadeLocal.JNDI);

			racaSessionBeanFacadeLocal = (RacaSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + RacaSessionBeanFacadeLocal.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	@Init
	public void init() {

		if (Sessions.getCurrent().getAttribute("usuario") == null) {
			Executions.sendRedirect("index.zul");

		} else {
			cliente = ClienteBuilder.umNovoCliente().constroi();
			cliente = (Cliente) Sessions.getCurrent().getAttribute("cliente");

			animais = (List<Animal>) animalSessionBeanFacadeLocal.findByClienteId(cliente.getClienteId());
			// animal =
			// AnimalBuilder.umNovoAnimal().definidoAutomaticamentePelaDataCadastro().constroi();

			// especieSelecionada = EspecieBuilder.umaNovaEspecie().constroi();
			// racaSelecionada = RacaBuilder.umaNovaRaca().constroi();

			// especies = (List<Especie>) especieSessionBeanFacadeLocal.findAll();
			// racasAll = (List<Raca>) racaSessionBeanFacadeLocal.findAll();
			// portes = new ArrayList<String>();
			// portes.add("Pequeno");
			// portes.add("Médio");
			// portes.add("Grande");

		}

	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange({ "especies", "racas" })
	public void onSelectEspecie(@BindingParam("especieSelecionada") Integer especieId) {

		racas = (List<Raca>) RacaPredicates.filtrarRaca(racasAll,
				RacaPredicates.racaEquals(especieSelecionada.getEspecieId()));

		Collections.sort(racas, new Comparator<Raca>() {
			public int compare(Raca r1, Raca r2) {
				return r1.getDescricao().compareTo(r2.getDescricao());
			}
		});

		BindUtils.postNotifyChange(null, null, this, "especies");
		BindUtils.postNotifyChange(null, null, this, "racas");
	}

	@Command
	@NotifyChange({ "femea", "macho" })
	public void femea() {

		macho = false;

		BindUtils.postNotifyChange(null, null, this, "femea");
		BindUtils.postNotifyChange(null, null, this, "macho");
	}

	@Command
	@NotifyChange({ "femea", "macho" })
	public void macho() {

		femea = false;

		BindUtils.postNotifyChange(null, null, this, "femea");
		BindUtils.postNotifyChange(null, null, this, "macho");
	}

	@Command
	public void salvaEdicaoPet() {
		if (racaSelecionada == null) {
			Clients.showNotification("Raça não pode ser nula!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000,
					true);
			return;
		}

		if (animal.getPorte() == null || animal.getPorte().isEmpty()) {
			Clients.showNotification("Porte não pode ser nulo!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000,
					true);
			return;
		}
		if (animal.getNome() == null || animal.getNome().isEmpty()) {
			Clients.showNotification("Nome não pode ser nulo!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000,
					true);
			return;
		}
		if (idade == null || idade.isEmpty()) {
			Clients.showNotification("Idade não pode ser nula!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000,
					true);
			return;
		}
		if (animal.getCor() == null || animal.getCor().isEmpty()) {
			Clients.showNotification("Cor não pode ser nula!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000,
					true);
			return;
		}

		if (femea == false && macho == false) {
			Clients.showNotification("Sexo não pode ser nulo!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000,
					true);
			return;
		}

		animal.setRaca(racaSelecionada);
		animal.setDoador(cliente);
		animal.setIdade(Integer.parseInt(idade));

		if (femea) {
			animal.setSexo("F");
		} else {
			animal.setSexo("M");
		}

		animal.setAtivo(true);

		if (animalSessionBeanFacadeLocal.update(animal) != null) {
			Clients.showNotification("Edicao de Pet salva com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null, null,
					3000, true);

		} else {

			Clients.showNotification("Erro ao salvar edicao Pet!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000,
					true);
			return;
		}

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

	public List<Especie> getEspecies() {
		return especies;
	}

	public void setEspecies(List<Especie> especies) {
		this.especies = especies;
	}

	public List<Raca> getRacas() {
		return racas;
	}

	public void setRacas(List<Raca> racas) {
		this.racas = racas;
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

	public List<Raca> getRacasAll() {
		return racasAll;
	}

	public void setRacasAll(List<Raca> racasAll) {
		this.racasAll = racasAll;
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

	public List<String> getPortes() {
		return portes;
	}

	public void setPortes(List<String> portes) {
		this.portes = portes;
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

}
