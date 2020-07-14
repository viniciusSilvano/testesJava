package br.com.testeJava.bo.testeJackson;

import java.io.IOException;

import javax.ejb.Stateless;

import com.fasterxml.jackson.databind.ObjectMapper;

@Stateless
public class AFakeMarshaller{
	

	private ObjectMapper objectMapper = new ObjectMapper();
	
	public String objectToObjectStream(Object obj) throws IOException {
		String json = objectMapper.writeValueAsString(obj);
		System.out.println(json);
		return json;
	}

	public Object objectFromObjectStream(String json) throws IOException, ClassNotFoundException {
		return objectMapper.readValue(json, Object.class);
	}

}
