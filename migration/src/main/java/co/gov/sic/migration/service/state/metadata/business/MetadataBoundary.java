package co.gov.sic.migration.service.state.metadata.business;

import co.gov.sic.migration.commons.domains.request.MetadatosDTORequest;
import co.gov.sic.migration.commons.domains.response.EstadoMetadatoDTOResponse;
import co.gov.sic.migration.commons.enums.EstadoParametro;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.persistence.entities.Metadatos;

import java.util.List;

public interface MetadataBoundary {

    List<EstadoMetadatoDTOResponse> findAllMetadataExecutionState();

    EstadoMetadatoDTOResponse updateMetadataState(final MetadatosDTORequest metadatosRequest, int estadoProceso)
            throws SystemException;

    Metadatos buscarMetadato(final int idRegistro) throws BusinessException;

    List<Metadatos> findMetadataByUuid(final String uuid) throws SystemException, BusinessException;

    List<Metadatos> updateIntitalMigrationBatch(final Integer batchSize, final String uuid) throws SystemException;

    Metadatos findMetadatoByIdRegistroAndEstadoProceso(final int idRegistro, EstadoParametro estado) throws SystemException;

}
