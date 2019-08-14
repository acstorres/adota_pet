package br.com.ifma.adota.pet.model.hashtag_animal;

import javax.ejb.Local;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacade;

@Local
public interface HashTagAnimalSessionBeanFacadeLocal extends GenericsSessionBeanFacade<HashTagAnimal, Integer> {

	public String JNDI = "HashTagAnimalSessionBeanFacade!br.com.ifma.adota.pet.model.hashtag_animal.HashTagAnimalSessionBeanFacadeLocal";

}
