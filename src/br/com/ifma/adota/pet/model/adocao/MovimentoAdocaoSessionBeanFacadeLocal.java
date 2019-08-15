package br.com.ifma.adota.pet.model.adocao;

import javax.ejb.Local;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacade;

@Local
public interface MovimentoAdocaoSessionBeanFacadeLocal extends GenericsSessionBeanFacade<MovimentoAdocao, Integer> {
	
	public String JNDI = "MovimentoAdocaoSessionBeanFacade!br.com.ifma.adota.pet.model.adocao.MovimentoAdocaoSessionBeanFacadeLocal";

}
