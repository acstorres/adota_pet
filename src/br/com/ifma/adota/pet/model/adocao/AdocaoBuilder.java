package br.com.ifma.adota.pet.model.adocao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import br.com.ifma.adota.pet.model.animal.Animal;
import br.com.ifma.adota.pet.model.lancamento.Lancamento;

public class AdocaoBuilder {

	private Adocao adocao;

	public Adocao constroi() {
		return this.adocao;
	}

	private AdocaoBuilder() {
		this.adocao = new Adocao();
	}

	private AdocaoBuilder(Integer movimentoAdocaoId) {
		this.adocao = new Adocao(movimentoAdocaoId);
	}

	private AdocaoBuilder(Lancamento lancamento, Animal animal) {
		this.adocao = new Adocao(lancamento, animal);
	}

	public static final AdocaoBuilder umNovoMovimentoAdocao() {
		AdocaoBuilder movimentoAdocaoBuilder = new AdocaoBuilder();
		return movimentoAdocaoBuilder;
	}

	public static final AdocaoBuilder umNovoMovimentoAdocao(Integer movimentoAdocaoId) {
		AdocaoBuilder movimentoAdocaoBuilder = new AdocaoBuilder(movimentoAdocaoId);
		return movimentoAdocaoBuilder;
	}

	public static final AdocaoBuilder umNovoMovimentoAdocao(Lancamento lancamento, Animal animal) {
		AdocaoBuilder movimentoAdocaoBuilder = new AdocaoBuilder(lancamento, animal);
		return movimentoAdocaoBuilder;
	}

	public AdocaoBuilder comORespectivoLancamentoAdocao(Lancamento lancamento) {
		this.adocao.setLancamento(lancamento);
		return this;
	}

	public AdocaoBuilder contendoOSeguinteAnimal(Animal animal) {
		this.adocao.setAnimal(animal);
		return this;
	}

	public AdocaoBuilder definidoAutomaticamentePelaDataLancamento() {
		this.adocao.setDataLancamento(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return this;
	}

	public AdocaoBuilder comADataLancamento(Date dataLancamento) {
		this.adocao.setDataLancamento(dataLancamento);
		return this;
	}

	public AdocaoBuilder comADataLancamento(LocalDateTime dataLancamento) {
		this.adocao.setDataLancamento(
				Date.from(dataLancamento.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return this;
	}

	public AdocaoBuilder comADataLancamentoDate(LocalDate dataLancamento) {
		this.adocao.setDataLancamento(Date.from(dataLancamento.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return this;
	}
}
