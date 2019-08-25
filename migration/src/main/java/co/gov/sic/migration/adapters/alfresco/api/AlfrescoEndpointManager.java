package co.gov.sic.migration.adapters.alfresco.api;

import co.gov.sic.migration.commons.domains.response.MigracionGeneralStatusDTOResponse;
import org.springframework.http.ResponseEntity;

public interface AlfrescoEndpointManager {

    ResponseEntity bulkImportStart(final String replaceExisting,
                                   final String sourcePaths);

    ResponseEntity<MigracionGeneralStatusDTOResponse> bulkImportPause();

    ResponseEntity<MigracionGeneralStatusDTOResponse> bulkImportStop();

    ResponseEntity<MigracionGeneralStatusDTOResponse> bulkImportResume();

    ResponseEntity<MigracionGeneralStatusDTOResponse> bulkImportStatus();
}
