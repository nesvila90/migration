package co.gov.sic.migration.service.mapper.business.assembly.builder;

import co.gov.sic.migration.commons.domains.generic.Workspace;
import co.gov.sic.migration.commons.utils.date.DateUtil;
import co.gov.sic.migration.persistence.entities.Metadatos;
import co.gov.sic.migration.service.mapper.business.assembly.structure.DocumentStructureXml;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class XmlDocumentMetadataBuilder implements DocumentStructureXml {


    private XmlDocumentMetadataBuilder(){}

    public static Map<String, Object> assembler(Metadatos metadata, Workspace workspace) {
        Map<String, Object> contentMetadataFile = new HashMap<>();

        contentMetadataFile.put(TYPE, NODE_TYPE);
        contentMetadataFile.put(TITLE, metadata.getNombreExpediente());
        contentMetadataFile.put(TARGET_PATH, workspace.getPath());
        contentMetadataFile.put(FECHA_CREACION , DateUtil.dateNowStringToString(new Date()));
        contentMetadataFile.put(AUTOR, metadata.getAutor());
        contentMetadataFile.put(UUID, metadata.getUuid());
        contentMetadataFile.put(ID_REGISTRO , metadata.getIdregistro());
        contentMetadataFile.put(NOMBRE_REMITENTE ,metadata.getAutor());
        contentMetadataFile.put(NUMERO_RADICADO,metadata.getNumeroRadicado());
        contentMetadataFile.put(TIPOLOGIA_DOCUMENTAL, metadata.getTipoDocumental());
        contentMetadataFile.put(X_TIPO, metadata.getTipoDocumento());
        contentMetadataFile.put(ACTUACION, metadata.getActuacion());
        contentMetadataFile.put(TRAMITE, metadata.getTramite());
        contentMetadataFile.put(EVENTO, metadata.getEvento());
        contentMetadataFile.put(SECUENCIA_EVENTO, metadata.getSecuenciaEvento());
        contentMetadataFile.put(FECHA_RADICACION, metadata.getFechaRadicacion());
        contentMetadataFile.put(CONTROL_RADICACION, metadata.getControlRadicacion());
        contentMetadataFile.put(GRUPO_SEGURIDAD, metadata.getGrupoSeguridad());
        contentMetadataFile.put(FECHA_ACTO, metadata.getFechaActo());
        contentMetadataFile.put(NUMERO_ACTO, metadata.getNumeroActo());
        contentMetadataFile.put(TIPO_ACTO, metadata.getTipoActo());
        contentMetadataFile.put(TIPO_COMUNICACION, metadata.getTipoComunicacion());
        contentMetadataFile.put(ESTADO_PROCESO, metadata.getEstadoProceso());
        contentMetadataFile.put(TIPO_XML, "carpeta");

        return contentMetadataFile;
    }

}
