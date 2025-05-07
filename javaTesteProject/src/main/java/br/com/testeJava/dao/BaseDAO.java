package br.com.testeJava.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;

import br.com.testeJava.entity.IEntidade;

public abstract class BaseDAO<T> {
	
	@PersistenceContext(unitName = "javaTesteProject")
	private EntityManager entityManager;
	
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	protected Session getSession() {
		return this.getEntityManager().unwrap(Session.class);
	} 
	
	public void  save(IEntidade entidade) {
		Session session = this.getSession();
		session.save(entidade);
	};
	
	public void saveOrUpdate(IEntidade entidade) {
		Session session = this.getSession();
		session.saveOrUpdate(entidade);
	};
	
	public List<T>  ListByIds(List<Long> ids, Class<T> target) {
		MultiIdentifierLoadAccess<T> multiLoadAccess = getSession().byMultipleIds(target);
		List<T> entitys = multiLoadAccess.multiLoad(ids);
		return entitys;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAll(Class<T> target, boolean isCacheable){
		return this.getSession().createQuery(String.format(" SELECT alvo FROM %s alvo ", target.getSimpleName())).setCacheable(isCacheable).list();
	}
	
	public void evictSecondLevelCache(Class<T> target) {
		this.getSession().getSessionFactory().getCache().evict(target);
	}

}
