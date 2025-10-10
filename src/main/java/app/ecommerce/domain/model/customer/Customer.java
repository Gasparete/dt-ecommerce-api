package app.ecommerce.domain.model.customer;

import app.ecommerce.domain.model.Address;
import app.ecommerce.domain.model.Person;
import app.ecommerce.dto.AddressDTO;
import app.ecommerce.dto.CustomerRequestDTO;
import app.ecommerce.dto.PersonDTO;
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
    @Embedded
    private Status status;
    @Transient
    private String tempId;

    public Customer() {
    }

    public Customer(CustomerRequestDTO customerDTO) {
        AddressDTO addressDto = customerDTO.getAddress();
        this.address = new Address(
                addressDto.getZipCode(),
                addressDto.getStreet(),
                addressDto.getNeighborhood(),
                addressDto.getCity(),
                addressDto.getState()
        );

        PersonDTO personDto = customerDTO.getPerson();
        this.person = new Person(
                personDto.getName(),
                personDto.getCpf()
        );

        this.email = customerDTO.getEmail();
        this.status = Status.EM_ANALISE;
        this.tempId = customerDTO.getTempId();
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