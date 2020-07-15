package br.com.testeJava.bo.infinispan.cache;

import java.io.IOException;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.testeJava.bo.infinispan.ClusteredCache;
import br.com.testeJava.bo.infinispan.cache.abstraction.ClusteredCacheAbstract;
import br.com.testeJava.dto.LivroDto;

@Stateless
public class CacheLivro extends ClusteredCacheAbstract<LivroDto> {
	
	@Inject
	private ClusteredCache cacheManager;
		
	private final static String CHAVE = CacheLivro.class.getName();

	public void inserir(LivroDto entity){
		String json;
		try {
			json = this.serializar(entity);
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
				livro = this.deserializar((String) cacheManager.obterCache(CHAVE).get(id), LivroDto.class);
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
