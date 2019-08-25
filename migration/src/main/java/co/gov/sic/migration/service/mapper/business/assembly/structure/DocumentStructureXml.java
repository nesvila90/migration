package co.gov.sic.migration.service.mapper.business.assembly.structure;

public interface DocumentStructureXml {

    String TIPO_XML = "tipoXML";
    String TYPE = "type";
    String NODE_TYPE = "cmmig:CM_Migracion";
    String TARGET_PATH = "targetPath";
    String FECHA_CREACION = "cm:created";
    String AUTOR = "cm:author";
    String UUID = "uuid";
    String ID_REGISTRO = "idRegistro";
    String TITLE = "cm:title";
    String NOMBRE_REMITENTE = "cmcor:NombreRemitente";
    String NUMERO_RADICADO = "cmcor:NroRadicado";
    String TIPOLOGIA_DOCUMENTAL = "cmcor:TipologiaDocumental";
    String ID_DOC_PRINCIPAL = "cmcor:xIdentificadorDocPrincipal";
    String NUMERO_REFERIDO = "cmcor:xNumeroReferido";
    String X_TIPO = "cmcor:xTipo";
    String ACTUACION = "cmcor:actuacion";
    String TRAMITE = "cmcor:tramite";
    String DEPENDENCIA_ORIGEN = "cmcor:dependenciaOrigen";
    String EVENTO = "cmcor:evento";
    String NOMBRE_PROCESO = "cmcor:nombreProceso";
    String FECHA_RADICACION = "cmcor:xFechaRadicacion";
    String CONTROL_RADICACION = "cmmig:controlRadicacion";
    String FECHA_ACTO = "cmmig:fechaActo";
    String GRUPO_SEGURIDAD = "cmmig:grupoSeguridad";
    String NUMERO_ACTO = "cmmig:numeroActo";
    String SECUENCIA_EVENTO = "cmmig:secuenciaEvento";
    String TIPO_ACTO = "cmmig:tipoActo";
    String TIPO_COMUNICACION = "cmmig:tipoComunicacion";
    String ESTADO_PROCESO = "estadoProceso";
}
