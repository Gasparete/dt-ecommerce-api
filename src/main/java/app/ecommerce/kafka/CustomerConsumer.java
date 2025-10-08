package app.ecommerce.kafka;

import app.ecommerce.dto.CustomerResponseDTO;
import app.ecommerce.model.Customer;
import app.ecommerce.model.CustomerStatus;
import app.ecommerce.service.CpfValidationService;
import app.ecommerce.service.CustomerService;
import app.ecommerce.service.SseService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomerConsumer {
    private static final Logger log = LoggerFactory.getLogger(CustomerConsumer.class);

    private final CustomerService customerService;
    private final CpfValidationService validationService;
    private final SseService sseService;

    public CustomerConsumer(CustomerService customerService,
                            CpfValidationService validationService,
                            SseService sseService) {
        this.customerService = customerService;
        this.validationService = validationService;
        this.sseService = sseService;
    }

    @KafkaListener(topics = "topic-new-customer", groupId = "group-new-customer")
    public void consumeNewCustomerEvent(Customer customer) {
        try {
            boolean isCpfValid = validationService.isCpfValid(customer.getPerson().getCpf());
            customer.setStatus(isCpfValid ? CustomerStatus.OK : CustomerStatus.ERRO);
            Customer savedCustomer = customerService.save(customer);
            CustomerResponseDTO customerDTO = new CustomerResponseDTO(savedCustomer);
            sseService.sendCustomerUpdate(customerDTO);
        } catch (Exception e) {
            log.error("Erro ao processar cliente do Kafka. CPF: {}", customer.getPerson().getCpf(), e);
        }
    }
}