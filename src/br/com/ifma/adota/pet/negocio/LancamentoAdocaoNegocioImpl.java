package br.com.ifma.adota.pet.negocio;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.ifma.adota.pet.exception.NegocioException;
import br.com.ifma.adota.pet.model.adocao.LancamentoAdocao;
import br.com.ifma.adota.pet.model.adocao.MovimentoAdocao;
import br.com.ifma.adota.pet.model.cliente.Cliente;

public class LancamentoAdocaoNegocioImpl implements LancamentoAdocaoNegocio{
	
	private EntityManager em;

	public LancamentoAdocaoNegocioImpl(EntityManager em) {
		this.em = em;
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public LancamentoAdocao gerarLancamentoAdocao(Cliente cliente) throws NegocioException {		
		return null;
	}

	@Override
	public void processarLancamentosAdocao(List<LancamentoAdocao> lancamentosAdocao) throws NegocioException {

	}

	@Override
	public void processarLancamentosAdocao(LancamentoAdocao lancamentoAdocao) throws NegocioException {
		
	}

	@Override
	public MovimentoAdocao gerarMovimentacaoAdocao(LancamentoAdocao lancamentoAdocao) throws NegocioException {
		return null;
	}

	@Override
	public List<MovimentoAdocao> gerarMovimentacaoAdocaoPor() throws NegocioException {
		return null;
	}
}
