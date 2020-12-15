package br.com.testeJava.rest.pessoa;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.pessoa.qualifier.ColaboradorServiceQualifier;
import br.com.testeJava.entity.pessoa.Colaborador;
import br.com.testeJava.rest.BaseRest;

@Path("/colaborador")
public class ColaboradorRest extends BaseRest {
	
	@Inject
	@ColaboradorServiceQualifier
	BaseService service;

	@Override
	protected BaseService getService() {
		return service;
	}
	
	@PUT
	@Path("/indexar")
	@Consumes(value = {MediaType.APPLICATION_JSON})
	public Response indexarEntidade(Colaborador entidade) {		
		try {
			getService().inserir(entidade);
			return Response.status(Status.CREATED).build();
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
	}
}
