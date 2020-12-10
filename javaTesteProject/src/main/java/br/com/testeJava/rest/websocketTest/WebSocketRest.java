package br.com.testeJava.rest.websocketTest;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/javaTestProjectWebSocket")
public class WebSocketRest {

	@OnMessage
	public String onMessage(String message) {
		 return "teste websocket";
	}
	
	@OnOpen
	public void abrir(Session s) {
	     System.out.println("WEB SOCKET ABERTO ");
	}

	@OnClose
	public void fechar(CloseReason c) {
	     System.out.println("WEB SOCKET FECHADO");
	}

	@OnError
	public void erro(Throwable t) {
	   System.out.println("ERRO NO WEBSOCKET");
	}
	
}
