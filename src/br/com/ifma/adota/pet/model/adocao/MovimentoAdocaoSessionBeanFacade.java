package br.com.ifma.adota.pet.model.adocao;

import javax.ejb.Stateless;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacadeImpl;

@Stateless
public class MovimentoAdocaoSessionBeanFacade extends GenericsSessionBeanFacadeImpl<MovimentoAdocao, Integer>
		implements MovimentoAdocaoSessionBeanFacadeLocal {

	public static final String NAME = "adota_pet_MovimentoAdocaoSessionBeanFacade";
	
}
