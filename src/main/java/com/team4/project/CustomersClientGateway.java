package com.team4.project;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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


@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomersClientGateway {

	@GetMapping
	public List<Customer> getAll(){
		
		RestTemplate rt = new RestTemplate();
		List customers = rt.getForObject(
				"http://localhost:9001/gateway/customers", 
				List.class);
		
		System.out.println(customers);
		
		return customers;
	}
	
	@GetMapping("/{id}")
	public Customer getById(@PathVariable String id){
		
		RestTemplate rt = new RestTemplate();
		Customer customer = rt.getForObject(
				"http://localhost:9001/gateway/customers/" + id, Customer.class);
		
		System.out.println(customer);
		
		return customer;
	}
	
	@PostMapping
	public void createCustomer(@RequestBody Customer c) {
		RestTemplate rt = new RestTemplate();
		rt.postForObject("http://localhost:9001/gateway/customers", c, Customer.class);
	}
	
	@PutMapping("/{id}")
	public void updateCustomerById(@RequestBody Customer c, @PathVariable String id) {
		RestTemplate rt = new RestTemplate();
		rt.put("http://localhost:9001/gateway/customers/" + id, c);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable String id){
		
		RestTemplate rt = new RestTemplate();
		rt.delete("http://localhost:9001/gateway/customers/" + id);
		
	}
	
	
	// GET a customer by their name
	@GetMapping("/byname/{username}")
	public Customer getCustomerByName(@PathVariable String username) {
				
		RestTemplate rt = new RestTemplate();
		return rt.getForObject("http://localhost:9001/gateway/customers/byname/" + username, Customer.class);
		
	}
		

	@PostMapping(path = "/byname")
	public Customer getCustomerByNamePost(@RequestBody String customerName){
		
		RestTemplate rt = new RestTemplate();
		return rt.postForObject("http://localhost:9001/gateway/customers/byname", customerName, Customer.class);
		
	}
}
