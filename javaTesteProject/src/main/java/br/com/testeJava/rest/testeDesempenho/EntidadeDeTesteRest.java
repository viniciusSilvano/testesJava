package br.com.testeJava.rest.testeDesempenho;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.IService;
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
}
