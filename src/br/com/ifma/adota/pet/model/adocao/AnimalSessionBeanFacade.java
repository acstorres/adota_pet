package br.com.ifma.adota.pet.model.adocao;

import javax.ejb.Stateless;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacadeImpl;

@Stateless
public class AnimalSessionBeanFacade extends GenericsSessionBeanFacadeImpl<Animal, Long>
		implements AnimalSessionBeanFacadeLocal {
	
	public static final String NAME = "adota_pet_AnimalSessionBeanFacade";

}
