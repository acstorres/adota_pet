package br.com.ifma.adota.pet.model.raca;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface RacaSessionBeanFacadeLocal {

	public String JNDI = "RacaSessionBeanFacade!br.com.ifma.adota.pet.model.raca.RacaSessionBeanFacadeLocal";

	public Raca include(Raca raca);

	public Raca update(Raca raca);

	public void remove(Integer racaId);

	public Collection<Raca> findAll();

}
