package br.com.ifma.adota.pet.model.adocao;

import javax.ejb.Stateless;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacadeImpl;

@Stateless
public class AdocaoSessionBeanFacade extends GenericsSessionBeanFacadeImpl<Adocao, Integer>
		implements AdocaoSessionBeanFacadeLocal {

	public static final String NAME = "adota_pet_AdocaoSessionBeanFacade";

}
