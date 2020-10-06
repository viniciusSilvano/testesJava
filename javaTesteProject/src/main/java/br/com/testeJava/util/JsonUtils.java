package br.com.testeJava.util;

import java.util.Objects;

import org.json.JSONObject;

import protostream.com.google.gson.JsonObject;

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
}
