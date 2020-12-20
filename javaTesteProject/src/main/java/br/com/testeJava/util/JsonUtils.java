package br.com.testeJava.util;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	
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
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(obj);
		System.out.println(json);
		return json;
	}
	
	public static Object parseStringJSONToObject(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, Object.class);
	}
	
	
	public static String parseListToJsonString(List<?> lista) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(lista);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error to parse list to json string");
		}
	}
}
