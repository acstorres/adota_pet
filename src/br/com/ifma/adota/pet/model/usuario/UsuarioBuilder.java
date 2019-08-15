package br.com.ifma.adota.pet.model.usuario;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import br.com.ifma.adota.pet.model.endereco.EnderecoBuilder;

public class UsuarioBuilder {
	private Usuario usuario;

	public Usuario constroi() {
		return this.usuario;
	}

	public UsuarioBuilder() {
		this.usuario = new Usuario();
	}

	public static final UsuarioBuilder umNovoUsuario() {
		UsuarioBuilder usuarioBuilder = new UsuarioBuilder();
		return usuarioBuilder;
	}

	public UsuarioBuilder comADataLancamentoDate(LocalDate dataLancamento) {
		this.usuario.setDataLancamento(Date.from(dataLancamento.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		return this;
	}

	public UsuarioBuilder definidoAutomaticamentePelaDataLancamento() {
		this.usuario.setDataLancamento(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return this;
	}

}
