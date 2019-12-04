package com.customer.database.db;

import org.springframework.data.repository.CrudRepository;

import com.customer.database.business.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}
