package br.com.testeJava.bo.infinispan.cache;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.testeJava.bo.infinispan.ClusteredCache;
import br.com.testeJava.dto.LivroDto;

@Stateless
public class CacheLivros {
	@Inject
	private ClusteredCache cacheManager;
	
	private ObjectMapper objectMapper = new ObjectMapper();
		
	private final static String CHAVE = CacheLivros.class.getName();

	public void inserir(List<LivroDto> entity){
		String json;
		try {
			json = objectMapper.writeValueAsString(entity);
			cacheManager.obterCache(CHAVE).put(1l, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public List<LivroDto> recuperar(Long id){
		List<LivroDto> livro = null;
		if(Objects.nonNull(cacheManager.obterCache(CHAVE).get(id))) {
			try {
				livro = objectMapper.readValue(((String) cacheManager.obterCache(CHAVE).get(id)), new TypeReference<List<LivroDto>>(){});
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return livro;
	}
}
