package br.com.testeJava.bo.infinispan;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.inject.Inject;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

import br.com.testeJava.bo.infinispan.cache.log.LogCachesListerner;

public abstract class CacheManager {
	private EmbeddedCacheManager cacheManager;
	
	private Logger LOGGER = Logger.getLogger(CacheManager.class.getName());
	
	@Inject
	private LogCachesListerner logs;
	
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
	
	@Lock(LockType.READ)
	public <K, V> Cache<K, V> obterCache(String chave){
		Cache<K, V> cache = cacheManager.getCache(chave);
		initCacheLog(chave,cache);
		return cache;
	}
	
	private void initCacheLog(String chave, Cache<?,?> cache) {
		if(!cache.getListeners().contains(logs)) {
			cache.addListener(logs);
		}
	}
	
	protected abstract GlobalConfiguration getGlobalConfiguration();
	
	protected abstract Configuration getConfiguration();
}
