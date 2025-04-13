package br.com.testeJava.rest.sse_test;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.Sse;

import br.com.testeJava.bo.sse_test.SseParallelProcessingTestService;

@Path("/sse_parallel_processing_test")
public class SseParallelProcessingTestResource {

	@Inject
	private SseParallelProcessingTestService service;

	
    @GET
    public Response beginProcessing(@Context Sse sse) {
    	service.beginParallelProcessingTest(sse);
    	return Response.ok().build();
    }
}
