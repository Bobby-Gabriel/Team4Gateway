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
@RequestMapping("/api/events")
public class EventsClientGateway {

	private static final RestTemplate rt = new RestTemplate();

	//get all
	@GetMapping
	public List getAll(){

		return rt.getForObject("http://localhost:9000/gateway/events", List.class);
	}
	
	//get by id
	@GetMapping("/{id}")
	public Event getById(@PathVariable String id){

		return rt.getForObject("http://localhost:9000/gateway/events/" + id, Event.class);
	}
	
	//post a new event
	@PostMapping
	public ResponseEntity<?> createEvent(@RequestBody Event e) {
		
    ResponseEntity<Event> response = rt.postForEntity("http://localhost:9000/gateway/events", e, Event.class);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getBody().getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	//put by id
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEventById(@RequestBody Event e, @PathVariable String id) {

		rt.put("http://localhost:9000/gateway/events/" + id, e);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(e.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id){
		
		rt.delete("http://localhost:9000/gateway/events/" + id);

		return ResponseEntity.ok().build();
	}
}
