package br.com.ifma.adota.pet.model.cliente;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import br.com.ifma.adota.pet.model.endereco.Endereco;
import br.com.ifma.adota.pet.model.usuario.Usuario;

public class ClienteBuilder {

	private Cliente cliente;

	public Cliente constroi() {
		return this.cliente;
	}

	public ClienteBuilder() {
		this.cliente = new Cliente();
	}

	private ClienteBuilder(Usuario usuario, Endereco endereco) {
		this.cliente = new Cliente(usuario, endereco);
	}

	public static final ClienteBuilder umNovoCliente() {
		ClienteBuilder clienteBuilder = new ClienteBuilder();
		return clienteBuilder;
	}

	public static final ClienteBuilder umNovoClienteCom(Usuario usuario, Endereco endereco) {
		ClienteBuilder clienteBuilder = new ClienteBuilder(usuario, endereco);
		return clienteBuilder;
	}

	public ClienteBuilder comADataLancamentoDate(LocalDate dataLancamento) {
		this.cliente.setDataLancamento(Date.from(dataLancamento.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		return this;
	}
	
	public ClienteBuilder definidoAutomaticamentePelaDataLancamento() {
	    this.cliente
	        .setDataLancamento(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

	    return this;
	  }

}
