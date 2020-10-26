package br.com.testeJava.bo.elasticSearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import br.com.testeJava.entity.IEntidade;

public abstract class IElasticSearchBO{
	
	private RestHighLevelClient getClient() {
		return new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost",9200,"http")));
	}
	
	public void inserirIndiceDocumento(IEntidade obj, final String INDEX) {
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
	
	protected abstract void  inserirIndiceDocumentoAbstract(IEntidade obj, RestHighLevelClient client, String INDEX);
}
