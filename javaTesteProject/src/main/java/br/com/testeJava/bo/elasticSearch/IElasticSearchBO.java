package br.com.testeJava.bo.elasticSearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

public abstract class IElasticSearchBO{
	
	private RestClient getClient() {
		return RestClient.builder(new HttpHost("localhost",9200,"http")).build();
	}
	
	public void inserirIndiceDocumento(String url, Long id, Object obj) {
		RestClient client = getClient();
		try {
			inserirIndiceDocumentoAbstract(url,id,obj,client);
		}finally {
			try {
				client.close();
			} catch (IOException e) {
				System.out.println("Erro ao tentar fechar conexão com o ElasticSearch");
			}
		}
	}
	
	protected abstract void  inserirIndiceDocumentoAbstract(String url, Long id,Object obj, RestClient client);
}
