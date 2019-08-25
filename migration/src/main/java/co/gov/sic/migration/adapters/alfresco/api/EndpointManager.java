package co.gov.sic.migration.adapters.alfresco.api;

import co.gov.sic.migration.commons.domains.response.MigracionGeneralStatusDTOResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface EndpointManager {

    ResponseEntity<MigracionGeneralStatusDTOResponse> endpointConsumerClient(final String pathEndpoint,
                                                                             final Class<?> typeResponse,
                                                                             final HttpMethod method);

}
