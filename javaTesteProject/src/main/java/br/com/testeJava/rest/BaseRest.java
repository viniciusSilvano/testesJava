package br.com.testeJava.rest;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseRest {
	
	public String parseListToJsonString(List<?> lista) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(lista);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error to parse list to json string");
		}
	}
}
