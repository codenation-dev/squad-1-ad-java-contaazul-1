package com.central.entity.enums;

public enum NivelErro {
	
    debug("debug"),
    error("error"),
    warning("warning"),
	trace("trace"),
	info("info"),
	fatal("fatal");
	

    private String nivel;

    NivelErro (String nivel) {
        this.nivel = nivel;
    }
}
