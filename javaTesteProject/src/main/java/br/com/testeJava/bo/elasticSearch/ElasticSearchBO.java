package br.com.testeJava.bo.elasticSearch;

import java.io.IOException;

import javax.ejb.Stateless;

import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.testeJava.util.JsonUtils;

@Stateless
public class ElasticSearchBO  extends IElasticSearchBO {

	@Override
	protected void inserirIndiceDocumentoAbstract(String url, Long id, Object obj, RestClient client) {
		Request request = new Request("PUT", String.format("/elastic/_doc/", id));
		try {
			request.setJsonEntity(JsonUtils.parseObjectToStringJSON(obj));
			Response response =  client.performRequest(request);
			System.out.println(response);
		} catch (JsonProcessingException e1) {
			System.out.println("Erro ao gerar JSON ao indexar objeto no ElasticSearch");
		}catch (IOException e) {
			System.out.println("Erro ao gerar indice do documento no ElasticSearch");
		}			
	}
}
