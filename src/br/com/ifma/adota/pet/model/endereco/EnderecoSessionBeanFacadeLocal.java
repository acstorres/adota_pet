package br.com.ifma.adota.pet.model.endereco;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface EnderecoSessionBeanFacadeLocal {
	
	public String JNDI = "EnderecoSessionBeanFacade!br.com.ifma.adota.pet.model.endereco.EnderecoSessionBeanFacadeLocal";


	public Endereco include(Endereco endereco);
	public Endereco update(Endereco endereco);

	public void remove(Integer enderecoId);
	
	public Collection<Endereco> findAll();
	
	public Endereco findByPrimaryKey(Integer enderecoId);
	
}
