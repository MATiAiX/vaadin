package com.mtxsoftware.spring.view;

import com.mtxsoftware.spring.model.Customer;
import com.mtxsoftware.spring.model.CustomerRole;
import com.mtxsoftware.spring.service.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.*;

public class CustomerForm extends FormLayout {

    private TextField firstname = new TextField("Имя");
    private TextField lastname = new TextField("Фамилия");
    private ComboBox<CustomerRole> customerRole = new ComboBox<>("Роль");
    private DatePicker birthDate = new DatePicker("День рождения");
    private BeanValidationBinder<Customer> binder = new BeanValidationBinder<>(Customer.class);

    private Button saveButton = new Button("Сохранить");
    private Button deleteButton = new Button("Удалить");

    private MainView mainView;
    private CustomerService customerService;

    public CustomerForm(MainView mainView, CustomerService customerService) {
        this.mainView = mainView;
        this.customerService = customerService;

        customerRole.setItems(CustomerRole.values());
        customerRole.setItemLabelGenerator(CustomerRole::getName);
        customerRole.setValue(CustomerRole.RETAIL);

        saveButton.addClickListener(e -> save());
        deleteButton.addClickListener(e -> delete());
        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, deleteButton);
        add(firstname, lastname, customerRole, birthDate, buttonsLayout);


        binder.bindInstanceFields(this);
    }

    private void showInvalidDialog() {
        Dialog dialog = new Dialog();
        VerticalLayout layout = new VerticalLayout();
        layout.add(new Label("Поля фамилии и имени не должны содержать спецсимволы. Все поля обязательны для заполнения"));
        layout.add(new Button("Cancel", event -> dialog.close()));
        dialog.add(layout);

        dialog.setWidth("400px");
        dialog.setHeight("150px");
        dialog.setCloseOnOutsideClick(false);
        dialog.open();

    }

    private void save() {
        if (!binder.isValid()) {
            showInvalidDialog();
            return;
        }
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
