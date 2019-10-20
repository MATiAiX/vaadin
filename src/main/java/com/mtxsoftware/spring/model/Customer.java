package com.mtxsoftware.spring.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private CustomerRole customerRole;
    private LocalDate birthDate;

    public Customer() {}

    public Customer(String firstname, String lastname, CustomerRole customerRole, LocalDate birthDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.customerRole = customerRole;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstname='%s', lastname='%s']",
                id, firstname, lastname);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public CustomerRole getCustomerRole() {
        return customerRole;
    }

    public void setCustomerRole(CustomerRole customerRole) {
        this.customerRole = customerRole;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
