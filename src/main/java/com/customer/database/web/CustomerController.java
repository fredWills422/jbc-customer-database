package com.customer.database.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.customer.database.business.Customer;
import com.customer.database.db.CustomerRepository;

@CrossOrigin
@RestController
@RequestMapping(path="/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepo;
	
	public JsonResponse save(Customer customer) {
		try {
			return JsonResponse.getInstance(customerRepo.save(customer));
		}catch(IllegalArgumentException ex) {
			return JsonResponse.getInstance("Parameter customer cannot be null");
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
	@GetMapping("/")
	public Iterable<Customer> getAll(){
		return customerRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public @ResponseBody JsonResponse getByPK(@PathVariable Integer id){
		try {
			if(id ==null) return JsonResponse.getInstance("Parameter id cannot be null");
			Optional<Customer> c = customerRepo.findById(id);
			if(!c.isPresent()) {
				return JsonResponse.getInstance("Customer not found");
			}return JsonResponse.getInstance(c.get());
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
	@PostMapping
	public @ResponseBody JsonResponse insert(@RequestBody Customer customer) {
		try {
			return save(customer);
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex);
		}
	}
	
	@PutMapping("/{id}")
	public @ResponseBody JsonResponse update(@RequestBody Customer customer, @PathVariable Integer id) {
		try {
			if(id != customer.getId()) {
				return JsonResponse.getInstance("Parameter id doesn't match");
			}
			return save(customer);
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex);
		}
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody JsonResponse delete(@PathVariable Integer id) {
		try {
			if(id==null) return JsonResponse.getInstance("Parameter id can't be null");
			Optional<Customer> c = customerRepo.findById(id);
			if(!c.isPresent()) {
				return JsonResponse.getInstance("Customer not found");
			}
			customerRepo.deleteById(c.get().getId());
			return JsonResponse.getInstance(c.get());
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
}
