package com.soaint.migracion.commons;

import com.soaint.migracion.dto.Registrar;
import org.alfresco.extension.bulkimport.source.BulkImportItem;
import org.alfresco.extension.bulkimport.source.BulkImportItemVersion;

import java.io.Serializable;

public class BulkImportItemConverter {

    private static final String ID_DOCUMENTO_KEY = "idRegistro";



    public static Registrar converterRegistar(BulkImportItem<BulkImportItemVersion> item,
                                                final String uuid,
                                                final CodigoEstado estado,
                                                final ImportStatus codigoEstado ){
        Integer idRegistro =  (Integer) getObjectByKey(ID_DOCUMENTO_KEY, item);


        return Registrar.builder().idRegistro(idRegistro).estadoProceso(estado.getId()).build();


    }

    private static Serializable getObjectByKey(final String key, BulkImportItem<BulkImportItemVersion> item) {
        return item.getVersions().first().getMetadata().get(key);
    }

    /*
     public static EjecucionDTOResponse converterEntityToDto(Ejecucion e, List<MetadatosDTOResponse> metadatos){
        return EjecucionDTOResponse.builder()
                .idRegistro(e.getIdRegistro())
                .mensajeEstadoProceso(e.getMensajeEstadoProceso())
                .estadoProceso(e.getCodEstadoProceso() != null ?
                        EstadosConverter.converterEntityToDto(e.getCodEstadoProceso()) : new EstadoDTOResponse())
                .metadatosList(metadatos != null ? metadatos : new ArrayList<>())
                .build();
    }

    public static EjecucionDTOResponse converterEntityToDto(Ejecucion e){
        return EjecucionDTOResponse.builder()
                .idRegistro(e.getIdRegistro())
                .mensajeEstadoProceso(e.getMensajeEstadoProceso())
                .estadoProceso(e.getCodEstadoProceso() != null ?
                        EstadosConverter.converterEntityToDto(e.getCodEstadoProceso()) : new EstadoDTOResponse())
                .uuid(e.getUuid())
                .build();
    }

     */

}
