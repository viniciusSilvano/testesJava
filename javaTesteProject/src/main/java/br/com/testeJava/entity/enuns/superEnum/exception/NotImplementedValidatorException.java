package br.com.testeJava.entity.enuns.superEnum.exception;

public class NotImplementedValidatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		
		return String.format("%s, %s","Validador de usuário não implementado", super.getMessage());
	}

}
