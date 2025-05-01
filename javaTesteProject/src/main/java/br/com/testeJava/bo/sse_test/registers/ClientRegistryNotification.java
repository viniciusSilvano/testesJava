package br.com.testeJava.bo.sse_test.registers;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.ejb.Singleton;
import javax.ws.rs.sse.SseEventSink;

import br.com.testeJava.bo.sse_test.qualifier.ClientRegisterProcessesNotificationQualifier;

@Singleton
@ClientRegisterProcessesNotificationQualifier
public class ClientRegistryNotification extends ClientRegistryBase {
	private final CopyOnWriteArrayList<SseEventSink> sinks = new CopyOnWriteArrayList<>();
	
    public CopyOnWriteArrayList<SseEventSink> getSinks() {
        return sinks;
    }
}
