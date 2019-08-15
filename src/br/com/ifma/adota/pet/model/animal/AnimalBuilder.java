package br.com.ifma.adota.pet.model.animal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AnimalBuilder {

	private Animal animal;

	public Animal constroi() {
		return this.animal;
	}

	public AnimalBuilder() {
		this.animal = new Animal();
	}

	public static final AnimalBuilder umNovoAnimal() {
		AnimalBuilder animalBuilder = new AnimalBuilder();
		return animalBuilder;
	}

	public AnimalBuilder comADataCadastroDate(LocalDate dataLancamento) {
		this.animal.setDataCadastro(Date.from(dataLancamento.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		return this;
	}

	public AnimalBuilder definidoAutomaticamentePelaDataCadastro() {
		this.animal.setDataCadastro(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return this;
	}

	
}
