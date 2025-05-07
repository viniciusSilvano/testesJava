package br.com.testeJava.rest.testNamedQueries;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.testNamedQueries.UserService;
import br.com.testeJava.bo.testNamedQueries.qualifier.UserServiceQualifier;
import br.com.testeJava.entity.testNamedQueries.User;
import br.com.testeJava.rest.BaseRest;

@Path("named_query_test")
public class UserRest extends BaseRest {
	
	@Inject
	@UserServiceQualifier
	private UserService userService;
	
	@Override
	protected BaseService<User> getService() {
		return userService;
	}
	
	protected UserService getServiceImpl() {
		return (UserService) getService();
	}
	
	@GET
	public void cachedListAllTest() {
		this.getServiceImpl().testListAllWithCache();
	}
	
	@GET
	@Path("evict_cache")
	public void evictCache() {
		this.getServiceImpl().evictCache();
	}
	
}
