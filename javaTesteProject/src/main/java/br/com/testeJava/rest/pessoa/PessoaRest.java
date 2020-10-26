package br.com.testeJava.rest.pessoa;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.testeJava.bo.elasticSearch.IElasticSearchBO;
import br.com.testeJava.bo.elasticSearch.qualifiers.ElasticSearchBOQualifier;
import br.com.testeJava.entity.pessoa.Colaborador;

@Path("/pessoa")
public class PessoaRest {
	
	@Inject
	@ElasticSearchBOQualifier
	private IElasticSearchBO service;
	
	private static final String ELASTIC_SEARCH_INDEX = "pessoa";
	
	@PUT
	@Path("/indexar")
	public Response indexarEntidade(Colaborador entidade) {		
		try {
			service.inserirIndiceDocumento(entidade,ELASTIC_SEARCH_INDEX);
			return Response.status(Status.CREATED).build();
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
	}
}
