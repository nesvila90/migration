package co.gov.sic.migration.commons.enums;

public enum Etapa {

    INICIADA("En Ejecución"),
    FINALIZADA ("Sin Ejecución");

    private final String name;

    Etapa(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
