package app.ecommerce.domain.validation;

import app.ecommerce.domain.model.customer.Customer;
import app.ecommerce.domain.model.customer.Status;
import app.ecommerce.port.CpfValidationPort;
import org.springframework.stereotype.Service;

@Service
public class CustomerValidator {

    private final CpfValidationPort cpfValidationPort;

    public CustomerValidator(CpfValidationPort cpfValidationPort) {
        this.cpfValidationPort = cpfValidationPort;
    }

    public Status validate(Customer customer) {
        String cpf = customer.getPerson().getCpf();

        if (!cpfValidationPort.isCpfRegistered(cpf)) {
            return Status.errorCpfNotRegistered();
        }

        if (!cpfValidationPort.hasClearLegalRecord(cpf)) {
            return Status.errorCpfWithPendingIssues();
        }

        return Status.OK;
    }
}