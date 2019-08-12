package br.com.ifma.adota.pet.infraestrutura;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author Daniel Santos
 * @since 12/08/2019
 * @category EntityManagerFactory
 */
public class EntityManagerFactory {

	private final javax.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("adota_pet");

	public EntityManager createEntityManager() {
		return emf.createEntityManager();
	}

	public void close() {
		this.emf.close();
	}
}
