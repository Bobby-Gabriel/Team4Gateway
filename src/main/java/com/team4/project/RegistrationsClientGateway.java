package com.team4.project;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@CrossOrigin
@RestController
@RequestMapping("/api/registrations")
public class RegistrationsClientGateway {
	
	private static RestTemplate rt = new RestTemplate();
	
	@GetMapping
	public List<Registration> getAll() {
		
		List registrations = rt.getForObject("http://registration:9002/gateway/registrations", List.class);
		System.out.println(registrations);
		return registrations;
	}
	
	@GetMapping("/{id}")
	public Registration getById(@PathVariable String id) {
		
		Registration registration = rt.getForObject("http://registration:9002/gateway/registrations/" + id, Registration.class);
		System.out.println(registration);
		return registration;
		
	}
	
	//post a new Registration
	@PostMapping
	public ResponseEntity<?> createRegistration(@RequestBody Registration r) {
		RestTemplate rt = new RestTemplate();

		ResponseEntity<Registration> response = rt.postForEntity("http://registration:9002/gateway/registrations", r, Registration.class);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getBody().getId()).toUri();
		ResponseEntity<?> responseEntity = ResponseEntity.created(location).build();
		return responseEntity;
	}
	
	@PutMapping("/{id}")
	public void updateRegistrationById(@RequestBody Registration r, @PathVariable String id) {
		
		rt.put("http://registration:9002/gateway/registrations/" + id, r);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable String id) {
		
		rt.delete("http://registration:9002/gateway/registrations/" + id);
	}

}
