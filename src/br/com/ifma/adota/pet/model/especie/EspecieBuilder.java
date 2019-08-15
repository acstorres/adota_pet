package br.com.ifma.adota.pet.model.especie;

public class EspecieBuilder {
	
	private Especie especie;

	public Especie constroi() {
		return this.especie;
	}

	public EspecieBuilder() {
		this.especie = new Especie();
	}

	public static final EspecieBuilder umaNovaEspecie() {
		EspecieBuilder especieBuilder = new EspecieBuilder();
		return especieBuilder;
	}

}
