package app.ecommerce.domain.model;

public enum CustomerStatus {
    EM_ANALISE("Em análise"),
    OK("OK"),
    ERRO("Erro");

    private final String displayName;

    CustomerStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}