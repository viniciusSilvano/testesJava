package br.com.testeJava.bo.infinispan.cache;

import java.io.IOException;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.testeJava.bo.infinispan.ClusteredCache;
import br.com.testeJava.bo.infinispan.cache.log.LogCachesListerner;
import br.com.testeJava.dto.LivroDto;

@Stateless
@DependsOn(value = {"ClusteredCache"})
public class CacheLivro {
	
	@Inject
	private ClusteredCache cacheManager;
	
	@Inject
	private LogCachesListerner logs;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@PostConstruct
	public void init() {
		cacheManager.obterCache(CHAVE).addListener(logs);
	}
	
	private final static String CHAVE = CacheImpl.class.getName();

	public void inserir(LivroDto entity){
		String json;
		try {
			json = objectMapper.writeValueAsString(entity);
			cacheManager.obterCache(CHAVE).put(entity.getId(), json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public LivroDto recuperar(Long id){
		LivroDto livro = null;
		if(Objects.nonNull(cacheManager.obterCache(CHAVE).get(id))) {
			try {
				livro = objectMapper.readValue(((String) cacheManager.obterCache(CHAVE).get(id)), LivroDto.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return livro;
	}

	public LivroDto atualizar(LivroDto entity){
		inserir(entity);
		return recuperar(entity.getId());
	}

	public void deletar(LivroDto entity) {
		cacheManager.obterCache(CHAVE).remove(entity.getId());
	}
}
