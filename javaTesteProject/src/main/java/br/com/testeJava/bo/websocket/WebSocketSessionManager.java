package br.com.testeJava.bo.websocket;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Schedule;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

import br.com.testeJava.entity.enuns.websocket.WebSocketSessionManagerKey;

@Stateful
@ApplicationScoped
public class WebSocketSessionManager {
	private Map<WebSocketSessionManagerKey,Session> sessions = new HashMap<WebSocketSessionManagerKey, Session>();
	
	public void addSession(WebSocketSessionManagerKey key, Session session) {
		sessions.put(key,session);
	}
	
	public Session getSession(WebSocketSessionManagerKey key) {
		return sessions.get(key);
	}
	
	@Schedule(minute = "*/10")
	private void checkAllSessions(){
		System.out.println(" Checando sessões do WebSocket ");
		int sessionsQuantity = sessions.values().size();
		sessions.values().removeIf(s -> !s.isOpen());
		System.out.println(" Quantidade de sessões removidas" + (sessions.values().size() - sessionsQuantity));
	}
	
	
}
