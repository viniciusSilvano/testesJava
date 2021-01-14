package br.com.testeJava.bo.testeZip;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.NotImplementedException;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.dao.IDAO;
import br.com.testeJava.util.ZipUtils;

@Stateless
public class TesteZipService extends BaseService {

	@Inject
	ZipUtils zipUtils;
	
	@Override
	protected IDAO getDAO() {
		throw new NotImplementedException();
	}
	
	public Response executarTeste() {
		return zipUtils.zipItLive();
	}
}
