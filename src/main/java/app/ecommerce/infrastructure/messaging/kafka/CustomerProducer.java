package app.ecommerce.infrastructure.messaging.kafka;

import app.ecommerce.domain.model.customer.Customer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerProducer {

    private static final String TOPIC = "topic-new-customer";

    private final KafkaTemplate<String, Customer> kafkaTemplate;

    public CustomerProducer(KafkaTemplate<String, Customer> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNewCustomer(Customer customer) {
        kafkaTemplate.send(TOPIC, customer);
    }
}