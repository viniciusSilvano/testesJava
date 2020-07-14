package br.com.testeJava.bo.infinispan.cache;

import javax.ejb.DependsOn;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.testeJava.bo.infinispan.ClusteredCache;

@Stateless
@DependsOn(value = {"ClusteredCache"})
public class CacheImpl<T extends Cacheavel> implements ICache<T> {
	
	@Inject
	private ClusteredCache cacheManager;
	
	private final static String CHAVE = CacheImpl.class.getName();

	@Override
	public void inserir(T entity) {
		cacheManager.obterCache(CHAVE).put(entity.getId(), entity);
	}

	@Override
	public T recuperar(Long id) {
		return (T) cacheManager.obterCache(CHAVE).get(id);
	}

	@Override
	public T atualizar(T entity) {
		inserir(entity);
		return recuperar(entity.getId());
	}

	@Override
	public void deletar(T entity) {
		cacheManager.obterCache(CHAVE).remove(entity.getId());
	}
}

