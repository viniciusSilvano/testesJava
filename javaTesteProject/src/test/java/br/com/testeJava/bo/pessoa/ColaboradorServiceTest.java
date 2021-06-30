package br.com.testeJava.bo.pessoa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.testeJava.bo.elasticSearch.ElasticSearchBO;
import br.com.testeJava.dao.pessoa.ColaboradorDAO;
import br.com.testeJava.entity.pessoa.Colaborador;

@ExtendWith(MockitoExtension.class)
public class ColaboradorServiceTest {
	
	private static final String COLABORADOR_NAME = "Juan";

	@InjectMocks
	private ColaboradorService colaboradorService;
	
	@Mock
	private ElasticSearchBO elasticSearchBO;
	
	@Mock
	private ColaboradorDAO colaboradorDAO;
	
	@Mock
	private SearchResponse searchResponse;
	
	@Mock
	private SearchHits searchHits;
	@Mock
	private SearchHit searchHit;
	
	@Test
	public void must_retrieve_name_from_elastic_search(){
		Mockito.when(elasticSearchBO.executeSearch(Mockito.anyString(),Mockito.any()))
			.thenReturn(searchResponse);
		Mockito.when(searchResponse.getHits()).thenReturn(searchHits);
		SearchHit[] hits = {searchHit};
		Mockito.when(searchHits.getHits()).thenReturn(hits);
		Mockito.when(this.searchHit.getId()).thenReturn("1");
		List<Long> idsColaboradores = Collections.singletonList(1L);
		Colaborador colaborador = new Colaborador();
		colaborador.setId(1L);
		colaborador.setNome(COLABORADOR_NAME);
		Mockito.when(this.colaboradorDAO.ListByIds(idsColaboradores, Colaborador.class))
			.thenReturn(Arrays.asList(colaborador));
		this.colaboradorService.searchByName(COLABORADOR_NAME);
	}
	
}
