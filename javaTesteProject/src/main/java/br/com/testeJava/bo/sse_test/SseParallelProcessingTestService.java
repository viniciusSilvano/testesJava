package br.com.testeJava.bo.sse_test;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;

@Stateless
public class SseParallelProcessingTestService {
	
	@Inject
	private ClientRegistry clientRegistry;
	
	@Asynchronous
	public void beginParallelProcessingTest(Sse sse) {
		 try {
	        for (int i = 0; i < 101; i++) {
	            OutboundSseEvent.Builder eventBuilder = sse.newEventBuilder();
	            eventBuilder.name("message")
	                        .data(String.class, "" + i);
	            OutboundSseEvent event = eventBuilder.build();
	            clientRegistry.getSinks().forEach(sink -> {
	                try {
	                    if (sink.isClosed()) {
	                    	clientRegistry.removeSink(sink);
	                    } else {
	                    	sink.send(event);
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    clientRegistry.removeSink(sink); // Remove faulty clients
	                }
	            });
            	Thread.sleep(1000); // Send an event every second
	        }
	    } catch (InterruptedException e) {
	    }
	}
}
