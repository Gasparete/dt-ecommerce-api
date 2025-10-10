package app.ecommerce.dto;

import app.ecommerce.domain.model.customer.Customer;
import app.ecommerce.domain.model.customer.Status;

public class CustomerResponseDTO {
    private Long id;
    private PersonDTO person;
    private AddressDTO address;
    private String email;
    private Status status;
    private String tempId;

    public CustomerResponseDTO() {
    }

    public CustomerResponseDTO(Customer customer) {
        this.id = customer.getId();
        this.email = customer.getEmail();
        this.status = customer.getStatus();
        this.tempId = customer.getTempId();

        if (customer.getPerson() != null) {
            this.person = new PersonDTO(
                    customer.getPerson().getName(),
                    customer.getPerson().getCpf());
        }

        if (customer.getAddress() != null) {
            this.address = new AddressDTO(
                    customer.getAddress().getZipCode(),
                    customer.getAddress().getStreet(),
                    customer.getAddress().getNeighborhood(),
                    customer.getAddress().getCity(),
                    customer.getAddress().getState());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }
}