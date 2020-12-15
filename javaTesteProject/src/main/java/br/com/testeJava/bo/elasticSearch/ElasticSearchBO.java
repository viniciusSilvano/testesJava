package br.com.testeJava.bo.elasticSearch;

import java.io.IOException;

import javax.ejb.Stateless;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import br.com.testeJava.bo.elasticSearch.qualifiers.ElasticSearchBOQualifier;
import br.com.testeJava.entity.IEntidade;
import br.com.testeJava.util.JsonUtils;

@Stateless
@ElasticSearchBOQualifier
public class ElasticSearchBO  extends IElasticSearchBO {

	@Override
	protected void inserirIndiceDocumentoAbstract(IEntidade obj, RestHighLevelClient client, final String INDEX) throws IOException {
		IndexRequest request = new IndexRequest(INDEX, "doc", obj.getId().toString());
		request.source(JsonUtils.parseObjectToStringJSON(obj), XContentType.JSON);
		IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
		System.out.println(indexResponse);
	}

	@Override
	protected void inserirIndiceDocumentoAbstract(IEntidade obj, RestHighLevelClient client, String INDEX_GENERICO,
			String INDEX) throws IOException {
		this.inserirIndiceDocumentoAbstract(obj, client, INDEX_GENERICO);
		this.inserirIndiceDocumentoAbstract(obj, client, INDEX);
	}
}
