/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infsci2560;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author kolobj
 */
public class LoginHelper {
        public static ResponseEntity<String> login(TestRestTemplate template, String route, String userName, String password) {
		HttpHeaders headers = getHeaders(template, route);
		headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.set("username", userName);
		form.set("password", password);		
		ResponseEntity<String> entity = template.exchange(route,
				HttpMethod.POST,
				new HttpEntity<MultiValueMap<String, String>>(form, headers),
				String.class);
                return entity;

	}

	private static HttpHeaders getHeaders(TestRestTemplate template, String route) {
                // todo : write getHeaders test
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<String> page = template.getForEntity(route, String.class);
		assertThat(page.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		String cookie = page.getHeaders().getFirst("Set-Cookie");
		headers.set("Cookie", cookie);

		Pattern pattern = Pattern.compile("(?s).*name=\"_csrf\".*?value=\"([^\"]+).*");
		Matcher matcher = pattern.matcher(page.getBody());
		assertThat(matcher.matches()).as(page.getBody()).isTrue();
		headers.set("X-CSRF-TOKEN", matcher.group(1));
		return headers;
	}
    
}
