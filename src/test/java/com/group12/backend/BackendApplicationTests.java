package com.group12.backend;

import com.group12.backend.model.Abbreviation;
import com.group12.backend.model.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
class BackendApplicationTests {

	@Test
	public void testAddAbbreviationSuccessShouldReturn201StatusCode() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8080/api/abbreviations";
		URI uri = new URI(baseUrl);
		Abbreviation abbreviation = new Abbreviation( "DVD", "Digital visual disk ofzo");

		HttpEntity<Abbreviation> request = new HttpEntity<>(abbreviation);

		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		//Verify request succeed
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void testAddDepartmentWithoutJWTShouldReturn403StatusCode() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8080/api/departments";
		URI uri = new URI(baseUrl);
		Department department = new Department("RandomAbbreviation", "ThisIsNotMeaningAnything");

		HttpEntity<Department> request = new HttpEntity<>(department);

		try
		{
			restTemplate.postForEntity(uri, request, String.class);
		}
		catch(HttpClientErrorException ex)
		{
			//Verify internal server error.
			Assertions.assertEquals(403, ex.getRawStatusCode());
		}
	}
}


