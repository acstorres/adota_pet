package br.com.ifma.adota.pet.negocio;

import java.util.List;

import br.com.ifma.adota.pet.exception.NegocioException;
import br.com.ifma.adota.pet.model.adocao.LancamentoAdocao;
import br.com.ifma.adota.pet.model.adocao.MovimentoAdocao;
import br.com.ifma.adota.pet.model.cliente.Cliente;

public interface LancamentoAdocaoNegocio {
	
	public LancamentoAdocao gerarLancamentoAdocao(Cliente cliente)throws NegocioException;
	
	public void processarLancamentosAdocao(List<LancamentoAdocao> lancamentosAdocao) throws NegocioException;

	public void processarLancamentosAdocao(LancamentoAdocao lancamentoAdocao) throws NegocioException;
	
	public MovimentoAdocao gerarMovimentacaoAdocao(LancamentoAdocao lancamentoAdocao) throws NegocioException;
	
	public List<MovimentoAdocao> gerarMovimentacaoAdocaoPor() throws NegocioException;
	
}
