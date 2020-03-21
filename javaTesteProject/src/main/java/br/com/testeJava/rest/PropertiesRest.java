package br.com.testeJava.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.com.testeJava.bo.PropertiesService;

@Path(value = "properties")
public class PropertiesRest {
	
	@Inject
	PropertiesService propertiesService;
	
	@GET
	public ResponseBuilder getProperties() {
		return Response.ok().entity(propertiesService.getPropriedadeTeste());
	}
}
