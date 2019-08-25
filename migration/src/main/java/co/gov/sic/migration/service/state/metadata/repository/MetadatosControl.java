package co.gov.sic.migration.service.state.metadata.repository;

import co.gov.sic.migration.commons.domains.response.EstadoMetadatoDTOResponse;
import co.gov.sic.migration.commons.domains.response.MetadatosDTOResponse;
import co.gov.sic.migration.commons.enums.EstadoParametro;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.persistence.entities.Metadatos;

import java.util.List;

public interface MetadatosControl {

    List<EstadoMetadatoDTOResponse> findMetadataExecutionStateByUuid(final String uuid) throws BusinessException, SystemException;

    Metadatos findMetadatoByIdRegistro(final int idRegistro) throws BusinessException, SystemException;

    EstadoMetadatoDTOResponse updateMetadataStateById(final Metadatos datos, final Integer codigoEstadoProceso) throws SystemException;

    List<Metadatos> findMetadataByState(final Integer estado) throws BusinessException, SystemException;

    List<Metadatos> findMetadatosByUuid(final String uuid) throws SystemException;

    List<Metadatos> findMetadataByState(final EstadoParametro estado) throws SystemException;

    List<Metadatos> updateMetadataBatch(final List<Metadatos> metadatos, final EstadoParametro estadoFinal, String uuid) throws SystemException;

    List<Metadatos> findMetadatosByStateAndQuantityResults(final EstadoParametro estado, final Integer cantResultado) throws SystemException;

    Metadatos findMetadatoByIdRegistroAndEstadoProceso(final int idRegistro, EstadoParametro estado) throws SystemException;

    List<EstadoMetadatoDTOResponse> findMetadataExecutionStateGeneral() throws SystemException;

    }
