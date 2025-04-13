package br.com.testeJava.rest.websocketTest;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

import br.com.testeJava.bo.websocket.WebSocketService;
import br.com.testeJava.bo.websocket.WebSocketSessionManager;
import br.com.testeJava.rest.BaseRest;

@ServerEndpoint("/javaTestProjectWebSocket")
public class WebSocketRest extends BaseRest {

	@Inject
	private WebSocketSessionManager webSocketSessionManager;
	
	@Inject
	private WebSocketService service;;
	
	@Override
	protected WebSocketService getService() {
		return service;
	}
	
	@OnMessage
	public void onMessage(String message) {
		getService().iniciarProcessamento();
	}
	
	@OnOpen
	public void abrir(Session s) {
	     System.out.println("WEB SOCKET ABERTO ");
	     webSocketSessionManager.addSession(s);
	}

	@OnClose
	public void fechar(CloseReason c) {
	     System.out.println("WEB SOCKET FECHADO");
	}

	@OnError
	public void erro(Throwable t) {
	   System.out.println("ERRO NO WEBSOCKET");
	}
	
	public static class Configurator extends ServerEndpointConfig.Configurator {
        @Override
        public boolean checkOrigin(String originHeaderValue) {
            System.out.println("Origin: " + originHeaderValue);

            //Allow only specific origins (like Angular dev server)
            return "http://localhost:4200".equals(originHeaderValue);
        }
    }
	
}
