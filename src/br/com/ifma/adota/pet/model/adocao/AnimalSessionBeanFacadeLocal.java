package br.com.ifma.adota.pet.model.adocao;

import javax.ejb.Local;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacade;

@Local
public interface AnimalSessionBeanFacadeLocal extends GenericsSessionBeanFacade<Animal, Long> {
	
	public String JNDI = "AnimalSessionBeanFacade!br.com.ifma.adota.pet.model.adocao.AnimalSessionBeanFacadeLocal";

}
