package br.com.ifma.adota.pet.model.adocao;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface AdocaoSessionBeanFacadeLocal {
	
	public String JNDI = "AdocaoSessionBeanFacade!br.com.ifma.adota.pet.model.adocao.AdocaoSessionBeanFacadeLocal";

	public Adocao include(Adocao adocao);

	public Adocao update(Adocao adocao);

	public void remove(Integer adocaoId);

	public Collection<Adocao> findAll();

}
