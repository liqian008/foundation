package com.bruce.foundation.macp.utils;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 将date类型转为long型的timestamp
 * @author liqian
 *
 */
public class JsonDateTypeAdapter implements JsonSerializer<Date>,
		JsonDeserializer<Date> {

	public JsonElement serialize(Date src, Type typeOfSrc,
			JsonSerializationContext context) {
		return new JsonPrimitive(src.getTime());
	};

	public Date deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		if (!(json instanceof JsonPrimitive)) {
			throw new JsonParseException("The date should be a long value");
		}
		Date date = new Date(json.getAsLong());
		return new Date(date.getTime());
	}

}