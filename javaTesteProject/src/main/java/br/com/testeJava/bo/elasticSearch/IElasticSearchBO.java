package br.com.testeJava.bo.elasticSearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import br.com.testeJava.entity.IEntidade;

public abstract class IElasticSearchBO{
	
	private RestClient getClient() {
		return RestClient.builder(new HttpHost("localhost",9200,"http")).build();
	}
	
	public void inserirIndiceDocumento(IEntidade obj) {
		RestClient client = getClient();
		try {
			inserirIndiceDocumentoAbstract(obj,client);
		}finally {
			try {
				client.close();
			} catch (IOException e) {
				System.out.println("Erro ao tentar fechar conexão com o ElasticSearch");
			}
		}
	}
	
	protected abstract void  inserirIndiceDocumentoAbstract(IEntidade obj, RestClient client);
}
