package com.mtxsoftware.spring.repository;

import com.mtxsoftware.spring.model.Customer;
import com.mtxsoftware.spring.model.CustomerRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    Iterable<Customer> findByLastnameContainsIgnoreCase(String lastname);
    Iterable<Customer> findByLastnameContainsIgnoreCaseAndCustomerRole(String lastname, CustomerRole role);
    Iterable<Customer> findByCustomerRole(CustomerRole role);




}
