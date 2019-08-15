package br.com.ifma.adota.pet.model.cliente;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface ClienteSessionBeanFacadeLocal {

	public String JNDI = "ClienteSessionBeanFacade!br.com.ifma.adota.pet.model.cliente.ClienteSessionBeanFacadeLocal";

	public Cliente include(Cliente cliente);

	public Cliente update(Cliente cliente);

	public void remove(Integer clienteId);

	public Collection<Cliente> findAll();

	public Cliente findByUsuarioId(Integer usuarioId);

	public Cliente findByPrimaryKey(Integer clienteId);

}
