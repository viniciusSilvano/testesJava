package br.com.testeJava.bo.sse_test.registers;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.ejb.Singleton;
import javax.enterprise.inject.Default;
import javax.ws.rs.sse.SseEventSink;

import br.com.testeJava.bo.sse_test.qualifier.ClientRegisterProcessesQualifier;

@Singleton
@ClientRegisterProcessesQualifier
@Default
public class ClientRegistryProcesses extends ClientRegistryBase {
	private final CopyOnWriteArrayList<SseEventSink> sinks = new CopyOnWriteArrayList<>();

	public CopyOnWriteArrayList<SseEventSink> getSinks() {
        return sinks;
    }
}
