package br.com.testeJava.bo.sse_test;

import java.util.Optional;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

import org.hibernate.event.spi.EventSource;

@Stateless
public class SseTestService {
		
	@Inject
	private ClientRegistry clientRegistry;
	
	@Asynchronous
	public void initSseConnection(SseEventSink eventSink, EventSource eventSource,Sse sse, Optional<Integer> optTempoMili) {
		clientRegistry.addSink(eventSink);
	}
}
