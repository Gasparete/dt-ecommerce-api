package app.ecommerce.dto;

import app.ecommerce.model.Customer;
import app.ecommerce.model.CustomerStatus;

public class CustomerResponseDTO {
    private final Long id;
    private final String name;
    private final String email;
    private final CustomerStatus status;

    public CustomerResponseDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getPerson().getName();
        this.email = customer.getEmail();
        this.status = customer.getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public CustomerStatus getStatus() {
        return status;
    }
}