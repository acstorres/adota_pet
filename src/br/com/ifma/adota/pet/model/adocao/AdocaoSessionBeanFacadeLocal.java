package br.com.ifma.adota.pet.model.adocao;

import javax.ejb.Local;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacade;

@Local
public interface AdocaoSessionBeanFacadeLocal extends GenericsSessionBeanFacade<Adocao, Integer> {
	
	public String JNDI = "AdocaoSessionBeanFacade!br.com.ifma.adota.pet.model.adocao.AdocaoSessionBeanFacadeLocal";
	
}
