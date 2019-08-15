package br.com.ifma.adota.pet.model.raca;

public class RacaBuilder {

	private Raca raca;

	public Raca constroi() {
		return this.raca;
	}

	public RacaBuilder() {
		this.raca = new Raca();
	}

	public static final RacaBuilder umaNovaRaca() {
		RacaBuilder racaBuilder = new RacaBuilder();
		return racaBuilder;
	}
}
