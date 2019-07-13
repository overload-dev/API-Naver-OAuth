package com.overload.framework.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


//String -> Map casting
public class Util {
	public static Map castMap(String data) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		Map map = new HashMap();
		map = mapper.readValue(data, new TypeReference<Map>(){});
		return map;
	}
}
