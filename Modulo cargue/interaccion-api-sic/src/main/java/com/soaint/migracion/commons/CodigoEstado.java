package com.soaint.migracion.commons;

public enum CodigoEstado {


    PENDIENTE(0, "estado.pediente"),
    ESCANEANDO(1, "estado.escaneado"),
    PAUSANDO(2, "estado.pausado"),
    DETENIENDO(3, "estado.deteniendo"),
    NUNCA_SE_EJECUTO(4, "estado.nunca_se_ejecuto"),
    FINALIZO(5, "estado.finalizo"),
    FALLO(6, "estado.fallo"),
    DETENIDO(7, "estado.detenido");


    private final String name;
    private final int id;

    private CodigoEstado(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}



