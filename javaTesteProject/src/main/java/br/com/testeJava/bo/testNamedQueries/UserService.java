package br.com.testeJava.bo.testNamedQueries;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.testNamedQueries.qualifier.UserServiceQualifier;
import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testNamedQueries.UserDao;
import br.com.testeJava.dao.testNamedQueries.qualifer.UserDaoQualifier;
import br.com.testeJava.entity.testNamedQueries.User;

@Stateless
@UserServiceQualifier
public class UserService extends BaseService<User> {

	@Inject
	@UserDaoQualifier
	private UserDao userDao;
	
	@Override
	protected BaseDAO<User> getDAO() {
		return userDao;
	}
	
	public void testListAllWithCache() {
		long tempoInicioTest1 = System.currentTimeMillis();
		this.listAll();
		System.out.println("query timing: " + (System.currentTimeMillis() - tempoInicioTest1));
		
		long tempoInicioTest2 = System.currentTimeMillis();
		this.listAll();
		System.out.println("query timing: " + (System.currentTimeMillis() - tempoInicioTest2));
		
		long tempoInicioTest3 = System.currentTimeMillis();
		this.listAll();
		System.out.println("query timing: " + (System.currentTimeMillis() - tempoInicioTest3));
		
		long tempoInicioTest4 = System.currentTimeMillis();
		this.listAll();
		System.out.println("query timing: " + (System.currentTimeMillis() - tempoInicioTest4));
		
	}
	
	public List<User> listAll(){
		List<User> users = this.getDAO().listAll(User.class,false);
		return users;
	}
	
	public void evictCache() {
		this.getDAO().evictSecondLevelCache(User.class);
	}

}
