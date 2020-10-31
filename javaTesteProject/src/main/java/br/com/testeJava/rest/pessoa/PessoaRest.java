package br.com.testeJava.rest.pessoa;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.pessoa.PessoaService;
import br.com.testeJava.bo.pessoa.qualifier.PessoaServiceQualifier;
import br.com.testeJava.entity.IEntidade;
import br.com.testeJava.entity.pessoa.Pessoa;
import br.com.testeJava.rest.BaseRest;

@Path("/pessoa")
public class PessoaRest extends BaseRest {
	
	@Inject
	@PessoaServiceQualifier
	BaseService service;

	@Override
	protected PessoaService getService() {
		return ((PessoaService)service);
	}
	
	@PUT
	@Path("/indexar")
	@Consumes(value = {MediaType.APPLICATION_JSON})
	public Response indexarEntidade(Pessoa entidade) {		
		try {
			getService().indexarEntidade(entidade);
			return Response.status(Status.CREATED).build();
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
	}
}
