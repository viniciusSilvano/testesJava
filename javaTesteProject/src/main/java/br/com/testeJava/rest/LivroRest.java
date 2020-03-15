package br.com.testeJava.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.testeJava.bo.LivroService;
import br.com.testeJava.dto.LivroDto;

@Path("/livro")
public class LivroRest {
	
	@Inject
	LivroService livroService;
	
	@GET
	@Path(value = "")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLivro() {
		List<LivroDto> livros = livroService.consultarLivros();
		return Response.status(Status.OK).entity(livros).build();
	}
	
	@GET
	@Path("{id}")
	public void getLivroPorId(@PathParam("id") Long id) {
		System.out.println("entrou no get de livro por id");
		//return Response.status(Status.FOUND).entity(livroService.consultarLivroPorId(id));
	} 
}
