package br.com.testeJava.bo.sse_test.registers;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.sse.SseEventSink;

public abstract class ClientRegistryBase {
	protected abstract CopyOnWriteArrayList<SseEventSink> getSinks();
	
	public void addSink(SseEventSink sink) {
		getSinks().add(sink);
    }

    public void removeSink(SseEventSink sink) {
    	getSinks().remove(sink);
    }
}
