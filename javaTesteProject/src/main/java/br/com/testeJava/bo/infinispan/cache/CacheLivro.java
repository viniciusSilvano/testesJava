package br.com.testeJava.bo.infinispan.cache;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Stateless;
import javax.inject.Inject;

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
	
	@PostConstruct
	public void init() {
		cacheManager.obterCache(CHAVE).addListener(logs);
	}
	
	private final static String CHAVE = CacheImpl.class.getName();

	public void inserir(LivroDto entity) {
		cacheManager.obterCache(CHAVE).put(entity.getId(), entity);
	}

	public LivroDto recuperar(Long id) {
		return (LivroDto) cacheManager.obterCache(CHAVE).get(id);
	}

	public LivroDto atualizar(LivroDto entity) {
		inserir(entity);
		return recuperar(entity.getId());
	}

	public void deletar(LivroDto entity) {
		cacheManager.obterCache(CHAVE).remove(entity.getId());
	}
}
