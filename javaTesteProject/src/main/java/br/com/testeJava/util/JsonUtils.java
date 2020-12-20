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

public class JsonUtils {
	
	
	private static ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper()
				.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		return objectMapper;
	}
	
	
	public static JSONObject getNullableJsonObject(JSONObject target, String key) {
		if(Objects.nonNull(target) && !target.isEmpty()) {
			JSONObject targetedObject = target.getJSONObject(key);
			if(!targetedObject.isNull(key)) {
				return targetedObject;
			}
		}
		return null;
	}
	
	public static String parseObjectToStringJSON(Object obj) throws JsonProcessingException {
		String json = getObjectMapper().writeValueAsString(obj);
		System.out.println(json);
		return json;
	}
	
	public static Object parseStringJSONToObject(String json) throws JsonParseException, JsonMappingException, IOException {
		return getObjectMapper().readValue(json, Object.class);
	}
	
	
	public static String parseListToJsonString(List<?> lista) {
		try {
			return getObjectMapper().writeValueAsString(lista);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error to parse list to json string");
		}
	}
}
