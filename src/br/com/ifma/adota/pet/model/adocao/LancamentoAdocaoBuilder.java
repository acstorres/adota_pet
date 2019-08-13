package br.com.ifma.adota.pet.model.adocao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import br.com.ifma.adota.pet.model.cliente.Cliente;

public class LancamentoAdocaoBuilder {
	
	private LancamentoAdocao lancamentoAdocao;
	
	public LancamentoAdocao constroi() {
		return this.constroi();
	}
	
	private LancamentoAdocaoBuilder(){
		this.lancamentoAdocao = new LancamentoAdocao();
	}
	
	private LancamentoAdocaoBuilder(Cliente cliente){
		this.lancamentoAdocao = new LancamentoAdocao(cliente);
	}
	
	public static final LancamentoAdocaoBuilder umNovoLancamentoAdocao() {
		LancamentoAdocaoBuilder lancamentoAdocaoBuilder = new LancamentoAdocaoBuilder();
		return lancamentoAdocaoBuilder;		
	}

	public static final LancamentoAdocaoBuilder umNovoLancamentoAdocaoCom(Cliente cliente) {
		LancamentoAdocaoBuilder lancamentoAdocaoBuilder = new LancamentoAdocaoBuilder(cliente);
		return lancamentoAdocaoBuilder;		
	}
	
	public LancamentoAdocaoBuilder comORespectivoCliente(Cliente cliente) {
		this.lancamentoAdocao.setCliente(cliente);
		return this;
	}
	
	public LancamentoAdocaoBuilder comADataLancamento(Date dataLancamento) {
		this.lancamentoAdocao.setDataLancamento(dataLancamento);
		return this;
	}
	
	public LancamentoAdocaoBuilder comADataLancamento(LocalDateTime dataLancamento) {
		this.lancamentoAdocao.setDataLancamento(
				Date.from(dataLancamento.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		return this;
	}

	public LancamentoAdocaoBuilder comADataLancamentoDate(LocalDate dataLancamento) {
		this.lancamentoAdocao
				.setDataLancamento(Date.from(dataLancamento.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		return this;
	}
}
