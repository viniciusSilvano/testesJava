package br.com.testeJava.bo;

import javax.ejb.Stateless;

@Stateless
public class PropertiesService {
	public String getPropriedadeTeste() {
		return System.getProperties().toString();
	}
}
