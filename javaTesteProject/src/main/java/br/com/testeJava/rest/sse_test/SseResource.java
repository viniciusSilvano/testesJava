package br.com.testeJava.rest.sse_test;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

import org.hibernate.event.spi.EventSource;

import br.com.testeJava.bo.sse_test.SseTestService;

@Path("/sse_test")
public class SseResource {
	
	@Inject
	private SseTestService service;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void streamEvents(
    		@Context SseEventSink eventSink, 
    		@Context EventSource eventSource,
    		@Context Sse sse,
    		@QueryParam("tempoMili") Integer tempoMili) {
    	service.beginTest(eventSink,eventSource,sse,Optional.ofNullable(tempoMili));
    }
}
