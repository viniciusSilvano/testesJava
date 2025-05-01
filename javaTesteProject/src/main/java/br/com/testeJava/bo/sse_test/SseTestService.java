package br.com.testeJava.bo.sse_test;

import java.util.Optional;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

import org.hibernate.event.spi.EventSource;

import br.com.testeJava.bo.sse_test.qualifier.ClientRegisterProcessesQualifier;
import br.com.testeJava.bo.sse_test.registers.ClientRegistryBase;

@Stateless
public class SseTestService {
		
	@Inject
	@ClientRegisterProcessesQualifier
	private ClientRegistryBase clientRegistry;
	
	@Asynchronous
	public void initSseConnection(SseEventSink eventSink, EventSource eventSource,Sse sse, Optional<Integer> optTempoMili) {
		clientRegistry.addSink(eventSink);
	}
}
