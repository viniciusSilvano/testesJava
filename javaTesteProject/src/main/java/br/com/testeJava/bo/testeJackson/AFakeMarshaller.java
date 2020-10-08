package br.com.testeJava.bo.testeJackson;

import java.io.IOException;

import javax.ejb.Stateless;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.testeJava.util.JsonUtils;

@Stateless
public class AFakeMarshaller{
	
	public String objectToObjectStream(Object obj) throws IOException {
		return JsonUtils.parseObjectToStringJSON(obj);
	}

	public Object objectFromObjectStream(String json) throws IOException, ClassNotFoundException {
		ObjectMapper objectMapper = new ObjectMapper();
		return JsonUtils.parseStringJSONToObject(json);
	}

}
