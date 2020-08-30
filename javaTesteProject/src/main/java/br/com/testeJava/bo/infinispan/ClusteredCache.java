package br.com.testeJava.bo.infinispan;

import java.util.concurrent.TimeUnit;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.transaction.LockingMode;
import org.infinispan.transaction.TransactionMode;
import org.infinispan.transaction.lookup.JBossStandaloneJTAManagerLookup;
import org.infinispan.util.concurrent.IsolationLevel;

//@Startup
@Singleton
public class ClusteredCache extends CacheManager {

	@Override
	protected GlobalConfiguration getGlobalConfiguration() {
		return GlobalConfigurationBuilder
				.defaultClusteredBuilder().build();				
	}

	@Override
	protected Configuration getConfiguration() {
		return new ConfigurationBuilder().clustering().cacheMode(CacheMode.REPL_SYNC)
				.locking()
					.lockAcquisitionTimeout(40,TimeUnit.MINUTES)
					.isolationLevel(IsolationLevel.REPEATABLE_READ)
				.transaction()
					.transactionMode(TransactionMode.TRANSACTIONAL)
					.lockingMode(LockingMode.OPTIMISTIC)
					.transactionManagerLookup(new JBossStandaloneJTAManagerLookup())
				.build();
	}
	
}
