package br.com.testeJava.rest.pessoa;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.pessoa.ColaboradorService;
import br.com.testeJava.bo.pessoa.qualifier.ColaboradorServiceQualifier;
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
	
	@GET
	@Path("/{nome}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getColaboradorByName(@PathParam("nome") String nome) {
		try {
			return Response.status(Status.ACCEPTED).entity(getService().searchByName(nome)).build();
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
	}
	
	@GET
	public Response listAll() {
		try {
			return Response.status(Status.ACCEPTED).entity(getService().listAll()).build();
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
	}
	
}
