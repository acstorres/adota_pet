package br.com.ifma.adota.pet.model.hashtag_cliente;

import javax.ejb.Local;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacade;

@Local
public interface HashTagClienteSessionBeanFacadeLocal extends GenericsSessionBeanFacade<HashTagCliente, Integer> {

	public String JNDI = "HashTagClienteSessionBeanFacade!br.com.ifma.adota.pet.model.hashtag_cliente.HashTagClienteSessionBeanFacadeLocal";

}
