package br.com.ifma.adota.pet.model.animal;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface AnimalSessionBeanFacadeLocal {
	
	public String JNDI = "AnimalSessionBeanFacade!br.com.ifma.adota.pet.model.animal.AnimalSessionBeanFacadeLocal";

	public Animal include(Animal animal);

	public Animal update(Animal animal);

	public void remove(Integer animalId);

	public Collection<Animal> findAll();
	public Collection<Animal> findByClienteId(Integer clienteId);

}
