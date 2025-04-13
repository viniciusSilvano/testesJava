package br.com.testeJava.rest.websocketTest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.com.testeJava.bo.websocket.WebSocketParallelProcessingTestService;

@Path("web_socket_parallel_test")
public class WebSocketParallelTestRest {
	
	@Inject
	private WebSocketParallelProcessingTestService webSocketParallelProcessingTestService;
	
	@GET
	public Response beginProcessing() {
		webSocketParallelProcessingTestService.iniciarProcessamento();
		return Response.ok().build();
		
	}
}
