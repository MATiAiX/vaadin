package com.mtxsoftware.spring.model;

public enum CustomerRole {
    WHOLESALE("Опт"),
    RETAIL("Розница"),
    CONTRACT("Договор");

    private final String name;

    CustomerRole(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
