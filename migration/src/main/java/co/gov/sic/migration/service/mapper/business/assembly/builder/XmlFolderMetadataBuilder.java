package co.gov.sic.migration.service.mapper.business.assembly.builder;

import co.gov.sic.migration.commons.domains.generic.Workspace;
import co.gov.sic.migration.persistence.entities.Metadatos;
import co.gov.sic.migration.service.mapper.business.assembly.structure.FolderStructureXml;

import java.util.HashMap;
import java.util.Map;

public class XmlFolderMetadataBuilder implements FolderStructureXml {

    private XmlFolderMetadataBuilder() {}

    public static Map<String, Object> createStructure(Metadatos metadata, Workspace workspace) {
        Map<String, Object> contentMetadataFile = new HashMap<>();
        contentMetadataFile.put(TYPE, NODE_TYPE);
        contentMetadataFile.put(TARGET_PATH, workspace.getPath());
        contentMetadataFile.put(TITLE, metadata.getNombreExpediente());
        contentMetadataFile.put(FECHA_CREADO, metadata.getFechaRadicacion());
        contentMetadataFile.put(ACCION, metadata.getActuacion());
        contentMetadataFile.put(UUID, metadata.getUuid());
        contentMetadataFile.put(ID_REGISTRO_MODELO, metadata.getIdregistro());
        contentMetadataFile.put(ID_REGISTRO , metadata.getIdregistro());
        contentMetadataFile.put(ENTIDAD, metadata.getEntidad());
        contentMetadataFile.put(CODIGO_DEPENDECIA_JERARQUICA , metadata.getDependenciaJerarquica());
        contentMetadataFile.put(CODIGO_DEPENDECIA_PRODUCTORA , metadata.getDependenciaProductora());
        contentMetadataFile.put(AUTOR, metadata.getAutorexpediente());
        contentMetadataFile.put(FASE_ARCHIVO, metadata.getFaseArchivo());
        contentMetadataFile.put(FECHA_CIERRE, metadata.getFechaCierre());
        contentMetadataFile.put(FECHA_INICIAL, metadata.getFechaInicial());
        contentMetadataFile.put(FECHA_FINAL, metadata.getFechaFinal());
        contentMetadataFile.put(SOPORTE, metadata.getSoporte());
        contentMetadataFile.put(ID, metadata.getIdExpediente());
        contentMetadataFile.put(CODIGO_SERIE, metadata.getSerie());
        contentMetadataFile.put(CODIGO_SUBSERIE, metadata.getSubserie());
        contentMetadataFile.put(TIPO_XML, "carpeta");
        return contentMetadataFile;
    }
}
