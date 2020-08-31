package br.com.testeJava.rest.testeDesempenho;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

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
	@Path("/realizarMock")
	public void solicitarMock(Long quantidade) {
		((EntidadeDeTesteService) service).realizarMockBD(quantidade);
	}
	
	@GET
	@Path("/verificarTempoListagem")
	public void verificarTempoListagem() {
		((EntidadeDeTesteService) service).executarTesteDeTempo(() -> ((EntidadeDeTesteService) service).listar());
	}
	
	@GET
	@Path("/{id}/verificarTempoListagemPorId")
	public void verificarTempoListagemPorId(@PathParam("id") Long id) {
		((EntidadeDeTesteService) service).executarTesteDeTempo(() -> ((EntidadeDeTesteService) service).listarPorId(id));
	}
	
	@GET
	@Path("/verificarTempoListagemComFilhas")
	public void verificarTempoListagemComFilhas() {
		((EntidadeDeTesteService) service).executarTesteDeTempo(() -> ((EntidadeDeTesteService) service).listarComFilhas());
	}
	
	@GET
	@Path("/{id}/verificarTempoListagemPorIdComFilhas")
	public void verificarTempoListagemPorIdComFilhas(@PathParam("id") Long id) {
		((EntidadeDeTesteService) service).executarTesteDeTempo(() -> ((EntidadeDeTesteService) service).listarPorIdComFilhas(id));
	}
}
