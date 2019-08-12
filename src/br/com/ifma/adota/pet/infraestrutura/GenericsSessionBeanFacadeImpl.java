package br.com.ifma.adota.pet.infraestrutura;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.lang.reflect.ParameterizedType;

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

@Stateless
@Local(GenericsSessionBeanFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GenericsSessionBeanFacadeImpl<T, K extends Serializable> implements GenericsSessionBeanFacade<T, K> {

	protected static final String ERRO_ACESSO_A_BASE_DADOS = "Ocorreu um erro ao acessar à base de dados.";

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

	@Override
	public T include(T obj) throws DaoRepositoryException {
		try {
			getEntityManagerSession().persist(obj);
			return obj;
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	@Override
	public T update(T obj) throws DaoRepositoryException {
		try {
			getEntityManagerSession().merge(obj);
			return obj;
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	@Override
	public void remove(Integer id) throws DaoRepositoryException {
		try {
			getEntityManagerSession().remove(em.getReference(classe, id));
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	@Override
	public void remove(T obj) throws DaoRepositoryException {
		try {
			getEntityManagerSession().remove(getEntityManagerSession().getReference(classe, obj));
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
			getEntityManagerSession().persist(obj);
			getEntityManagerSession().getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			getEntityManagerSession().getTransaction().rollback();
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	public void salvarSemCommit(T obj) throws DaoRepositoryException {
		try {
			getEntityManagerSession().persist(obj);
			getEntityManagerSession().close();
		} catch (HibernateException e) {
			getEntityManagerSession().getTransaction().rollback();
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	public void salvarEmLote(List<T> objs) throws DaoRepositoryException {
		try {
			for (T obj : objs) {
				getEntityManagerSession().persist(obj);
			}
			getEntityManagerSession().getTransaction().commit();
			getEntityManagerSession().close();
		} catch (HibernateException e) {
			getEntityManagerSession().getTransaction().rollback();
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	public void salvarOuAtualizarComCommit(T obj) throws DaoRepositoryException {
		try {
			getEntityManagerSession().merge(obj);
			getEntityManagerSession().getTransaction().commit();
			getEntityManagerSession().close();
		} catch (HibernateException e) {
			e.printStackTrace();
			getEntityManagerSession().getTransaction().rollback();
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	public void excluir(T obj) throws DaoRepositoryException {
		try {
			getEntityManagerSession().remove(obj);
		} catch (HibernateException e) {
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	public void mergeComCommit(T obj) throws DaoRepositoryException {
		try {
			getEntityManagerSession().merge(obj);
			getEntityManagerSession().getTransaction().commit();
			getEntityManagerSession().close();
		} catch (HibernateException e) {
			e.printStackTrace();
			getEntityManagerSession().getTransaction().rollback();
			throw new DaoRepositoryException(ERRO_ACESSO_A_BASE_DADOS, e);
		}
	}

	@Override
	public T recuperar(K id) throws DaoRepositoryException {
		try {
			T obj = (T) getEntityManagerSession().getReference(classe, id);
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
