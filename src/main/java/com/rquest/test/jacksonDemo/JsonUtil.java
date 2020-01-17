package com.rquest.test.jacksonDemo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	/**
	 * @JsonProperty 用来改变某个成员属性
	 * @JsonIgnore 用来忽略某个属性
	 * @JsonIgnoreProperties(ignoreUnknown=true) 忽略掉Pojo中没有配置的属性
	 * TypeReference可以用来应对复杂的范型！
	 * List<Bean> beanList = mapper.readValue(jsonString, new TypeReference<List<Bean>>() {});  
	 */
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static ObjectMapper objectMapper = null;

	private JsonUtil() {
	};

	public static ObjectMapper getInstance() {
		if (objectMapper == null) {
			synchronized (JsonUtil.class) {
				if (objectMapper == null) {
					objectMapper = new ObjectMapper();
					 //设置输出时包含属性的风格  
				    //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性  
					objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
					objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);  
				}
			}
		}
		return objectMapper;
	}

	/*
	 * static {
	 * objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
	 * true); objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,
	 * true);
	 * objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,
	 * true); }
	 */
	/**
	 * 将json string反序列化成对象
	 * 
	 * @param json
	 * @param valueType
	 * @return
	 */
	public static <T> T readValue(String json, Class<T> valueType) {
		try {
			getInstance();
			return objectMapper.readValue(json, valueType);
		} catch (JsonParseException e) {
			logger.error("readValue(String, Class<T>)", e);
		} catch (JsonMappingException e) {
			logger.error("readValue(String, Class<T>)", e);
		} catch (IOException e) {
			logger.error("readValue(String, Class<T>)", e);
		}
		return null;
	}

	/**
	 * 反序列化字符串成为对象比如Map<String Object> List<自定义对象> 等；
	 * new com.fasterxml.jackson.core.type.TypeReference<List<Map<String, Object>>>() {}
	 * @param json
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T getObjectFromJson(String json, TypeReference<T> valueTypeRef) {
		try {
			getInstance();
			return objectMapper.readValue(json, valueTypeRef);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将对象序列化
	 * 
	 * @param obj
	 * @return
	 */
	public static String writeValueAsString(Object obj) {
		try {
			getInstance();
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("序列化对象是出现错误");
			e.printStackTrace();
		}
		return null;
	}
	
	public static JsonNode getValueByKey(String content, String key) {
		JsonNode readTree;
		try {
			getInstance();
			readTree = objectMapper.readTree(content);
			JsonNode jsonNode = readTree.findValue(key);
			return jsonNode;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
