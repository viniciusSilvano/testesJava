package br.com.testeJava.bo.pessoa;

import javax.inject.Inject;

import org.apache.commons.lang3.NotImplementedException;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.elasticSearch.IElasticSearchBO;
import br.com.testeJava.bo.elasticSearch.qualifiers.ElasticSearchBOQualifier;
import br.com.testeJava.bo.pessoa.qualifier.PessoaServiceQualifier;
import br.com.testeJava.dao.testeDesempenho.IDAO;
import br.com.testeJava.entity.IEntidade;
import br.com.testeJava.entity.pessoa.Colaborador;


@PessoaServiceQualifier
public class PessoaService extends BaseService{
	
	private static final String ELASTIC_SEARCH_INDEX = "pessoa";
	private static final String ELASTIC_SEARCH_INDEX_COLABORADOR = "colaborador";
	
	@Inject
	@ElasticSearchBOQualifier
	private IElasticSearchBO elasticService;
	

	@Override
	protected IDAO getDAO() {
		throw new NotImplementedException();
	}
	
	public void indexarEntidade(IEntidade entidade) {
		if(entidade instanceof Colaborador) {
			elasticService.inserirIndiceDocumento(entidade,ELASTIC_SEARCH_INDEX,ELASTIC_SEARCH_INDEX_COLABORADOR);			
		}
	}

}
