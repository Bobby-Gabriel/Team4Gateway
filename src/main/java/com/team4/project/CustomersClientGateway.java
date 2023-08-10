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
@RequestMapping("/api/customers")
public class CustomersClientGateway {

	private static final RestTemplate rt = new RestTemplate();

	@GetMapping
	public List getAll(){

		return rt.getForObject("http://localhost:9001/gateway/customers", List.class);
	}
	
	@GetMapping("/{id}")
	public Customer getById(@PathVariable String id){

		return rt.getForObject("http://localhost:9001/gateway/customers/" + id, Customer.class);
	}
	
	//post a new Customer
	@PostMapping
	public ResponseEntity<?> createCustomer(@RequestBody Customer c) {

		ResponseEntity<Customer> response = rt.postForEntity("http://localhost:9001/gateway/customers", c, Customer.class);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getBody().getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public void updateCustomerById(@RequestBody Customer c, @PathVariable String id) {

		rt.put("http://localhost:9001/gateway/customers/" + id, c);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable String id){
		
		rt.delete("http://localhost:9001/gateway/customers/" + id);
	}
	
	
	// GET a customer by their name
	@GetMapping("/byname/{username}")
	public Customer getCustomerByName(@PathVariable String username) {
				
		return rt.getForObject("http://customer:9001/gateway/customers/byname/" + username, Customer.class);
	}
		

	@PostMapping(path = "/byname")
	public Customer getCustomerByNamePost(@RequestBody String customerName){
		
		return rt.postForObject("http://customer:9001/gateway/customers/byname", customerName, Customer.class);
	}
}
