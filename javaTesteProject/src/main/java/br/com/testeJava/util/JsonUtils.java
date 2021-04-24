package br.com.testeJava.util;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.testeJava.interfaces.IJsonMarshaller;

public class JsonUtils implements IJsonMarshaller{
	
	private static JsonUtils instance = new JsonUtils();
	private JsonUtils() {}
	
	public static JsonUtils getInstance() {
		if(Objects.nonNull(instance)) {
			return instance;
		}
		return new JsonUtils();
	}
	
	@Override
	public ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper()
				.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		return objectMapper;
	}
	
	@Override
	public JSONObject getNullableJsonObject(JSONObject target, String key) {
		if(Objects.nonNull(target) && !target.isEmpty()) {
			JSONObject targetedObject = target.getJSONObject(key);
			if(!targetedObject.isNull(key)) {
				return targetedObject;
			}
		}
		return null;
	}
	
	@Override
	public String parseObjectToStringJSON(Object obj) throws JsonProcessingException {
		String json = getObjectMapper().writeValueAsString(obj);
		System.out.println(json);
		return json;
	}
	
	@Override
	public Object parseStringJSONToObject(String json) throws JsonParseException, JsonMappingException, IOException {
		return getObjectMapper().readValue(json, Object.class);
	}
	
	@Override
	public String parseListToJsonString(List<?> lista) {
		try {
			return getObjectMapper().writeValueAsString(lista);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error to parse list to json string");
		}
	}
}
