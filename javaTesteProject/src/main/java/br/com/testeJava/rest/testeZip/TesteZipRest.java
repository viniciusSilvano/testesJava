package br.com.testeJava.rest.testeZip;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.testeZip.TesteZipService;
import br.com.testeJava.rest.BaseRest;

@Path("teste-zip")
public class TesteZipRest extends BaseRest {
	@Inject
	TesteZipService testeZipService;
	
	@Override
	protected BaseService getService() {
		return testeZipService;
	}
	
	@GET
	@Path("/download")
	@Produces(MediaType.APPLICATION_JSON)
	public Response baixarZip() {
		try {
			return ((TesteZipService)getService()).executarTeste();
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@OPTIONS
	@Path("/download")
	public Response saveOrUpdateOptions() throws Exception {
		try {
			return Response.status(Status.ACCEPTED)
					.header( "Access-Control-Allow-Origin", "http://localhost:4200")
					.header( "Access-Control-Allow-Origin", "http://localhost:8180/*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}	
	}

}
