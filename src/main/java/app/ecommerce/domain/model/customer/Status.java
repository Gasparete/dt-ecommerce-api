package app.ecommerce.domain.model.customer;

import jakarta.persistence.Embeddable;

@Embeddable
public class Status {

    public static final Status EM_ANALISE = new Status("EM_ANALISE", "Em análise...");
    public static final Status OK = new Status("OK", "Cliente cadastrado com sucesso.");
    private String code;
    private String description;

    protected Status() {
    }

    private Status(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Status falhaErro(String errorMessage) {
        return new Status("ERRO", String.format("Falha: %s", errorMessage));
    }

    public static Status falhaCpfDuplicado(String cpf) {
        return new Status("ERRO", "Falha: O CPF '" + cpf + "' já está cadastrado.");
    }

    public static Status falhaCpfNaoRegistrado() {
        return new Status("ERRO", "Falha: O CPF informado não foi encontrado nos registros.");
    }

    public static Status falhaCpfComPendencias() {
        return new Status("ERRO", "Falha: O CPF informado possui pendências legais.");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}