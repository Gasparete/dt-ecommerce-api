package app.ecommerce.dto;

import app.ecommerce.model.Person;

public class CustomerRequestDTO {
    private PersonDTO person;
    private AddressDTO address;
    private String email;

    public PersonDTO getPerson() {
        return person;
    }

    public String getEmail() {
        return email;
    }

    public AddressDTO getAddress() {
        return address;
    }
}