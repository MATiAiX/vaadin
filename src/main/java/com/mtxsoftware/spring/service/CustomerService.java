package com.mtxsoftware.spring.service;

import com.mtxsoftware.spring.model.Customer;
import com.mtxsoftware.spring.model.CustomerRole;
import com.mtxsoftware.spring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        repository = customerRepository;
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();
        repository.findAll().forEach(customerList::add);
        return customerList;
    }

    public List<Customer> findAllByLastname(String lastname) {
        List<Customer> customerList = new ArrayList<>();
        repository.findByLastnameContainsIgnoreCase(lastname).forEach(customerList::add);
        return customerList;
    }

    public List<Customer> findAllByLastnameAndRole(String lastname, CustomerRole role) {
        List<Customer> customerList = new ArrayList<>();
        repository.findByLastnameContainsIgnoreCaseAndCustomerRole(lastname, role).forEach(customerList::add);
        return customerList;
    }

    public List<Customer> getAllRetail() {
        List<Customer> customerList = new ArrayList<>();
        repository.findByCustomerRole(CustomerRole.RETAIL).forEach(customerList::add);
        return customerList;
    }

    public void delete(Customer customer){
        repository.delete(customer);
    }
}
