package br.com.ifma.adota.pet.infraestrutura;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * @author Daniel Santos
 * @since 12/08/2019
 * @category HibernateUtilJPA
 */
public class HibernateUtilJPA {

	private EntityManagerFactory factory;

	@PersistenceContext
	private EntityManager manager;

	private static HibernateUtilJPA instance;

	public HibernateUtilJPA() {
		factory = Persistence.createEntityManagerFactory("prototip-persiste-jpa");

		if (factory != null) {
			manager = factory.createEntityManager();
		}
	}

	public static HibernateUtilJPA getInstance() {
		if (instance == null) {
			instance = new HibernateUtilJPA();
		}
		return instance;
	}

	public EntityManager getEntityManagerSession() {
		return manager;
	}
}
