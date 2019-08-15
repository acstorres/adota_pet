package br.com.ifma.adota.pet.model.endereco;

public class EnderecoBuilder {
	private Endereco endereco;

	public Endereco controi() {
		return this.endereco;
	}

	public EnderecoBuilder() {
		this.endereco = new Endereco();
	}

	public static final EnderecoBuilder umNovoEndereco() {
		EnderecoBuilder enderecoBuilder = new EnderecoBuilder();
		return enderecoBuilder;
	}

}
