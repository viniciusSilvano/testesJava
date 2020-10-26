package br.com.testeJava.exception;

public class JavaTesteProjectException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4976187124349379369L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
		
}
