package br.com.ifma.adota.pet.model.hashtag_cliente;

import javax.ejb.Stateless;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacadeImpl;

@Stateless
public class HashTagClienteSessionBeanFacade extends GenericsSessionBeanFacadeImpl<HashTagCliente, Integer>
		implements HashTagClienteSessionBeanFacadeLocal {

	public static final String NAME = "adota_pet_HashTagClienteSessionBeanFacade";
}
