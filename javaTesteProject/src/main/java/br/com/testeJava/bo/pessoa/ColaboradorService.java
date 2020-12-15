package br.com.testeJava.bo.pessoa;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.elasticSearch.IElasticSearchBO;
import br.com.testeJava.bo.elasticSearch.qualifiers.ElasticSearchBOQualifier;
import br.com.testeJava.bo.pessoa.qualifier.ColaboradorServiceQualifier;
import br.com.testeJava.dao.IDAO;
import br.com.testeJava.dao.pessoa.qualifier.ColaboradorDAOQualifier;
import br.com.testeJava.entity.IEntidade;
import br.com.testeJava.entity.pessoa.Colaborador;

@Stateless
@ColaboradorServiceQualifier
public class ColaboradorService extends BaseService{
	
	private static final String ELASTIC_SEARCH_INDEX_GENERICO = "pessoa";
	private static final String ELASTIC_SEARCH_INDEX_COLABORADOR = "colaborador";
	
	@Inject
	@ElasticSearchBOQualifier
	private IElasticSearchBO elasticService;
	
	@Inject
	@ColaboradorDAOQualifier
	private IDAO ColaboradorDAOQualifier;

	@Override
	protected IDAO getDAO() {
		return ColaboradorDAOQualifier;
	}
	
	
	public void indexarEntidade(Colaborador entidade) throws IOException {
				
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserir(IEntidade entidade) throws IOException {
		try {
			getDAO().incluir(entidade);
			elasticService.inserirIndiceDocumento(entidade,ELASTIC_SEARCH_INDEX_GENERICO,ELASTIC_SEARCH_INDEX_COLABORADOR);
		} catch (IOException e) {
			throw e;
		}	
	}

}
