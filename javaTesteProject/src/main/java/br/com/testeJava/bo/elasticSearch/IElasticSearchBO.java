package br.com.testeJava.bo.elasticSearch;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import br.com.testeJava.entity.IEntidade;
import br.com.testeJava.entity.pessoa.Colaborador;

@Stateless
public abstract class IElasticSearchBO{
	
	private RestHighLevelClient getClient() {
		return new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost",9200,"http")));
	}
	
	public void inserirIndiceDocumento(Colaborador obj, final String INDEX) throws IOException{
		RestHighLevelClient client = getClient();
		try {
			inserirIndiceDocumentoAbstract(obj,client,INDEX);
		}finally {
			try {
				client.close();
			} catch (IOException e) {
				System.out.println("Erro ao tentar fechar conexão com o ElasticSearch");
			}
		}
	}
	
	public void inserirIndiceDocumento(IEntidade obj, final String INDEX_GENERICO, final String INDEX) throws IOException {
		RestHighLevelClient client = getClient();
		try {
			inserirIndiceDocumentoAbstract(obj,client,INDEX_GENERICO,INDEX);
		}finally {
			try {
				client.close();
			} catch (IOException e) {
				System.out.println("Erro ao tentar fechar conexão com o ElasticSearch");
			}
		}
	}
	
	public SearchResponse executeSearch(final String INDEX,SearchSourceBuilder searchSourceBuilder ) {
		RestHighLevelClient client = getClient();
		try {
			try {
				SearchRequest searchRequest = executeSearchAbstract(INDEX,searchSourceBuilder);
				searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS)); 
				SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
				return searchResponse;
			} catch (IOException e) {
				System.out.println("Erro ao buscar no elasticSearch");
			}
		}finally {
			try {
				client.close();
			} catch (IOException e) {
				System.out.println("Erro ao tentar fechar conexão com o ElasticSearch");
			}
		}
		throw new IllegalStateException();
	}
	
	protected abstract void  inserirIndiceDocumentoAbstract(IEntidade obj, RestHighLevelClient client, String INDEX) throws IOException;
	protected abstract void  inserirIndiceDocumentoAbstract(IEntidade obj, RestHighLevelClient client, String INDEX_GENERICO, String INDEX) throws IOException;
	protected abstract SearchRequest  executeSearchAbstract(final String INDEX,SearchSourceBuilder searchSourceBuilder );
}
