package br.com.ifma.adota.pet.model.lancamento;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LancamentoSessionBeanFacade implements LancamentoSessionBeanFacadeLocal {
	@PersistenceContext(unitName = "adota_pet")
	public EntityManager manager;

	public Lancamento include(Lancamento lancamento) {

		manager.persist(lancamento);

		return lancamento;
	}

	public Lancamento update(Lancamento lancamento) {
		manager.merge(lancamento);
		return lancamento;
	}

	public void remove(Integer lancamentoId) {
		manager.remove(manager.getReference(Lancamento.class, lancamentoId));
	}

	@SuppressWarnings("unchecked")
	public Collection<Lancamento> findAll() {
		Query q = manager.createQuery("select l from " + Lancamento.NAME + " l");
		return q.getResultList();
	}

}
