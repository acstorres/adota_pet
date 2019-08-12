package br.com.ifma.adota.pet.model.usuario;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacadeImpl;

/**
 * Session Bean implementation class UsuarioSessionBeanFacade
 */

@Stateless
public class UsuarioSessionBeanFacade extends GenericsSessionBeanFacadeImpl<Usuario, Long>
		implements UsuarioSessionBeanFacadeLocal {

	public static final String NAME = "adota_pet_UsuarioSessionBeanFacade";

//	@PersistenceContext(unitName = "adota_pet")
//	public EntityManager manager;
//
//	public  Usuario include(Usuario usuario) {
//
//		manager.persist(usuario);
//
//		return usuario;
//	}
//
//	public Usuario update(Usuario usuario) {
//		manager.merge(usuario);
//		return usuario;
//	}
//
//	public void remove(Long usuarioId) {
//		manager.remove(manager.getReference(Usuario.class, usuarioId));
//	}
//	
//	@SuppressWarnings("unchecked")
//	public Collection<Usuario> findAll() {
//		Query q = manager.createQuery("select u from " + Usuario.NAME + " u");
//		return q.getResultList();
//	}

	public Usuario findByLogin(String login) {
		Query q = getEntityManagerSession().createQuery("select u from " + Usuario.NAME + " u where u.login =: login")
				.setParameter("login", login);

		return (Usuario) q.getSingleResult();
	}

}
