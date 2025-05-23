package br.com.testeJava.bo.pessoa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.builder.ColaboradorDTOBuilder;
import br.com.testeJava.bo.elasticSearch.IElasticSearchBO;
import br.com.testeJava.bo.elasticSearch.qualifiers.ElasticSearchBOQualifier;
import br.com.testeJava.bo.pessoa.qualifier.ColaboradorServiceQualifier;
import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.pessoa.ColaboradorDAO;
import br.com.testeJava.dao.pessoa.qualifier.ColaboradorDAOQualifier;
import br.com.testeJava.dto.ColaboradorDTO;
import br.com.testeJava.entity.IEntidade;
import br.com.testeJava.entity.pessoa.Colaborador;

@Stateless
@ColaboradorServiceQualifier
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ColaboradorService extends BaseService<Colaborador>{
	
	private static final String ELASTIC_SEARCH_INDEX_GENERICO = "pessoa";
	private static final String ELASTIC_SEARCH_INDEX_COLABORADOR = "colaborador";
	
	@Inject
	@ElasticSearchBOQualifier
	private IElasticSearchBO elasticService;
	
	@Inject
	@ColaboradorDAOQualifier
	private BaseDAO<Colaborador> dao;

	@Override
	protected BaseDAO<Colaborador> getDAO() {
		return dao;
	}
		
	public List<ColaboradorDTO> searchByName(String nome) {
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("nome", nome); 
		initSearchByNameRules(sourceBuilder, matchQueryBuilder);
		SearchResponse response = elasticService.executeSearch(ELASTIC_SEARCH_INDEX_COLABORADOR, sourceBuilder);
		
		List<Long> idsColaboradores = new ArrayList<>();
		for(SearchHit hit : response.getHits().getHits()) {
			idsColaboradores.add(Long.valueOf(hit.getId()));
		}
		
		List<Colaborador> colaboradoresResult = dao.ListByIds(idsColaboradores, Colaborador.class);
		return colaboradoresResult.stream().filter(Objects::nonNull).map(
				c -> ColaboradorDTOBuilder.getInstance().id(c.getId()).nome(c.getNome()).build()).collect(Collectors.toList());		
	}
	
	public List<ColaboradorDTO> listAll(){
		return getDAO().listAll(Colaborador.class, false).stream()
				.map(c -> ColaboradorDTOBuilder.getInstance().id(c.getId()).nome(c.getNome()).build()).collect(Collectors.toList());
	} 


	private void initSearchByNameRules(SearchSourceBuilder sourceBuilder, MatchQueryBuilder matchQueryBuilder) {
		matchQueryBuilder.fuzziness(Fuzziness.AUTO); 
		matchQueryBuilder.prefixLength(3); 
		matchQueryBuilder.maxExpansions(10); 
		sourceBuilder.query(matchQueryBuilder);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveOrUpdate(IEntidade entidade) throws IOException {
		try {
			getDAO().saveOrUpdate(entidade);
			elasticService.inserirIndiceDocumento(entidade,ELASTIC_SEARCH_INDEX_GENERICO,ELASTIC_SEARCH_INDEX_COLABORADOR);
		} catch (IOException e) {
			throw e;
		}	
	}

	public void atualizarNomePorId(int id, String nome) {
		((ColaboradorDAO) getDAO()).atualizarNomePorId(id,nome);
	}
}
