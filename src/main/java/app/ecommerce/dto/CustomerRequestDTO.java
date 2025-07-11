package app.ecommerce.dto;

import app.ecommerce.model.Person;

public class CustomerRequestDTO {
    
    private Person person;
    private AddressZipCodeDTO address;
    private String email;

    public Person getPerson() {
        return person;
    }

    public String getEmail() {
        return email;
    }

    public AddressZipCodeDTO getAddress() {
        return address;
    }
}