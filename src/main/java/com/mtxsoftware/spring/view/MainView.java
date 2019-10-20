package com.mtxsoftware.spring.view;

import com.mtxsoftware.spring.model.Customer;
import com.mtxsoftware.spring.model.CustomerRole;
import com.mtxsoftware.spring.service.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;


@Route
public class MainView extends VerticalLayout {

    private CustomerService customerService;
    private Grid<Customer> customerGrid;
    private TextField filterLastnameTextField;
    private CustomerForm customerform;

    @Autowired
    public MainView(CustomerService customerService) {
        this.customerService = customerService;
        addTestData();

        customerform = new CustomerForm(this, customerService);

        filterLastnameTextField = new TextField();
        filterLastnameTextField.setPlaceholder("Filter by lastname...");
        filterLastnameTextField.setClearButtonVisible(true);
        filterLastnameTextField.setValueChangeMode(ValueChangeMode.EAGER);
        filterLastnameTextField.addValueChangeListener(e -> updateGrid());

        customerGrid = new Grid<>(Customer.class);
        customerGrid.setMinWidth("600px");
        customerGrid.setColumns("lastname", "firstname", "customerRole");
        customerGrid.asSingleSelect().addValueChangeListener(e ->
                customerform.setCustomer(customerGrid.asSingleSelect().getValue()));

        customerform.setWidth("400px");
        customerform.setCustomer(null);


        Button addCustomerButton = new Button("Add new customer");
        addCustomerButton.addClickListener(e -> {
            customerGrid.asSingleSelect().clear();
            customerform.setCustomer(new Customer());
        });

        HorizontalLayout uiActionElements = new HorizontalLayout();
        uiActionElements.add(filterLastnameTextField, addCustomerButton);

        add(uiActionElements);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(customerGrid, customerform);
        add(horizontalLayout);

        updateGrid();
    }

    public void updateGrid() {
        if (!filterLastnameTextField.getValue().isEmpty())
            customerGrid.setItems(customerService.findAllByLastname(filterLastnameTextField.getValue()));
        else customerGrid.setItems(customerService.findAll());
    }

    private void addTestData() {
        customerService.save(new Customer("alex", "ivanov", CustomerRole.RETAIL, LocalDate.of(1987, 8, 24)));
        customerService.save(new Customer("alex1", "ivanov2", CustomerRole.CONTRACT, LocalDate.of(1988, 1, 12)));
        customerService.save(new Customer("alex2", "sidorov", CustomerRole.WHOLESALE, LocalDate.of(1989, 4, 1)));
        customerService.save(new Customer("alex3", "pimanov", CustomerRole.RETAIL, LocalDate.of(1999, 5, 8)));
        customerService.save(new Customer("alex4", "kukumber", CustomerRole.RETAIL, LocalDate.of(200, 11, 29)));
    }

}
