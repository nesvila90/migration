package co.gov.sic.migration.commons.enums;


public enum EstadoParametro {


    PENDIENTE(0,"Pendiente."),
    ESCANEANDO(1,"Escaneando."),
    PAUSANDO(2,"Pausando"),
    DETENIENDO(3,"Deteniendo"),
    NUNCA_SE_EJECUTO(4,"Nunca se ejecutó"),
    FINALIZO(5,"Finalizó"),
    FALLO(6,"Falló"),
    DETENIDO(7,"Detenido");



    private final String name;
    private final int id;

    private EstadoParametro(int id, String name) {
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
