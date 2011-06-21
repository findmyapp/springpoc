package com.accenture.findmyapp;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.accenture.findmyapp.entity.Person;

public class PersonRestTest {

	@Test
	public void noContentDoesNotThrowException(){
		RestTemplate restTemplate = new RestTemplate();
		Person person = restTemplate.getForObject("http://127.0.0.1:8080/findmyapp/name/{name}", Person.class, "Bertil");
		assertThat(person, nullValue());
	}
	
	@Test
	private void correctContentIsReturnedOnValidParameter() {
		RestTemplate restTemplate = new RestTemplate();
		Person person = restTemplate.getForObject("http://127.0.0.1:8080/findmyapp/name/{name}", Person.class, "Kaare");
	}
	
}
