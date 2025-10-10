package app.ecommerce.application;

import app.ecommerce.application.exception.DuplicateCpfException;
import app.ecommerce.domain.model.customer.Customer;
import app.ecommerce.domain.model.customer.Status;
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
        try {
            customer.setStatus(customerValidator.validate(customer));
            return customerService.save(customer);
        } catch (DuplicateCpfException e) {
            customer.setStatus(Status.falhaCpfDuplicado(customer.getPerson().getCpf()));
            return customer;
        }
    }
}