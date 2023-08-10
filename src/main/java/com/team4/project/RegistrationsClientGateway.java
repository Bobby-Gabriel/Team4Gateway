package com.team4.project;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@CrossOrigin
@RestController
@RequestMapping("/api/registrations")
public class RegistrationsClientGateway {
	
	private static final RestTemplate rt = new RestTemplate();
	
	@GetMapping
	public List getAll() {

		return rt.getForObject("http://registration:9002/gateway/registrations", List.class);
	}
	
	@GetMapping("/{id}")
	public Registration getById(@PathVariable String id) {

		return rt.getForObject("http://registration:9002/gateway/registrations/" + id, Registration.class);
	}
	
	//post a new Registration
	@PostMapping
	public ResponseEntity<?> createRegistration(@RequestBody Registration r) {

		ResponseEntity<Registration> response = rt.postForEntity("http://registration:9002/gateway/registrations", r, Registration.class);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getBody().getId()).toUri();

		return ResponseEntity.created(location).build();
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
