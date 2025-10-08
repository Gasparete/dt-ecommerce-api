package app.ecommerce.application;

import app.ecommerce.domain.model.Customer;
import app.ecommerce.domain.validation.CustomerValidator;
import org.springframework.stereotype.Service;

@Service
public class CustomerProcessorService {

    private final CustomerValidator customerValidator;
    private final CustomerService customerService;

    public CustomerProcessorService(CustomerValidator customerValidator, CustomerService customerService) {
        this.customerValidator = customerValidator;
        this.customerService = customerService;
    }

    public Customer process(Customer customer) {
        customer.setStatus(customerValidator.validate(customer));
        return customerService.save(customer);
    }
}