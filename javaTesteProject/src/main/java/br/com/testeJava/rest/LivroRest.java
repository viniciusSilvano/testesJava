package br.com.testeJava.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import br.com.testeJava.bo.LivroService;

@Path("/livro")
public class LivroRest {
	
	@Inject
	LivroService livroService;
	
	@GET
	@Path(value = "")
	public Response getLivro() {
		return Response.ok().build();
	}
	
	@GET
	@Path("{id}")
	public void getLivroPorId(@PathParam("id") Long id) {
		System.out.println("entrou no get de livro por id");
		//return Response.status(Status.FOUND).entity(livroService.consultarLivroPorId(id));
	} 
}
