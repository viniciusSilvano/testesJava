package br.com.testeJava.dto;

import java.io.Serializable;

public class Transportador implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3519566574547839776L;
	private Object objeto;
	
	public Transportador(Object objeto) {
		this.objeto = objeto;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}	
}
