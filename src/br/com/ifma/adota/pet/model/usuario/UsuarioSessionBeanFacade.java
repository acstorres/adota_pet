package br.com.ifma.adota.pet.model.usuario;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UsuarioSessionBeanFacade implements UsuarioSessionBeanFacadeLocal {

	@PersistenceContext(unitName = "adota_pet")
	public EntityManager manager;

	public Usuario include(Usuario usuario) {
		manager.persist(usuario);

		return usuario;
				/*findByPrymaryKey(usuario.getUsuarioId());*/
	}

	public Usuario update(Usuario usuario) {
		manager.merge(usuario);
		return usuario;
	}

	public void remove(Long usuarioId) {
		manager.remove(manager.getReference(Usuario.class, usuarioId));
	}

	@SuppressWarnings("unchecked")
	public Collection<Usuario> findAll() {
		Query q = manager.createQuery("select u from " + Usuario.NAME + " u");
		return q.getResultList();
	}

	public Usuario findByLogin(String login) {
		Query q = manager.createQuery("select u from " + Usuario.NAME + " u where u.login =: login")
				.setParameter("login", login);

		return (Usuario) q.getSingleResult();
	}
	
	public Usuario findByPrymaryKey(Integer usuarioId) {
		Query q = manager.createQuery("select u from " + Usuario.NAME + " u where u.usuarioId =: usuarioId")
				.setParameter("usuarioId", usuarioId);

		return (Usuario) q.getSingleResult();
	}

}
