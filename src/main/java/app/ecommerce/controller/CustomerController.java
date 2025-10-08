package app.ecommerce.controller;

import app.ecommerce.application.CustomerService;
import app.ecommerce.domain.model.Customer;
import app.ecommerce.dto.CustomerRequestDTO;
import app.ecommerce.dto.CustomerResponseDTO;
import app.ecommerce.infrastructure.messaging.kafka.CustomerProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerProducer customerProducer;

    public CustomerController(CustomerService customerService, CustomerProducer customerProducer) {
        this.customerService = customerService;
        this.customerProducer = customerProducer;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        Customer customer = new Customer(customerRequestDTO);
        customerProducer.sendNewCustomer(customer);
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(customer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }
}