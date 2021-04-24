package br.com.testeJava.interfaces;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface IJsonMarshaller {
	public abstract ObjectMapper getObjectMapper();
	
	public JSONObject getNullableJsonObject(JSONObject target, String key);
	
	public abstract String parseObjectToStringJSON(Object obj) throws JsonProcessingException;
	
	public abstract Object parseStringJSONToObject(String json) throws JsonParseException, JsonMappingException, IOException;
	
	
	public abstract String parseListToJsonString(List<?> lista);
}
