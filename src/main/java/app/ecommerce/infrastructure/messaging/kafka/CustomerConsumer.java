package app.ecommerce.infrastructure.messaging.kafka;

import app.ecommerce.application.CustomerProcessorService;
import app.ecommerce.application.SseService;
import app.ecommerce.domain.model.customer.Customer;
import app.ecommerce.dto.CustomerResponseDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerConsumer {
    private final CustomerProcessorService customerProcessorService;
    private final SseService sseService;

    public CustomerConsumer(CustomerProcessorService customerProcessorService, SseService sseService) {
        this.customerProcessorService = customerProcessorService;
        this.sseService = sseService;
    }

    @KafkaListener(topics = "topic-new-customer")
    public void consumeNewCustomerEvent(Customer customer) {
        Customer savedCustomer = customerProcessorService.process(customer);
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(savedCustomer);
        sseService.sendCustomerUpdate(customerResponseDTO);
    }
}