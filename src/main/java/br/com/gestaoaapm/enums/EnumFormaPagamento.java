package br.com.gestaoaapm.enums;

public enum EnumFormaPagamento {
    DINHEIRO("Dinheiro"),
    CARTAO_CREDITO("Cartão de Crédito"),
    CARTAO_DEBITO("Cartão de Débito"),
    PIX("PIX"),
    BOLETO("Boleto"),
    DEPOSITO("Depósito");
    private String descricao;

    EnumFormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

