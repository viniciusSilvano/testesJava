package br.com.testeJava.dao.testeDesempenho;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;

@Dependent
public interface IDAO {
	public EntityManager getEntityManager();
}
