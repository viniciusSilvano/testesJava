package br.com.testeJava.bo.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.websocket.Session;

import br.com.testeJava.entity.enuns.websocket.WebSocketSessionManagerKey;

@Singleton
public class WebSocketSessionManager {
	private Map<WebSocketSessionManagerKey,Session> sessions = new HashMap<WebSocketSessionManagerKey, Session>();
	private List<Session> sessionsList = new ArrayList<Session>();
	
	public void addSession(WebSocketSessionManagerKey key, Session session) {
		sessions.put(key,session);
	}
	
	public void addSession(Session session) {
		this.sessionsList.add(session);
	}
	
	public List<Session> getAllSessions() {
		return new ArrayList(this.sessionsList);
	}
	
	public Session getSession(WebSocketSessionManagerKey key) {
		return sessions.get(key);
	}
	
	@Schedule(minute = "*/5")
	private void checkAllSessions(){
		System.out.println(" Checando sessões do WebSocket ");
		int sessionsQuantity = sessions.values().size();
		sessions.values().removeIf(s -> !s.isOpen());
		System.out.println(" Quantidade de sessões removidas" + (sessions.values().size() - sessionsQuantity));
	}
	
	
}
