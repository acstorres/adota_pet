package br.com.ifma.adota.pet.model.usuario;

import javax.ejb.Local;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacade;

@Local
public interface UsuarioSessionBeanFacadeLocal extends GenericsSessionBeanFacade<Usuario, Long>{

	public String JNDI = "UsuarioSessionBeanFacade!br.com.ifma.adota.pet.model.usuario.UsuarioSessionBeanFacadeLocal";
	
	//	public  Usuario include(Usuario usuario);
	//
	//	public Usuario update(Usuario usuario);
	//
	//	public void remove(Long usuarioId);
	//	
	//	public Collection<Usuario> findAll();
	
	public Usuario findByLogin(String login);
	
}
