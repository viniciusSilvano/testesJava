package br.com.testeJava.dao.pessoa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.testeJava.dao.IDAO;
import br.com.testeJava.dao.pessoa.qualifier.ColaboradorDAOQualifier;


@ColaboradorDAOQualifier
@Stateless
public class ColaboradorDAO implements IDAO {

	@PersistenceContext(unitName = "javaTesteProject")
	EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
