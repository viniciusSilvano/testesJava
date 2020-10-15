package br.com.testeJava.rest.testeDesempenho;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.testeDesempenho.EntidadeDeTesteService;
import br.com.testeJava.bo.testeDesempenho.qualifiers.EntidadeDeTesteServiceQualifier;
import br.com.testeJava.entity.testeDesempenho.EntidadeDeTeste;
import br.com.testeJava.rest.BaseRest;

@Path("/entidadeTeste")
public class EntidadeDeTesteRest extends BaseRest {
	
	@Inject
	@EntidadeDeTesteServiceQualifier
	BaseService service;

	@Override
	protected BaseService getService() {
		return service;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void cadastrar(EntidadeDeTeste entity) {
		((EntidadeDeTesteService) service).cadastrarEntidadeDeTeste(entity);
	}
	
	@POST
	@Path("/teste-insert-select")
	public void cadastrarPorPaiEntidadeDeTeste1(Long idPai) {
		((EntidadeDeTesteService) service).cadastrarPorPaiEntidadeDeTeste1(idPai);
	}
	
	@POST
	@Path("/realizarMock")
	public void solicitarMock(Long quantidade) {
		((EntidadeDeTesteService) service).realizarMockBD(quantidade);
	}
	
	@GET
	@Path("/verificarTempoListagem")
	public Response verificarTempoListagem() {
		return Response.ok().entity(
				((EntidadeDeTesteService) service).executarTesteDeTempo(() -> ((EntidadeDeTesteService) service).listar())
		).build();
	}
	
	@GET
	@Path("/{id}/verificarTempoListagemPorId")
	public Response verificarTempoListagemPorId(@PathParam("id") Long id) {
		return Response.ok().entity(
				((EntidadeDeTesteService) service).executarTesteDeTempo(() -> ((EntidadeDeTesteService) service).listarPorId(id))
		).build();
	}
	
	@GET
	@Path("/verificarTempoListagemComFilhas")
	public Response verificarTempoListagemComFilhas() {
		return Response.ok().entity(
				((EntidadeDeTesteService) service).executarTesteDeTempo(() -> ((EntidadeDeTesteService) service).listarComFilhas())
		).build();
	}
	
	@GET
	@Path("/{id}/verificarTempoListagemPorIdComFilhas")
	public Response verificarTempoListagemPorIdComFilhas(@PathParam("id") Long id) {
		return Response.ok().entity(
				((EntidadeDeTesteService) service).executarTesteDeTempo(() -> ((EntidadeDeTesteService) service).listarPorIdComFilhas(id))
		).build();
	}
}
