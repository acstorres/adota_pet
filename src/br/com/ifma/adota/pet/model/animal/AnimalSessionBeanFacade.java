package br.com.ifma.adota.pet.model.animal;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AnimalSessionBeanFacade implements AnimalSessionBeanFacadeLocal {

	@PersistenceContext(unitName = "adota_pet")
	public EntityManager manager;

	public Animal include(Animal animal) {

		manager.persist(animal);

		return animal;
	}

	public Animal update(Animal animal) {
		manager.merge(animal);
		return animal;
	}

	public void remove(Integer animalId) {
		manager.remove(manager.getReference(Animal.class, animalId));
	}

	@SuppressWarnings("unchecked")
	public Collection<Animal> findAll() {
		Query q = manager.createQuery("select a from " + Animal.NAME + " a");
		return q.getResultList();
	}

}
