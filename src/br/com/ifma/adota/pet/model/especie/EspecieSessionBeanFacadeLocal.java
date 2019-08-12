package br.com.ifma.adota.pet.model.especie;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface EspecieSessionBeanFacadeLocal {
	
	public String JNDI = "EspecieSessionBeanFacade!br.com.ifma.adota.pet.model.especie.EspecieSessionBeanFacadeLocal";
	
	public Especie include(Especie especie);

	public Especie update(Especie especie);

	public void remove(Integer especieId);
	
	public Collection<Especie> findAll();
		
}
