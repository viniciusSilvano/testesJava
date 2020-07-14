package br.com.testeJava.bo.infinispan.cache.log;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryInvalidated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryVisited;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryInvalidatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryVisitedEvent;

@Singleton
@Listener
public class LogCachesListerner {
	private static final Logger LOGGER = Logger.getLogger(LogCachesListerner.class.getName());

	@PostConstruct
	public void init() {
		LOGGER.warning("************ LOG DE CACHE INICIALIZADO ************");
	}

	@CacheEntryCreated
	public void observeAdd(CacheEntryCreatedEvent<?, ?> event) {
		if (!event.isPre()) // So that message is only logged after operation succeeded
			LOGGER.info(mensagem(event, "ADICIONADO"));
	}

	@CacheEntryRemoved
	public void observeRemove(CacheEntryRemovedEvent<?, ?> event) {
		if (!event.isPre())
			LOGGER.info(mensagem(event, "REMOVIDO"));
	}

	@CacheEntryVisited
	public void observeVisit(CacheEntryVisitedEvent<?, ?> event) {
		if (!event.isPre())
			LOGGER.info(mensagem(event, "LIDO"));
	}

	@CacheEntryInvalidated
	public void observerInvalidate(CacheEntryInvalidatedEvent<?, ?> event) {
		if (!event.isPre())
			LOGGER.info(mensagem(event, "INVALIDADO"));
	}

	@CacheEntryModified
	public void observerModified(CacheEntryModifiedEvent<?, ?> event) {
		if (!event.isPre())
			LOGGER.info(mensagem(event, "MODIFICADO"));
	}

	private String mensagem(CacheEntryEvent<?, ?> event, String acao) {
		return String.format("Cache com a chave: %s %s no cache: %s", event.getKey(), acao,
				event.getCache().toString().toUpperCase());
	}
}
