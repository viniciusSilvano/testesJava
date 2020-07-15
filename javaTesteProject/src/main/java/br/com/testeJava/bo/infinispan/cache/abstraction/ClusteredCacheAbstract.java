package br.com.testeJava.bo.infinispan.cache.abstraction;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ClusteredCacheAbstract<T>{
	protected String serializar(T objeto) throws JsonProcessingException{
		return getObjectMapper().writeValueAsString(objeto);
	}
	protected T deserializar(String objetoSerializado, Class<T> type) throws JsonParseException, JsonMappingException, IOException{
		return getObjectMapper().readValue(objetoSerializado, type);
	}
	private ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}
