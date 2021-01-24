package com.luv2code.jackson.json.demo;

import java.io.File;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {
		
		try {
			
			// crate object mapper
			ObjectMapper objectMapper=new ObjectMapper();
			
			// read JSON file and map/convert to JAVA POJO:
			// data/sample-lite.json
			Student student=objectMapper.readValue(new File("data/sample-full.json"), Student.class);
			
			// print first name and last name		
			System.out.println("firstName : "+student.getFirstName());
			System.out.println("lastName :"+student.getLastName());
			
			// print out the address city and stree
			Address address=student.getAddress();
			
			System.out.println("city : "+address.getCity());
			System.out.println("street : "+address.getStreet());
			
			// print out languages
			//System.out.println("Languages : "+Arrays.toString(student.getLanguages()));
			for (String languages : student.getLanguages()) {
				System.out.println(languages);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
