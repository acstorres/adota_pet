package br.com.ifma.adota.pet.model.cliente;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ClienteSessionBeanFacade
 */
public class ClienteSessionBeanFacade implements ClienteSessionBeanFacadeLocal {
	
	public static final String NAME = "adota_pet_ClienteSessionBeanFacade";
	
	@PersistenceContext(unitName = "adota_pet")
	public EntityManager manager;

	public Cliente include(Cliente cliente) {

		manager.persist(cliente);

		return cliente;
	}

	public Cliente update(Cliente cliente) {
		manager.merge(cliente);
		return cliente;
	}

	public void remove(Integer clienteId) {
		manager.remove(manager.getReference(Cliente.class, clienteId));
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Cliente> findAll() {
		Query q = manager.createQuery("select c from " + Cliente.NAME + " c");
		return q.getResultList();
	}


}
