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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		ObjectMapper mapper = new ObjectMapper();
		List<LivroDto> livros = livroService.consultarLivros();
		String resposta;
		try {
			resposta = mapper.writeValueAsString(livros);
		} catch (JsonProcessingException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.OK).entity(resposta).build();
	}
	
	@GET
	@Path("{id}")
	public void getLivroPorId(@PathParam("id") Long id) {
		System.out.println("entrou no get de livro por id");
		//return Response.status(Status.FOUND).entity(livroService.consultarLivroPorId(id));
	} 
}
