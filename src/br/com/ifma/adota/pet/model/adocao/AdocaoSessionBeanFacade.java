package br.com.ifma.adota.pet.model.adocao;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class AdocaoSessionBeanFacade implements AdocaoSessionBeanFacadeLocal{

	@PersistenceContext(unitName = "adota_pet")
	public EntityManager manager;

	public Adocao include(Adocao adocao) {

		manager.persist(adocao);

		return adocao;
	}

	public Adocao update(Adocao adocao) {
		manager.merge(adocao);
		return adocao;
	}

	public void remove(Integer adocaoId) {
		manager.remove(manager.getReference(Adocao.class, adocaoId));
	}

	@SuppressWarnings("unchecked")
	public Collection<Adocao> findAll() {
		Query q = manager.createQuery("select a from " + Adocao.NAME + " a");
		return q.getResultList();
	}
}
