package co.gov.sic.migration.commons.utils.converter;

import co.gov.sic.migration.commons.domains.generic.GeneralStatus;
import co.gov.sic.migration.commons.domains.response.EstadoDTOResponse;
import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;
import co.gov.sic.migration.persistence.entities.AplicacionEstado;
import co.gov.sic.migration.persistence.entities.Estados;

import java.io.Serializable;

public class AplicacionEstadoConverter implements Serializable {

    public static MigrationStatusDTOResponse AplicacionEstadoEntityToDto(AplicacionEstado aplicacionEstado){
        EstadoDTOResponse estado = EstadoDTOResponse.builder()
                                    .codEstado(aplicacionEstado.getEstadoMigracion().getCodEstado())
                                    .descripcionEstado(aplicacionEstado.getEstadoMigracion().getDescripcionEstado())
                                    .etapa(aplicacionEstado.getEstadoMigracion().getEtapa())
                .build();
        return MigrationStatusDTOResponse.builder()
                .idEjecucion(aplicacionEstado.getIdEjecucion())
                .carpetaMigracion(aplicacionEstado.getCarpetaMigracion())
                .estadoMigracion(estado)
                .descripcionEstadoMigracion(aplicacionEstado.getDescripcionEstadoMigracion())
                .estadoGeneralMigracion(aplicacionEstado.getEstadoGeneralMigracion())
                .uuid(aplicacionEstado.getUuid())
                .build();
    }

    public static AplicacionEstado AplicacionEstadoDtoToEntity(MigrationStatusDTOResponse aplicacionEstado){
        Estados estado = Estados.builder()
                .codEstado(aplicacionEstado.getEstadoMigracion().getCodEstado())
                .descripcionEstado(aplicacionEstado.getEstadoMigracion().getDescripcionEstado())
                .etapa(aplicacionEstado.getEstadoMigracion().getEtapa())
                .build();
        return AplicacionEstado.builder()
                .idEjecucion(aplicacionEstado.getIdEjecucion())
                .carpetaMigracion(aplicacionEstado.getCarpetaMigracion())
                .estadoMigracion(estado)
                .descripcionEstadoMigracion(aplicacionEstado.getDescripcionEstadoMigracion())
                .estadoGeneralMigracion(aplicacionEstado.getEstadoGeneralMigracion())
                .uuid(aplicacionEstado.getUuid())
                .build();
    }

    public static GeneralStatus AplicacionEstadoDtoToGeneralStatus(MigrationStatusDTOResponse aplicacionEstado){
        GeneralStatus generalStatus = GeneralStatus.builder().generalState(EstadoParametroFinder
                .getEstadoParametroId(aplicacionEstado.getEstadoMigracion().getCodEstado()))
                .pathMigration(aplicacionEstado.getCarpetaMigracion())
                .uuid(aplicacionEstado.getUuid()).build();

        return generalStatus;
    }

}
