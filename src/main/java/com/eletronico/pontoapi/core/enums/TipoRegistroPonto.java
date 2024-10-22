package com.eletronico.pontoapi.core.enums;

public enum TipoRegistroPonto {

    ENTRADA(0, "ENTRADA"),
    SAIDA(1, "SAIDA"),
    PAUSA_INICIAL(2, "PAUSA_INICIAL"),
    PAUSA_FINAL(3, "PAUSA_FINAL");

    private Integer codigo;
    private String descricao;

    TipoRegistroPonto(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

}
