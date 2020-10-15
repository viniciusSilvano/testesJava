package br.com.testeJava.exception;

import javax.ws.rs.core.Response.ResponseBuilder;

public class RestException extends Exception {
	
	private ResponseBuilder response;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4976187124349379369L;

}
