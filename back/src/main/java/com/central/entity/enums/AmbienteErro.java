package com.central.entity.enums;

public enum AmbienteErro {

    homologacao("homologacao"),
    dev("dev"),
    producao("producao");

    private String ambiente;

    AmbienteErro (String ambiente) {
        this.ambiente = ambiente;
    }
	
}
