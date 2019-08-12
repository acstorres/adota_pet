package br.com.ifma.adota.pet.model.adocao;

import javax.ejb.Local;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacade;

@Local
public interface LancamentoAdocaoSessionBeanFacadeLocal extends GenericsSessionBeanFacade<LancamentoAdocao, Long> {
	
	public String JNDI = "LancamentoAdocaoSessionBeanFacade!br.com.ifma.adota.pet.model.adocao.LancamentoAdocaoSessionBeanFacadeLocal";

}
