package co.gov.sic.migration.adapters.alfresco.cmis.util;

import co.gov.sic.migration.adapters.alfresco.domain.request.CmisDTO;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.persistence.entities.Metadatos;
import co.gov.sic.migration.service.migration.validator.impl.BatchValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AlfrescoCmisUtil {

    private static final Logger LOGGER = LogManager.getLogger(AlfrescoCmisUtil.class);


    public static CmisDTO validateWorkspaceParams(Metadatos workspaceParams) {
        LOGGER.info("EXECUTE: AlfrescoCmisUtil - validateWorkspaceParams - Parameters - workspaceParams: {}", workspaceParams.toString());

        if (workspaceParams.getDependenciaJerarquica() == null || workspaceParams.getDependenciaJerarquica().trim().isEmpty()) {
            LOGGER.info("EXECUTE: AlfrescoCmisUtil - validateWorkspaceParams - Parameters - DependeciaJerarquica: {}", workspaceParams.getDependenciaJerarquica());
            return null;
        } else if (workspaceParams.getDependenciaProductora() == null || workspaceParams.getDependenciaProductora().trim().isEmpty()) {
            LOGGER.info("EXECUTE: AlfrescoCmisUtil - validateWorkspaceParams - Parameters - DependenciaProductora: {}", workspaceParams.getDependenciaProductora());
            return null;
        } else if (workspaceParams.getSerie() == null || workspaceParams.getSerie().trim().isEmpty()) {
            LOGGER.info("EXECUTE: AlfrescoCmisUtil - validateWorkspaceParams - Parameters - Serie: {}", workspaceParams.getSerie());
            return null;
        } else {
            LOGGER.info("EXECUTE: AlfrescoCmisUtil - validateWorkspaceParams - CORRECT - workspaceParams: {}", workspaceParams.toString());
            return CmisDTO.builder()
                    .codigoDependecia(workspaceParams.getDependenciaProductora())
                    .codigoUnidadAdministrativaPadre(workspaceParams.getDependenciaJerarquica())
                    .serie(workspaceParams.getSerie())
                    .subserie(workspaceParams.getSubserie()).build();
        }
    }
}
