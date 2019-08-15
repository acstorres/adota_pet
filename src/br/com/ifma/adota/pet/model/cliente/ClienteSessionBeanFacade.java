package br.com.ifma.adota.pet.model.cliente;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ClienteSessionBeanFacade
 */
@Stateless
public class ClienteSessionBeanFacade implements ClienteSessionBeanFacadeLocal {

	@PersistenceContext(unitName = "adota_pet")
	public EntityManager manager;

	public Cliente include(Cliente cliente) {

		manager.persist(cliente);

		return findByPrimaryKey(cliente.getClienteId());
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

	public Cliente findByUsuarioId(Integer usuarioId) {
		Query q = manager.createQuery("select c from " + Cliente.NAME + " c where c.usuario.usuarioId =: usuarioId")
				.setParameter("usuarioId", usuarioId);

		return (Cliente) q.getSingleResult();
	}

	public Cliente findByPrimaryKey(Integer clienteId) {
		Query q = manager.createQuery("select c from " + Cliente.NAME + " c where c.clienteId =: clienteId")
				.setParameter("clienteId", clienteId);

		return (Cliente) q.getSingleResult();
	}

}
