package app.ecommerce.domain.validation;

import app.ecommerce.domain.model.Customer;
import app.ecommerce.domain.model.CustomerStatus;
import app.ecommerce.port.CpfValidationPort;
import org.springframework.stereotype.Service;

@Service
public class CustomerValidator {

    private final CpfValidationPort cpfValidationPort;

    public CustomerValidator(CpfValidationPort cpfValidationPort) {
        this.cpfValidationPort = cpfValidationPort;
    }

    public CustomerStatus validate(Customer customer) {
        String cpf = customer.getPerson().getCpf();

        if (!cpfValidationPort.isCpfRegistered(cpf)) {
            return CustomerStatus.ERRO;
        }

        if (!cpfValidationPort.hasClearLegalRecord(cpf)) {
            return CustomerStatus.ERRO;
        }

        return CustomerStatus.OK;
    }
}