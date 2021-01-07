package br.com.testeJava.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;

import br.com.testeJava.entity.IEntidade;

public interface IDAO {
	public EntityManager getEntityManager();
	
	public default Session getSession() {
		return this.getEntityManager().unwrap(Session.class);
	} 
	
	public default void  save(IEntidade entidade) {
		Session session = this.getSession();
		session.save(entidade);
	};
	
	public default void saveOrUpdate(IEntidade entidade) {
		Session session = this.getSession();
		session.saveOrUpdate(entidade);
	};
	
	public default <T> List<T>  ListByIds(List<Long> ids, Class<T> target) {
		MultiIdentifierLoadAccess<T> multiLoadAccess = getSession().byMultipleIds(target);
		List<T> entitys = multiLoadAccess.multiLoad(ids);
		return entitys;
	}
	
	@SuppressWarnings("unchecked")
	public default <T> List<T> listAll(Class<T> target, boolean isCacheable){
		return this.getSession().createQuery(String.format(" SELECT * FROM %s ", target.getSimpleName())).setCacheable(isCacheable).list();
	}
}
