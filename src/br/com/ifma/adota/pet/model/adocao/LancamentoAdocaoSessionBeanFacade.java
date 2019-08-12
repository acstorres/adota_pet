package br.com.ifma.adota.pet.model.adocao;

import javax.ejb.Stateless;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacadeImpl;

@Stateless
public class LancamentoAdocaoSessionBeanFacade extends GenericsSessionBeanFacadeImpl<LancamentoAdocao, Long>
		implements LancamentoAdocaoSessionBeanFacadeLocal {
	
	public static final String NAME = "adota_pet_LancamentoAdocaoSessionBeanFacade";

}
