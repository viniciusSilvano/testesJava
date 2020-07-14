package br.com.testeJava.bo.infinispan;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public abstract class CacheManager {
	private EmbeddedCacheManager cacheManager;
	
	private Logger LOGGER = Logger.getLogger(CacheManager.class.getName());
	
	@PostConstruct
	public void init() {
		LOGGER.info("************** INICIANDO CACHE MANAGER **************");
		cacheManager = new DefaultCacheManager(getGlobalConfiguration(), getConfiguration());
	}
	
	@PreDestroy
	public void finalizar() {
		LOGGER.info("************** FINALIZANDO CACHE MANAGER **************");
		cacheManager.stop();
	}
	
	public void limpar(String chave) {
		cacheManager.removeCache(chave);
	}
	
	public <K, V> Cache<K, V> obterCache(String chave){
		return cacheManager.getCache(chave);
	}
	
	protected abstract GlobalConfiguration getGlobalConfiguration();
	
	protected abstract Configuration getConfiguration();
}
