package br.com.testeJava.rest.pessoa;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.pessoa.ColaboradorService;
import br.com.testeJava.bo.pessoa.qualifier.ColaboradorServiceQualifier;
import br.com.testeJava.entity.pessoa.Colaborador;
import br.com.testeJava.exception.JavaTesteProjectException;
import br.com.testeJava.rest.BaseRest;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Optional;

@Path("/colaborador")
public class ColaboradorRest extends BaseRest {
	
	@Inject
	@ColaboradorServiceQualifier
	BaseService<Colaborador> service;

	@Override
	protected ColaboradorService getService() {
		return (ColaboradorService) service;
	}

	@POST
	@Path("/indexar")
	@Consumes(value = {MediaType.APPLICATION_JSON})
	public Response saveOrUpdate(Colaborador entidade) throws Exception {
		try {
			getService().saveOrUpdateAndIndexElastic(entidade);
			return Response.status(Status.CREATED).build();
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}	
	}
	
	@PUT
	@Path("{id}/nome")
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response atualizarNomePorId(@PathParam("id") int id, String nome) {
		try {
			getService().atualizarNomePorId(id,nome);
			return Response.status(Status.ACCEPTED).build();
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
			return Response.status(Status.ACCEPTED).entity(parseListToJsonString(getService().listAll())).build();
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
	}

	@POST
	public Response criarNovoColaborador(@FormParam("nome") String nome){
		Colaborador colaborador = new Colaborador();
		colaborador.setNome(nome);
		try {
			this.getService().validarNovoColaborador(colaborador);
			this.getService().saveOrUpdate(colaborador);
		} catch (JavaTesteProjectException e) {
			return Response.status(e.getStatus()).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
		return Response.ok().build();
	}

	@GET
	@Path("/criteria-test")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response realizarTesteCriteria(@QueryParam("nome") String nome){
		try {
			return Response.ok(parseListToJsonString(this.getService().realizarTesteCriteria(Optional.ofNullable(nome)))).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
}
