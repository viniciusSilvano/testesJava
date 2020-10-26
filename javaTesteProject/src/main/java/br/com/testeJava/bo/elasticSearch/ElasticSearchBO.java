package br.com.testeJava.bo.elasticSearch;

import java.io.IOException;

import javax.ejb.Stateless;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.testeJava.bo.elasticSearch.qualifiers.ElasticSearchBOQualifier;
import br.com.testeJava.entity.IEntidade;
import br.com.testeJava.util.JsonUtils;

@Stateless
@ElasticSearchBOQualifier
public class ElasticSearchBO  extends IElasticSearchBO {

	@Override
	protected void inserirIndiceDocumentoAbstract(IEntidade obj, RestHighLevelClient client, final String INDEX) {
		IndexRequest request = new IndexRequest(INDEX, "doc", obj.getId().toString());
		try {
			request.source(JsonUtils.parseObjectToStringJSON(obj), XContentType.JSON);
			IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
			System.out.println(indexResponse);
		} catch (JsonProcessingException e1) {
			System.out.println("Erro ao gerar JSON ao indexar objeto no ElasticSearch");
		}catch (IOException e) {
			System.out.println("Erro ao gerar indice do documento no ElasticSearch");
		}			
	}
}
