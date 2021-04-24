package br.com.testeJava.rest;


import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.interfaces.IJsonMarshaller;
import br.com.testeJava.util.JsonUtils;

public abstract class BaseRest implements IJsonMarshaller{
	
	protected abstract BaseService getService();
	
	@Override
	public ObjectMapper getObjectMapper() {
		return JsonUtils.getInstance().getObjectMapper();
	}
	
	@Override
	public JSONObject getNullableJsonObject(JSONObject target, String key) {
		return JsonUtils.getInstance().getNullableJsonObject(target, key);
	}
	
	@Override
	public String parseObjectToStringJSON(Object obj) throws JsonProcessingException {
		return JsonUtils.getInstance().parseObjectToStringJSON(obj);
	}
	
	@Override
	public Object parseStringJSONToObject(String json) throws JsonParseException, JsonMappingException, IOException {
		return JsonUtils.getInstance().parseStringJSONToObject(json);
	}
	
	@Override
	public String parseListToJsonString(List<?> lista) {
		return JsonUtils.getInstance().parseListToJsonString(lista);
	}
}
