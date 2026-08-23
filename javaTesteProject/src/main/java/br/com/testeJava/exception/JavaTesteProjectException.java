package br.com.testeJava.exception;

import javax.ws.rs.core.Response;

public class JavaTesteProjectException extends Exception {


	private Response.Status status;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4976187124349379369L;
	
	private String message;

	public JavaTesteProjectException() {
		super();
	}

	public JavaTesteProjectException(String message, Response.Status status) {
		super(message);
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Response.Status getStatus() {
		return status;
	}

	public void setStatus(Response.Status status) {
		this.status = status;
	}
}
