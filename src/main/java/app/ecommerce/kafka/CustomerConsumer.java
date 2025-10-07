package app.ecommerce.kafka;

import app.ecommerce.model.Customer;
import app.ecommerce.service.CustomerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerConsumer {

    private final CustomerService customerService;

    public CustomerConsumer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @KafkaListener(topics = "topic-new-customer", groupId = "group-new-customer")
    public void consumeNewCustomerEvent(Customer customer) {
        try {
            customerService.createCustomer(customer);
        } catch (Exception e) {
            // Aqui você pode adicionar lógica para tratar erros (ex: enviar para um "dead letter topic")
            System.err.println("❌ Erro ao salvar cliente no banco: " + e.getMessage());
        }
    }
}