package co.gov.sic.migration.commons.enums;

public enum MensajeError {

    NOT_FOUND_METADATA_PENDING(5,"No se han encontrado metadatos pendientes por procesar."),
    OPERACION_MENSAJE(3, "No es posible realizar la operacion solicitada"),
    COULDNT_FIND_PATH_FROM_LOCAL_AND_WORKSPACE(5,"Error en la validación de los metadatos de los documentos, el sistema no pudo encontrar el workspace o la ruta local del archivo a migrar."),
    WEB_SERVICE_EXCEPTION(2, "Web Service - a system error has occurred"),
    WEB_API_EXCEPTION(7, "Web API Service - a system error has occurred - No access to Resource alfresco."),
    SYSTEM_EXCEPTION(1, "System exception - a system error has occurred"),
	NO_DATA (4, "NO-DATA"),
    GENERIC_ERROR(0, "system.generic.error"),
    ROUTE_NO_FOUND(6, "system.generic.error"),
    PARAMETERS_REQUIRED_EMPTY(8, "Parámetros con valores vacios."),
    DOCUMENTS_ALREADY_MIGRATED(9, "Documento Existente. Este documento ya ha sido migrado.")

    ;


    private final String name;
    private final int id;

    MensajeError(int id, String name) {
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
