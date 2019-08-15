package br.com.ifma.adota.pet.infraestrutura;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;

@Stateless
@Local(GenericsSessionBeanFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GenericsSessionBeanFacadeImpl<T, K extends Serializable> implements GenericsSessionBeanFacade<T, K> {

	protected static final String ERRO_ACESSO_A_BASE_DADOS = " : Ocorreu um erro ao "
			+ "acessar a base de dados. Tente novamente mais tarde.";

	protected Class<T> classe;

	@PersistenceContext(unitName = "adota_pet")
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	public GenericsSessionBeanFacadeImpl() {
		this.classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public EntityManager getEntityManagerSession() throws DaoRepositoryException {
		try {
			return em;
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	public Session getSession() throws DaoRepositoryException {
		return em.unwrap(Session.class);
	}
	
	public Statistics getStatisticas() throws DaoRepositoryException {
		Statistics statistics = getSession().getSessionFactory().getStatistics();
		return statistics;
	}

	@Override
	public T include(T obj) throws DaoRepositoryException {
		try {
			getSession().persist(obj);
			return obj;
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	@Override
	public T update(T obj) throws DaoRepositoryException {
		try {
			getSession().update(obj);
			return obj;
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	@Override
	public void remove(Integer id) throws DaoRepositoryException {
		try {
			getSession().delete(em.getReference(classe, id));
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	@Override
	public void remove(T obj) throws DaoRepositoryException {
		try {
			getSession().delete(getSession().getReference(classe, obj));
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	@Override
	public Collection<T> findAll() throws DaoRepositoryException {
		try {
			CriteriaBuilder criteriaBuilder = getEntityManagerSession().getCriteriaBuilder();
			CriteriaQuery<T> query = criteriaBuilder.createQuery(classe);

			TypedQuery<T> typedQuery = getEntityManagerSession().createQuery(query.select(query.from(classe)));

			return typedQuery.getResultList();
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	public void salvar(T obj) throws DaoRepositoryException {
		try {
			getSession().save(obj);
			getSession().flush();
			getSession().clear();
		} catch (HibernateException e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	public void salvarSemCommit(T obj) throws DaoRepositoryException {
		try {
			getSession().save(obj);
			getSession().close();
		} catch (HibernateException e) {
			getSession().getTransaction().rollback();
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	public void salvarEmLote(List<T> objs) throws DaoRepositoryException {
		try {
			for (T obj : objs)
				include(obj);

		} catch (HibernateException e) {
			getSession().getTransaction().rollback();
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	public void salvarOuAtualizarComCommit(T obj) throws DaoRepositoryException {
		try {
			getSession().saveOrUpdate(obj);
			getSession().flush();
			getSession().clear();
		} catch (HibernateException e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	public void excluir(T obj) throws DaoRepositoryException {
		try {
			getSession().delete(obj);
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	public void mergeComCommit(T obj) throws DaoRepositoryException {
		try {
			getSession().update(obj);
			getSession().flush();
			getSession().clear();
		} catch (HibernateException e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}



	@Override
	public T recuperar(K id) throws DaoRepositoryException {
		try {
			T obj = (T) getSession().get(classe, id);
			return obj;
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	@Override
	public List<T> recuperarTodos() throws DaoRepositoryException {
		try {
			CriteriaBuilder criteriaBuilder = getEntityManagerSession().getCriteriaBuilder();
			CriteriaQuery<T> query = criteriaBuilder.createQuery(classe);

			TypedQuery<T> typedQuery = getEntityManagerSession().createQuery(query.select(query.from(classe)));

			return typedQuery.getResultList();
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> recuperarPor(String queryString) throws DaoRepositoryException {
		try {
			Query query = getEntityManagerSession().createQuery(queryString);
			return query.getResultList();
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	public List<T> recuperarOrdenado(String propriedade, boolean ascendente) throws DaoRepositoryException {
		try {
			CriteriaBuilder criteriaBuilder = getEntityManagerSession().getCriteriaBuilder();
			CriteriaQuery<T> criteria = criteriaBuilder.createQuery(classe);
			Root<T> query = criteria.from(classe);

			criteria.select(query);
			if (ascendente) {
				criteria.orderBy(criteriaBuilder.asc(query.get(propriedade)));
			} else {
				criteria.orderBy(criteriaBuilder.desc(query.get(propriedade)));
			}
			return getEntityManagerSession().createQuery(criteria).getResultList();
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

}
