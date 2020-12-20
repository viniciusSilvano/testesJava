package br.com.testeJava.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.testeJava.entity.IEntidade;

public interface IDAO {
	public EntityManager getEntityManager();
	
	public default Session getSession() {
		return this.getEntityManager().unwrap(Session.class);
	} 
	
	public default void  save(IEntidade entidade) {
		EntityManager entityManager = this.getEntityManager();
		entityManager.persist(entidade);
	};
	
	public default void saveOrUpdate(IEntidade entidade) {
		Session session = this.getSession();
		session.saveOrUpdate(entidade);
	};
	
}
