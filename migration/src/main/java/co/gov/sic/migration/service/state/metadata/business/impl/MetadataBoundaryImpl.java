package co.gov.sic.migration.service.state.metadata.business.impl;

import co.gov.sic.migration.commons.domains.request.MetadatosDTORequest;
import co.gov.sic.migration.commons.domains.response.EstadoMetadatoDTOResponse;
import co.gov.sic.migration.commons.enums.EstadoParametro;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.commons.utils.converter.MetadatosConverter;
import co.gov.sic.migration.persistence.entities.Ejecucion;
import co.gov.sic.migration.persistence.entities.Metadatos;
import co.gov.sic.migration.service.state.execution.repository.impl.EjecucionControlImpl;
import co.gov.sic.migration.service.state.metadata.business.MetadataBoundary;
import co.gov.sic.migration.service.state.metadata.repository.MetadatosControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetadataBoundaryImpl implements MetadataBoundary {

    private static final Logger LOGGER = LogManager.getLogger(MetadataBoundaryImpl.class);


    @Autowired
    private MetadatosControl metadatosControl;


    @Override
    public List<EstadoMetadatoDTOResponse> findAllMetadataExecutionState() {
        LOGGER.info("MetadataBoundaryImpl - findAllMetadataExecutionState: {}");
        try {
            List<EstadoMetadatoDTOResponse> estadosMetados = metadatosControl.findMetadataExecutionStateGeneral();
            LOGGER.info("MetadataBoundaryImpl - RESULTADO: findAllMetadataExecutionState: {}", estadosMetados.stream().map(e -> e.toString()));
            return estadosMetados;
        } catch (Exception nr) {
            LOGGER.warn("WARN: MetadataBoundaryImpl - findAllMetadataExecutionState: No se encontraron metadatos: {}");
            return new ArrayList<>();
        }
    }

    @Override
    public EstadoMetadatoDTOResponse updateMetadataState(MetadatosDTORequest metadatosRequest, int estadoProceso) throws SystemException {
        LOGGER.info("MetadataBoundaryImpl - updateMetadataState: {}", metadatosRequest.toString());
        return metadatosControl.updateMetadataStateById(MetadatosConverter.dtoToEntityRequest(metadatosRequest), estadoProceso);
    }

    @Override
    public Metadatos buscarMetadato(int idRegistro) throws BusinessException {
        return new Metadatos();
    }

    public List<Metadatos> findMetadataByUuid(final String uuid) throws SystemException, BusinessException {
        return metadatosControl.findMetadatosByUuid(uuid);
    }


    public List<Metadatos> updateIntitalMigrationBatch(final Integer batchSize, final String uuid) throws SystemException {

        List<Metadatos> initialBatch = metadatosControl.findMetadatosByStateAndQuantityResults(EstadoParametro.PENDIENTE, batchSize);

        if (!initialBatch.isEmpty()) {
            metadatosControl.updateMetadataBatch(initialBatch, EstadoParametro.ESCANEANDO, uuid);
        }
        return initialBatch == null ? new ArrayList<>() : initialBatch;
    }


    public Metadatos findMetadatoByIdRegistroAndEstadoProceso(final int idRegistro, EstadoParametro estado) throws SystemException {
        return metadatosControl.findMetadatoByIdRegistroAndEstadoProceso(idRegistro, estado);
    }

}