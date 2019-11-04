package com.mtxsoftware.spring.model;

import com.mtxsoftware.spring.annotation.NotContainsSpecialSymbols;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 1, max = 40, message = "Минимальная длина 1 символ, максимальная 40 символов")
    @Pattern(regexp = "[А-Яа-яA-Za-z0-9-]+", message = "Имя может содержать только цифры, буквы, пробел и дефис")
//    @NotContainsSpecialSymbols
    private String firstname;
    @Size(min = 1, max = 40, message = "Минимальная длина 1 символ, максимальная 40 символов")
    @Pattern(regexp = "[А-Яа-яA-Za-z0-9-]+", message = "Фамилия может содержать только цифры, буквы, пробел и дефис")
//    @NotContainsSpecialSymbols
    private String lastname;
    @NotNull(message = "Роль клиента должна быть указана")
    private CustomerRole customerRole;
    @NotNull(message = "Необходимо указать день рождения клиента")
    private LocalDate birthDate;

    public Customer() {
    }

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
