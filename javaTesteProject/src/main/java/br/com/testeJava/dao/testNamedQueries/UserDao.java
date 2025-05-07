package br.com.testeJava.dao.testNamedQueries;

import java.util.List;

import javax.ejb.Stateless;

import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testNamedQueries.qualifer.UserDaoQualifier;
import br.com.testeJava.entity.testNamedQueries.User;

@Stateless
@UserDaoQualifier
public class UserDao extends BaseDAO<User> {
	
	

	@Override
	public List<User> listAll(Class<User> target, boolean isCacheable) {
		return getEntityManager()
			    .createNamedQuery("User.findAll", User.class)
			    .getResultList();
	}
	
	
}
