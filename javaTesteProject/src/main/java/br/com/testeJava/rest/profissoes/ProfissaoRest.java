package br.com.testeJava.rest.profissoes;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.testeJava.bo.profissoes.ProfissaoService;
import br.com.testeJava.rest.BaseRest;

@Path("/profissoes")
public class ProfissaoRest extends BaseRest {
	
	@Inject
	ProfissaoService profissaoService;
	
	@GET
	@Path(value = "")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listarProfissoes(){
		return Response.ok().entity(parseListToJsonString(profissaoService.recuperarProfissoes())).build();
	}
}
