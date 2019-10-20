package com.mtxsoftware.spring.view;

import com.mtxsoftware.spring.model.Customer;
import com.mtxsoftware.spring.model.CustomerRole;
import com.mtxsoftware.spring.service.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class CustomerForm extends FormLayout {

    private TextField firstname = new TextField("First name");
    private TextField lastname = new TextField("Last name");
    private ComboBox<CustomerRole> customerRole = new ComboBox<>("Role");
    private DatePicker birthDate = new DatePicker("Birthdate");
    public Binder<Customer> binder = new Binder<>(Customer.class);

    private Button saveButton = new Button("Save");
    private Button deleteButton = new Button("Delete");

    private MainView mainView;
    private CustomerService customerService;

    public CustomerForm(MainView mainView, CustomerService customerService){
        this.mainView = mainView;
        this.customerService = customerService;

        customerRole.setItems(CustomerRole.values());

        saveButton.addClickListener(e -> save());
        deleteButton.addClickListener(e -> delete());
        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, deleteButton);
        add(firstname, lastname, customerRole, birthDate, buttonsLayout);

        binder.bindInstanceFields(this);
    }

    private void save() {
        Customer customer = binder.getBean();
        customerService.save(customer);
        mainView.updateGrid();
        setCustomer(null);
    }
    private void delete() {
        Customer customer = binder.getBean();
        customerService.delete(customer);
        mainView.updateGrid();
        setCustomer(null);
    }

    public void setCustomer(Customer customer) {
        binder.setBean(customer);

        if (customer == null) {
            setVisible(false);
        } else {
            setVisible(true);
            firstname.focus();
        }
    }
}
