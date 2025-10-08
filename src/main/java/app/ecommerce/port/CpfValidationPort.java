package app.ecommerce.port;

public interface CpfValidationPort {
    boolean isCpfRegistered(String cpf);

    boolean hasClearLegalRecord(String cpf);
}