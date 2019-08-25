package co.gov.sic.migration.adapters.alfresco.api.bulkimport.impl;

import co.gov.sic.migration.adapters.alfresco.api.AlfrescoEndpointManager;
import co.gov.sic.migration.adapters.alfresco.api.EndpointManager;
import co.gov.sic.migration.adapters.alfresco.infrastructure.EndpointConfig;
import co.gov.sic.migration.commons.constants.AlfrescoConstants;
import co.gov.sic.migration.commons.domains.response.MigracionGeneralStatusDTOResponse;
import co.gov.sic.migration.commons.utils.bundles.AlfrescoBundleUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AlfrescoEndpointManagerImpl extends EndpointManagerAbstract implements AlfrescoEndpointManager {


    private static final Logger LOGGER = LogManager.getLogger(AlfrescoEndpointManagerImpl.class);

    //TODO: Ajustar bundle de basepath de url
    private static String alfrescoClientUrl = AlfrescoBundleUtil.getProperty("alfresco.client.url");

    @Autowired
    protected EndpointManager endpointManager;

    @Autowired
    public AlfrescoEndpointManagerImpl(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    public ResponseEntity<?> bulkImportStart(final String replaceExisting,
                                          final String sourcePath) {
        LOGGER.info("INVOKING: AlfrescoEndpointManagerImpl - Endpoint bulkImportStart - Parameters - replaceExisting: {}, sourcePath {}");
        final String url = createUrlRequestInitiateBulk(replaceExisting, sourcePath);

        ResponseEntity response = endpointManager.endpointConsumerClient(
                url,
                MigracionGeneralStatusDTOResponse.class,
                HttpMethod.POST);

        return response.getStatusCode().is2xxSuccessful() ?
                ResponseEntity.ok().body(response.getBody()) :
                ResponseEntity.unprocessableEntity().body(new MigracionGeneralStatusDTOResponse());
    }

    @Override
    public ResponseEntity<MigracionGeneralStatusDTOResponse> bulkImportPause() {
        LOGGER.info("INVOKING: AlfrescoEndpointManagerImpl - Endpoint bulkImportPause - Without Parameters - ");
        String urlEndpointResume =
                alfrescoClientUrl.concat(AlfrescoConstants.PAUSE_ENDPOINT);
        return endpointManager.endpointConsumerClient(urlEndpointResume,
                MigracionGeneralStatusDTOResponse.class,
                HttpMethod.POST);
    }

    @Override
    public ResponseEntity<MigracionGeneralStatusDTOResponse> bulkImportStop() {
        LOGGER.info("INVOKING: AlfrescoEndpointManagerImpl - Endpoint bulkImportStop - Without Parameters - ");
        String urlEndpointStop =
                alfrescoClientUrl.concat(AlfrescoConstants.STOP_ENDPOINT);
        return endpointManager.endpointConsumerClient(urlEndpointStop,
                MigracionGeneralStatusDTOResponse.class,
                HttpMethod.POST);
    }

    @Override
    public ResponseEntity<MigracionGeneralStatusDTOResponse> bulkImportResume() {
        LOGGER.info("INVOKING: AlfrescoEndpointManagerImpl - Endpoint bulkImportResume - Without Parameters - ");
        String urlEndpointResume =
                alfrescoClientUrl.concat(AlfrescoConstants.RESUME_ENDPOINT);
        return endpointManager.endpointConsumerClient(urlEndpointResume,
                MigracionGeneralStatusDTOResponse.class,
                HttpMethod.POST);
    }

    @Override
    public ResponseEntity<MigracionGeneralStatusDTOResponse> bulkImportStatus() {
        LOGGER.info("INVOKING: AlfrescoEndpointManagerImpl - Endpoint bulkImportStatus - Without Parameters - ");
        String urlEndpointStatus =
                alfrescoClientUrl.concat(AlfrescoConstants.STATUS_ENDPOINT);
        ResponseEntity<MigracionGeneralStatusDTOResponse> response = endpointManager.endpointConsumerClient(urlEndpointStatus,
                MigracionGeneralStatusDTOResponse.class,
                HttpMethod.GET);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }



    private String createUrlRequestInitiateBulk(final String replaceExisting,
                                                final String sourcePath) {

        return alfrescoClientUrl.concat(AlfrescoConstants.INITIATE_ENDPOINT)
                .concat(AlfrescoConstants.DEFAULT_TARGET_PATH_IMPORT)
                .concat(AlfrescoConstants.SOURCE_DIRECTORY)
                .concat(sourcePath)
                .concat(AlfrescoConstants.REPLACE_EXISTING)
                .concat(replaceExisting)
                .concat(AlfrescoConstants.DEFAULT_BEAN_MIGRATION);
    }
}