package app.ecommerce.infrastructure.messaging.kafka;

import app.ecommerce.application.CustomerProcessorService;
import app.ecommerce.application.SseService;
import app.ecommerce.domain.model.Customer;
import app.ecommerce.dto.CustomerResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerConsumer {
    private static final Logger log = LoggerFactory.getLogger(CustomerConsumer.class);

    private final CustomerProcessorService customerProcessorService;
    private final SseService sseService;

    public CustomerConsumer(CustomerProcessorService customerProcessorService, SseService sseService) {
        this.customerProcessorService = customerProcessorService;
        this.sseService = sseService;
    }

    @KafkaListener(topics = "topic-new-customer", groupId = "group-new-customer")
    public void consumeNewCustomerEvent(Customer customer) {
        try {
            Customer savedCustomer = customerProcessorService.process(customer);
            CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(savedCustomer);
            sseService.sendCustomerUpdate(customerResponseDTO);
        } catch (Exception e) {
            log.error("Erro ao processar cliente do Kafka. CPF: {}", customer.getPerson().getCpf(), e);
        }
    }
}