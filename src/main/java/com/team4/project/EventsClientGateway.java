package com.team4.project;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

	//get all
	@GetMapping
	public List<Event> getAll(){
		
		RestTemplate rt = new RestTemplate();
		List events = rt.getForObject(
				"http://event:9000/gateway/events", 
				List.class);
		
		System.out.println(events);
		
		return events;
	}
	
	//get by id
	@GetMapping("/{id}")
	public Event getById(@PathVariable String id){
		
		RestTemplate rt = new RestTemplate();
		Event event = rt.getForObject(
				"http://event:9000/gateway/events/" + id, Event.class);
		
		System.out.println(event);
		
		return event;
	}
	
	//post a new event
	@PostMapping
	public ResponseEntity<?> createEvent(@RequestBody Event e) {
		RestTemplate rt = new RestTemplate();

		ResponseEntity<Event> response = rt.postForEntity("http://event:9000/gateway/events", e, Event.class);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getBody().getId()).toUri();
		ResponseEntity<?> responseEntity = ResponseEntity.created(location).build();
		return responseEntity;
	}
	
	//put by id
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEventById(@RequestBody Event e, @PathVariable String id) {
		RestTemplate rt = new RestTemplate();
		rt.put("http://event:9000/gateway/events/" + id, e);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(e.getId()).toUri();
		ResponseEntity<?> responseEntity = ResponseEntity.created(location).build();
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id){
		
		RestTemplate rt = new RestTemplate();
		rt.delete("http://event:9000/gateway/events/" + id);
		return ResponseEntity.ok().build();
		
	}
}
