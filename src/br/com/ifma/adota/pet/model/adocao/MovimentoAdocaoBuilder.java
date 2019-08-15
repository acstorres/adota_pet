package br.com.ifma.adota.pet.model.adocao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class MovimentoAdocaoBuilder {

	private MovimentoAdocao movimentoAdocao;

	public MovimentoAdocao constroi() {
		return this.movimentoAdocao;
	}

	private MovimentoAdocaoBuilder() {
		this.movimentoAdocao = new MovimentoAdocao();
	}

	private MovimentoAdocaoBuilder(Integer movimentoAdocaoId) {
		this.movimentoAdocao = new MovimentoAdocao(movimentoAdocaoId);
	}

	private MovimentoAdocaoBuilder(LancamentoAdocao lancamentoAdocao, Animal animal) {
		this.movimentoAdocao = new MovimentoAdocao(lancamentoAdocao, animal);
	}

	public static final MovimentoAdocaoBuilder umNovoMovimentoAdocao() {
		MovimentoAdocaoBuilder movimentoAdocaoBuilder = new MovimentoAdocaoBuilder();
		return movimentoAdocaoBuilder;
	}
	
	public static final MovimentoAdocaoBuilder umNovoMovimentoAdocao(Integer movimentoAdocaoId) {
		MovimentoAdocaoBuilder movimentoAdocaoBuilder = new MovimentoAdocaoBuilder(movimentoAdocaoId);
		return movimentoAdocaoBuilder;
	}

	public static final MovimentoAdocaoBuilder umNovoMovimentoAdocao(LancamentoAdocao lancamentoAdocao, Animal animal) {
		MovimentoAdocaoBuilder movimentoAdocaoBuilder = new MovimentoAdocaoBuilder(lancamentoAdocao, animal);
		return movimentoAdocaoBuilder;
	}

	public MovimentoAdocaoBuilder comORespectivoLancamentoAdocao(LancamentoAdocao lancamentoAdocao) {
		this.movimentoAdocao.setLancamentoAdocao(lancamentoAdocao);
		return this;
	}

	public MovimentoAdocaoBuilder contendoOSeguinteAnimal(Animal animal) {
		this.movimentoAdocao.setAnimal(animal);
		return this;
	}
	
	public MovimentoAdocaoBuilder definidoAutomaticamentePelaDataLancamento() {
		this.movimentoAdocao.setDataLancamento(
				Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return this;
	}

	public MovimentoAdocaoBuilder comADataLancamento(Date dataLancamento) {
		this.movimentoAdocao.setDataLancamento(dataLancamento);
		return this;
	}

	public MovimentoAdocaoBuilder comADataLancamento(LocalDateTime dataLancamento) {
		this.movimentoAdocao.setDataLancamento(
				Date.from(dataLancamento.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return this;
	}

	public MovimentoAdocaoBuilder comADataLancamentoDate(LocalDate dataLancamento) {
		this.movimentoAdocao
				.setDataLancamento(Date.from(dataLancamento.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return this;
	}
}
