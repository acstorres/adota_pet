package br.com.ifma.adota.pet.model.raca;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.ifma.adota.pet.model.animal.Animal;

@Stateless
public class RacaSessionBeanFacade implements RacaSessionBeanFacadeLocal {
	
	@PersistenceContext(unitName = "adota_pet")
	public EntityManager manager;

	public Raca include(Raca raca) {

		manager.persist(raca);

		return raca;
	}

	public Raca update(Raca raca) {
		manager.merge(raca);
		return raca;
	}

	public void remove(Integer racaId) {
		manager.remove(manager.getReference(Raca.class, racaId));
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Raca> findAll() {
		Query q = manager.createQuery("select r from " + Raca.NAME + " r");
		return q.getResultList();
	}



}
