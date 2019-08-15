package br.com.ifma.adota.pet.model.endereco;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.ifma.adota.pet.model.cliente.Cliente;

@Stateless
public class EnderecoSessionBeanFacade implements EnderecoSessionBeanFacadeLocal{
		
	
	@PersistenceContext(unitName = "adota_pet")
	public EntityManager manager;

	public Endereco include(Endereco endereco) {

		manager.persist(endereco);

		return findByPrimaryKey(endereco.getEnderecoId());
	}

	public Endereco update(Endereco endereco) {
		manager.merge(endereco);
		return endereco;
	}

	public void remove(Integer enderecoId) {
		manager.remove(manager.getReference(Endereco.class, enderecoId));
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Endereco> findAll() {
		Query q = manager.createQuery("select e from " + Endereco.NAME + " e");
		return q.getResultList();
	}
	
	public Endereco findByPrimaryKey(Integer enderecoId) {
		Query q = manager.createQuery("select e from " + Endereco.NAME + " e where e.enderecoId =: enderecoId")
				.setParameter("enderecoId", enderecoId);

		return (Endereco) q.getSingleResult();
	}



}
