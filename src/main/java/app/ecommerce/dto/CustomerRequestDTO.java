package app.ecommerce.dto;

public class CustomerRequestDTO {
    private PersonDTO person;
    private AddressDTO address;
    private String email;
    private String tempId;

    public PersonDTO getPerson() {
        return person;
    }

    public String getEmail() {
        return email;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public String getTempId(){
        return tempId;
    }
}