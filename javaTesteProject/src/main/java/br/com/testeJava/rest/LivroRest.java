package br.com.testeJava.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import br.com.testeJava.bo.LivroService;

@Path("/livro")
public class LivroRest {
	
	@Inject
	LivroService livroService;
	
	@GET()
	@Path("{id}")
	public ResponseBuilder getLivroPorId(@PathParam("id") Long id) {
		return Response.status(Status.FOUND).entity(livroService.consultarLivroPorId(id));
	} 
}
