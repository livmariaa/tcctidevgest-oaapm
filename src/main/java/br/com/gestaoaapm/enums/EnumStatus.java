package br.com.gestaoaapm.enums;


public enum EnumStatus {

    DISPONIVEL("Disponível"),
    INDISPONIVEL("Indisponível");

    private String status;

    EnumStatus(String status) {
        this.status  = status;
    }

    public String getStatus() { return status; }
}

