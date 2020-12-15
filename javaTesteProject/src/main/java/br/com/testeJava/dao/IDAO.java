package br.com.testeJava.dao;

import javax.persistence.EntityManager;

import br.com.testeJava.entity.IEntidade;

public interface IDAO {
	public EntityManager getEntityManager();
	
	public default void  incluir(IEntidade entidade) {
		EntityManager entityManager = this.getEntityManager();
		entityManager.persist(entidade);
	};
	
}
