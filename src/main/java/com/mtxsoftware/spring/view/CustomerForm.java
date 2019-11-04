package com.mtxsoftware.spring.view;

import com.mtxsoftware.spring.model.Customer;
import com.mtxsoftware.spring.model.CustomerRole;
import com.mtxsoftware.spring.service.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.*;

public class CustomerForm extends Dialog {

    private TextField firstname = new TextField("Имя");
    private TextField lastname = new TextField("Фамилия");
    private ComboBox<CustomerRole> customerRole = new ComboBox<>("Роль");
    private DatePicker birthDate = new DatePicker("День рождения");
    private BeanValidationBinder<Customer> binder = new BeanValidationBinder<>(Customer.class);

    private Button saveButton = new Button("Сохранить");
    private Button cancelButton = new Button("Отмена");

    private MainView mainView;
    private CustomerService customerService;

    public CustomerForm(MainView mainView, CustomerService customerService) {
        this.mainView = mainView;
        this.customerService = customerService;

        customerRole.setItems(CustomerRole.values());
        customerRole.setItemLabelGenerator(CustomerRole::getName);

        saveButton.addClickListener(e -> save());
        cancelButton.addClickListener(e -> this.close());
        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, cancelButton);

        HorizontalLayout fioFields = new HorizontalLayout(firstname, lastname);
        HorizontalLayout paramFields = new HorizontalLayout(customerRole, birthDate);

        VerticalLayout fields = new VerticalLayout();
        fields.add(fioFields, paramFields);
        this.add(fields, buttonsLayout);

        binder.bindInstanceFields(this);
    }

    private void showInvalidDialog() {
        Dialog dialog = new Dialog();
        VerticalLayout layout = new VerticalLayout();
        layout.add(new Label("Поля фамилии и имени не должны содержать спецсимволы. Все поля обязательны для заполнения"));
        layout.add(new Button("Ок", event -> dialog.close()));
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
        this.close();
    }

    public void setCustomer(Customer customer) {
        binder.setBean(customer);
    }
}
