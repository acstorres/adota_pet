package br.com.ifma.adota.pet.model.lancamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import br.com.ifma.adota.pet.model.cliente.Cliente;

public class LancamentoBuilder {

	private Lancamento lancamento;

	public Lancamento constroi() {
		return this.lancamento;
	}

	private LancamentoBuilder() {
		this.lancamento = new Lancamento();
	}

	private LancamentoBuilder(Cliente cliente) {
		this.lancamento = new Lancamento(cliente);
	}

	public static final LancamentoBuilder umNovoLancamento() {
		LancamentoBuilder LancamentoBuilder = new LancamentoBuilder();
		return LancamentoBuilder;
	}

	public static final LancamentoBuilder umNovoLancamentoCom(Cliente cliente) {
		LancamentoBuilder LancamentoBuilder = new LancamentoBuilder(cliente);
		return LancamentoBuilder;
	}

	public LancamentoBuilder comORespectivoCliente(Cliente cliente) {
		this.lancamento.setCliente(cliente);
		return this;
	}

	public LancamentoBuilder definidoAutomaticamentePelaDataLancamento() {
		this.lancamento.setDataLancamento(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return this;
	}

	public LancamentoBuilder comADataLancamento(Date dataLancamento) {
		this.lancamento.setDataLancamento(dataLancamento);
		return this;
	}

	public LancamentoBuilder comADataLancamento(LocalDateTime dataLancamento) {
		this.lancamento.setDataLancamento(
				Date.from(dataLancamento.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return this;
	}

	public LancamentoBuilder comADataLancamentoDate(LocalDate dataLancamento) {
		this.lancamento.setDataLancamento(Date.from(dataLancamento.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return this;
	}
}
