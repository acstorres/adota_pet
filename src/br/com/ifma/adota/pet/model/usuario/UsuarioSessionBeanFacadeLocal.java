package br.com.ifma.adota.pet.model.usuario;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface UsuarioSessionBeanFacadeLocal {

	public String JNDI = "UsuarioSessionBeanFacade!br.com.ifma.adota.pet.model.usuario.UsuarioSessionBeanFacadeLocal";

	public Usuario include(Usuario usuario);

	public Usuario update(Usuario usuario);

	public void remove(Long usuarioId);

	public Collection<Usuario> findAll();

	public Usuario findByLogin(String login);

	public Usuario findByPrymaryKey(Integer usuarioId);

}
