package br.com.ifma.adota.pet.infraestrutura;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.stat.Statistics;

public interface GenericsSessionBeanFacade<T, K extends Serializable> {

	///////////////////////////////////// Default

	EntityManager getEntityManagerSession() throws DaoRepositoryException;
	
	Session getSession() throws DaoRepositoryException;
	
	Statistics getStatisticas() throws DaoRepositoryException;

	///////////////////////////////////// Métodos Padrão

	T include(T obj) throws DaoRepositoryException;

	T update(T obj) throws DaoRepositoryException;

	void remove(Integer id) throws DaoRepositoryException;

	void remove(T obj) throws DaoRepositoryException;

	Collection<T> findAll() throws DaoRepositoryException;

	///////////////////////////////////// Demais Métodos

	void excluir(T obj) throws DaoRepositoryException;

	void mergeComCommit(T obj) throws DaoRepositoryException;

	T recuperar(K id) throws DaoRepositoryException;

	List<T> recuperarTodos() throws DaoRepositoryException;

	List<T> recuperarPor(String queryString) throws DaoRepositoryException;

	List<T> recuperarOrdenado(String propriedade, boolean ascendente) throws DaoRepositoryException;

	void salvar(T obj) throws DaoRepositoryException;

	void salvarSemCommit(T obj) throws DaoRepositoryException;

	void salvarEmLote(List<T> objs) throws DaoRepositoryException;

	void salvarOuAtualizarComCommit(T obj) throws DaoRepositoryException;

}
