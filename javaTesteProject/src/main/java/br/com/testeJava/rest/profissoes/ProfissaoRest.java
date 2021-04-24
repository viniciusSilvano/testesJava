package br.com.testeJava.rest.profissoes;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.profissoes.ProfissaoService;
import br.com.testeJava.bo.profissoes.qualifier.ProfissaoServiceQualifier;
import br.com.testeJava.rest.BaseRest;

@Path("/profissoes")
public class ProfissaoRest extends BaseRest {
	
	@Inject
	@ProfissaoServiceQualifier
	BaseService profissaoService;
	
	@Override
	protected BaseService getService() {
		// TODO Auto-generated method stub
		return profissaoService;
	}
	
	@GET
	@Path(value = "")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listarProfissoes(){
		return Response.ok().entity(parseListToJsonString(((ProfissaoService)getService()).recuperarProfissoes())).build();
	}


}
