package br.com.ifma.adota.pet.model.hashtag_animal;

import javax.ejb.Stateless;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacadeImpl;

@Stateless
public class HashTagAnimalSessionBeanFacade extends GenericsSessionBeanFacadeImpl<HashTagAnimal, Integer>
		implements HashTagAnimalSessionBeanFacadeLocal {

	public static final String NAME = "adota_pet_HashTagAnimalSessionBeanFacade";
}
