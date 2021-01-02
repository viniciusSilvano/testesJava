package br.com.testeJava.rest.pessoa;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.pessoa.ColaboradorService;
import br.com.testeJava.bo.pessoa.qualifier.ColaboradorServiceQualifier;
import br.com.testeJava.entity.IEntidade;
import br.com.testeJava.entity.pessoa.Colaborador;
import br.com.testeJava.rest.BaseRest;

@Path("/colaborador")
public class ColaboradorRest extends BaseRest {
	
	@Inject
	@ColaboradorServiceQualifier
	BaseService service;

	@Override
	protected ColaboradorService getService() {
		return (ColaboradorService) service;
	}

	@POST
	@Path("/indexar")
	@Consumes(value = {MediaType.APPLICATION_JSON})
	public Response saveOrUpdate(Colaborador entidade) throws Exception {
		try {
			getService().saveOrUpdate(entidade);
			return Response.status(Status.CREATED).build();
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}	
	}
	
	@OPTIONS
	@Path("/indexar")
	public Response saveOrUpdateOptions() throws Exception {
		try {
			return Response.status(Status.ACCEPTED)
					.header( "Access-Control-Allow-Origin", "http://localhost:4200")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}	
	}
	
}
