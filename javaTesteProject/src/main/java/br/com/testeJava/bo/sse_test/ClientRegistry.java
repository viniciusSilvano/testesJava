package br.com.testeJava.bo.sse_test;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.ejb.Singleton;
import javax.ws.rs.sse.SseEventSink;

@Singleton
public class ClientRegistry {
	private final CopyOnWriteArrayList<SseEventSink> sinks = new CopyOnWriteArrayList<>();

    public void addSink(SseEventSink sink) {
        sinks.add(sink);
    }

    public void removeSink(SseEventSink sink) {
        sinks.remove(sink);
    }

    public CopyOnWriteArrayList<SseEventSink> getSinks() {
        return sinks;
    }
}
