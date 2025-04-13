package br.com.testeJava.bo.sse_test;

import java.util.Optional;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

import org.hibernate.event.spi.EventSource;

@Stateless
public class SseTestService {
	
	
	@Asynchronous
	public void beginTest(SseEventSink eventSink, EventSource eventSource,Sse sse, Optional<Integer> optTempoMili) {
		 try {
		        for (int i = 0; i < 10; i++) {
		            // Create an event and send it
		            OutboundSseEvent.Builder eventBuilder = sse.newEventBuilder();
		            eventBuilder.name("message")
		                        .data(String.class, "Message " + i);
		            OutboundSseEvent event = eventBuilder.build();
		            /*SseBroadcaster sseBroadcaster = sse.newBroadcaster();
		            sseBroadcaster.broadcast(event);*/
		            eventSink.send(event);
		            if(optTempoMili.isPresent()) {
		            	Thread.sleep(optTempoMili.get());
		            }else {
		            	Thread.sleep(1000); // Send an event every second
		            }
		        }
		        eventSink.close(); // Close the connection after sending all events
		    } catch (InterruptedException e) {
		        eventSink.close();
		    }
	}
}
