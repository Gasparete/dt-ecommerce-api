package app.ecommerce.model;

import app.ecommerce.dto.CustomerRequestDTO;
import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Address address;
    @Embedded
    private Person person;
    private String email;

    public Customer() {
    }

    public Customer(CustomerRequestDTO clientDTO) {
        address = new Address(clientDTO.getAddress().getZipCode());
        person = clientDTO.getPerson();
        email = clientDTO.getEmail();
    }

    public Long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}