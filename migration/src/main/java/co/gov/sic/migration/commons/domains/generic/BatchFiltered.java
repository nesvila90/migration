package co.gov.sic.migration.commons.domains.generic;

import lombok.*;
import org.springframework.stereotype.Service;


@Data
@Builder
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class BatchFiltered<Metadatos, Workspace, Document> {

    private Metadatos document;
    private Workspace workspace;
    private Document file;
}
