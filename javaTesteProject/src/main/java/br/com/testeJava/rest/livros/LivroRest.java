package br.com.testeJava.rest.livros;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.livro.LivroService;
import br.com.testeJava.bo.livro.qualifier.LivroServiceQualifier;
import br.com.testeJava.rest.BaseRest;

@Path("/livro")
public class LivroRest extends BaseRest {
	
	@Inject
	@LivroServiceQualifier
	BaseService livroService;
	
	@Override
	protected BaseService getService() {
		return livroService;
	} 
	
	@GET
	@Path(value = "/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLivro() {
		return Response.status(Status.OK).entity(parseListToJsonString(((LivroService)getService()).consultarLivros())).build();
	}
	
	@GET
	@Path("/{id}")
	public void getLivroPorId(@PathParam("id") Long id) {
		System.out.println("entrou no get de livro por id");
		//return Response.status(Status.FOUND).entity(livroService.consultarLivroPorId(id));
	}

}
