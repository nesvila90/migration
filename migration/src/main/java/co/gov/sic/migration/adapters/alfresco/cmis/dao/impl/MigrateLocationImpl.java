package co.gov.sic.migration.adapters.alfresco.cmis.dao.impl;

import co.gov.sic.migration.adapters.alfresco.cmis.dao.AlfrescoWorkspaceFinder;
import co.gov.sic.migration.adapters.alfresco.cmis.dao.MigrateLocation;
import co.gov.sic.migration.adapters.alfresco.domain.request.CmisDTO;
import co.gov.sic.migration.service.mapper.business.filler.FillerDocumentMetadata;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MigrateLocationImpl implements MigrateLocation {

    private static final Logger LOGGER = LogManager.getLogger(FillerDocumentMetadata.class);


    private static final Integer WORKSPACE_PROPERTY = 2;
    private static final Integer WORKSPACE_FIRST_VALUE_PROPERTY = 0;

    private AlfrescoWorkspaceFinder workspaceFinder;

    @Autowired
    public MigrateLocationImpl(AlfrescoWorkspaceFinder workspaceFinder) {
        this.workspaceFinder = workspaceFinder;

    }


    /*
     * TODO: Cambiar implementación con QueryStatement de Cmis para darle flexibilidad a la query y mover las consultas a
     * constantes de interfaz para poderlas integrar en un archivo de propiedades
     */
    @Override
    public String findLocationDocument(CmisDTO locationParams) {
        LOGGER.info("EXECUTE: MigrateLocationImpl - findLocationDocument - Parameters - locationParams: {}", locationParams.toString());
        String query;
        String WORKSPACE ="";
        final int WORKSPACE_POSITION;
        if (locationParams.getSubserie() == null || locationParams.getSubserie().equals("")) {
            LOGGER.info("EXECUTE: MigrateLocationImpl - findLocationDocument - Without Subserie{}", locationParams.toString());
            query = "SELECT * FROM cmcor:CM_Unidad_Administrativa u JOIN cmcor:CM_Serie  s ON " +
                    "u.cmis:objectId = s.cmis:objectId  WHERE u.cmcor:CodigoUnidadAdminPadre = '" +
                    locationParams.getCodigoUnidadAdministrativaPadre() + "' " +
                    "AND u.cmcor:CodigoDependencia ='" + locationParams.getCodigoDependecia() +
                    "' AND s.cmcor:CodigoSerie = '" + locationParams.getSerie() + "' AND " + PropertyIds.OBJECT_TYPE_ID +"='F:cmcor:CM_Serie'";
        } else {
            LOGGER.info("EXECUTE: MigrateLocationImpl - findLocationDocument - With Subserie{}", locationParams.toString());
            query = "SELECT * FROM cmcor:CM_Subserie   " +
                    "WHERE cmcor:CodigoUnidadAdminPadre = '" + locationParams.getCodigoUnidadAdministrativaPadre() +
                    "' AND cmcor:CodigoDependencia ='" + locationParams.getCodigoDependecia() + "' " +
                    "AND cmcor:CodigoSerie = '" + locationParams.getSerie() + "' AND " +
                    "cmcor:CodigoSubserie= '" + locationParams.getSubserie() + "' AND " + PropertyIds.OBJECT_TYPE_ID +"='F:cmcor:CM_Subserie'";
        }

        List<String> workspaces = new ArrayList<>();
        for (QueryResult result : workspaceFinder.executeQuery(query)) {
            String workspace = (String) result.getProperties()
                    .get(WORKSPACE_PROPERTY)
                    .getValues()
                    .get(WORKSPACE_FIRST_VALUE_PROPERTY);

            workspaces.add(workspace);
        }
        if(!workspaces.isEmpty()){
            WORKSPACE_POSITION = workspaces.size() - 1;
            WORKSPACE = workspaces.get(WORKSPACE_POSITION);
        }

        LOGGER.info("EXECUTE: MigrateLocationImpl - findLocationDocument - WORKSPACE: {}", WORKSPACE);
        return WORKSPACE;
    }

}
