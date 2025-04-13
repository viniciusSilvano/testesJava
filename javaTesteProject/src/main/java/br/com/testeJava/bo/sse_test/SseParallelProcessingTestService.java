package br.com.testeJava.bo.sse_test;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;

@Stateless
public class SseParallelProcessingTestService {
	
	@Asynchronous
	public void beginParallelProcessingTest(Sse sse) {
		 try {
	        for (int i = 0; i < 10; i++) {
	            // Create an event and send it
	            OutboundSseEvent.Builder eventBuilder = sse.newEventBuilder();
	            eventBuilder.name("message")
	                        .data(String.class, "Message Parallel Processing " + i);
	            OutboundSseEvent event = eventBuilder.build();
	            SseBroadcaster sseBroadcaster = sse.newBroadcaster();
	            sseBroadcaster.broadcast(event);
	            //eventSink.send(event);
            	Thread.sleep(1000); // Send an event every second
	        }
	        //eventSink.close(); // Close the connection after sending all events
	    } catch (InterruptedException e) {
	        //eventSink.close();
	    }
	}
}
