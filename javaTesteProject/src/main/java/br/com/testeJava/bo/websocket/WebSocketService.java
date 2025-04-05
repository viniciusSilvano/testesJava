package br.com.testeJava.bo.websocket;

import java.io.IOException;
import java.util.Iterator;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.Session;

import org.apache.commons.lang3.NotImplementedException;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.dao.IDAO;
import br.com.testeJava.dto.WebSocketProgressDto;
import br.com.testeJava.entity.enuns.websocket.WebSocketSessionManagerKey;
import br.com.testeJava.util.JsonUtils;

@Stateless
public class WebSocketService extends BaseService {
	
	@Inject
	private WebSocketSessionManager webSocketSessionManager;

	@Override
	protected IDAO getDAO() {
		throw new NotImplementedException();
	}

	@Asynchronous
	public void iniciarProcessamento() {
		 
		for(int status = 0; status <= 100; status++) {
			if(status % 2 == 0) {
				try {
					this.iniciarContagem();
					Iterator<Session> it = this.webSocketSessionManager.getAllSessions().iterator();
					while(it.hasNext()) {
						Session session = it.next();
						if(session.isOpen()) {
							session.getBasicRemote()
							.sendText(JsonUtils.getInstance().parseObjectToStringJSON(new WebSocketProgressDto(WebSocketSessionManagerKey.JAVA_TEST_PROJECT_WEBSOCKET, status)));
						}
					}
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void iniciarContagem() {
		try {
			Thread.sleep((int)(1000 * (Math.random() * 2)));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
