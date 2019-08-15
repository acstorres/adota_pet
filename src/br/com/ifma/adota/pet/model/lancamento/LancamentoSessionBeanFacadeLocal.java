package br.com.ifma.adota.pet.model.lancamento;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface LancamentoSessionBeanFacadeLocal {

	public String JNDI = "LancamentoAdocaoSessionBeanFacade!br.com.ifma.adota.pet.model.lancamento_adocao.LancamentoAdocaoSessionBeanFacadeLocal";

	public Lancamento include(Lancamento lancamentoAdocao);

	public Lancamento update(Lancamento lancamentoAdocao);
	
	public void remove(Integer lancamentoAdocaoId);

	public Collection<Lancamento> findAll();
}
