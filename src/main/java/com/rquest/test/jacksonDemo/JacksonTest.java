package com.rquest.test.jacksonDemo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rquest.test.entity.User;

public class JacksonTest {

	public static void main(String[] args) {
		
		ObjectMapper obj = new ObjectMapper();
		User user = new User();
		user.setAge(30);
		user.setUserId("user123");
		Double[][] test = {{1.2,3.45,5.67890,3.4323},{0.321,4.345,543.32},{1.234,3.345,2.123}};
		user.setValue1(test);
			try {
				String writeValueAsString = obj.writeValueAsString(user);
				System.out.println(writeValueAsString);
				User readValue = obj.readValue(writeValueAsString,User.class);
				System.out.println(readValue.toString());
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
