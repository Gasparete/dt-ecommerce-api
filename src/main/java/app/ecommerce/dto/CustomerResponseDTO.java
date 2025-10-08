package app.ecommerce.dto;

import app.ecommerce.model.Customer;
import app.ecommerce.model.CustomerStatus;

public class CustomerResponseDTO {
    private Long id;
    private PersonDTO person;
    private AddressDTO address;
    private String email;
    private CustomerStatus status;
    private String tempId;

    public CustomerResponseDTO(Customer customer) {
        this.id = customer.getId();
        this.email = customer.getEmail();
        this.status = customer.getStatus();
        this.tempId = customer.getTempId();

        if (customer.getPerson() != null) {
            this.person = new PersonDTO();
            this.person.setName(customer.getPerson().getName());
            this.person.setCpf(customer.getPerson().getCpf());
        }

        if (customer.getAddress() != null) {
            this.address = new AddressDTO();
            this.address.setZipCode(customer.getAddress().getZipCode());
            this.address.setStreet(customer.getAddress().getStreet());
            this.address.setNeighborhood(customer.getAddress().getNeighborhood());
            this.address.setCity(customer.getAddress().getCity());
            this.address.setState(customer.getAddress().getState());
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

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }
}