package app.ecommerce.service;

import app.ecommerce.model.Customer;
import app.ecommerce.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final AddressService addressService;

    public CustomerService(CustomerRepository repository, AddressService addressService) {
        this.repository = repository;
        this.addressService = addressService;
    }

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Optional<Customer> getCustomerById(long id) {
        return repository.findById(id);
    }

//    public ResponseEntity<Customer> createClient(CustomerRequestDTO clientDTO) {
//        Customer client = new Customer(clientDTO);
//        Address addressFilled = addressService.getAddressByZipCode(clientDTO.getAddress().getZipCode());
//        client.setAddress(addressFilled);
//        return ResponseEntity.ok(repository.save(client));
//    }

    public ResponseEntity<Customer> createCustomer(Customer customer) {
        return ResponseEntity.ok(repository.save(customer));
    }

    public ResponseEntity<Void> deleteCustomer(Long id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        repository.delete(customer);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Customer> updateCustomer(Long id, Customer newCustomer) {
        Customer existingCustomer = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        existingCustomer.setPerson(newCustomer.getPerson());
        existingCustomer.setAddress(newCustomer.getAddress());
        existingCustomer.setEmail(newCustomer.getEmail());

        Customer savedCustomer = repository.save(existingCustomer);
        return ResponseEntity.ok(savedCustomer);
    }
}