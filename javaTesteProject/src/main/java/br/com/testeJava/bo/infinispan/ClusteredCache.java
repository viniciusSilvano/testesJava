package br.com.testeJava.bo.infinispan;

import javax.ejb.Startup;
import javax.ejb.Stateless;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;

@Startup
@Stateless
public class ClusteredCache extends CacheManager {

	@Override
	protected GlobalConfiguration getGlobalConfiguration() {
		return GlobalConfigurationBuilder
				.defaultClusteredBuilder()
				.globalJmxStatistics()
				.allowDuplicateDomains(true)
				.enable().build();				
	}

	@Override
	protected Configuration getConfiguration() {
		return new ConfigurationBuilder().clustering().cacheMode(CacheMode.REPL_ASYNC).build();
	}
	
}
