package br.com.ifma.adota.pet.model.especie;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ClienteSessionBeanFacade
 */
@Stateless
public class EspecieSessionBeanFacade implements EspecieSessionBeanFacadeLocal {
	
	public static final String NAME = "adota_pet_EspecieSessionBeanFacade";
	
	@PersistenceContext(unitName = "adota_pet")
	public EntityManager manager;

	public Especie include(Especie especie) {

		manager.persist(especie);

		return especie;
	}

	public Especie update(Especie especie) {
		manager.merge(especie);
		return especie;
	}

	public void remove(Integer especieId) {
		manager.remove(manager.getReference(Especie.class, especieId));
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Especie> findAll() {
		Query q = manager.createQuery("select e from " + Especie.NAME + " e");
		return q.getResultList();
	}


}
