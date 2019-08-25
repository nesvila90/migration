package co.gov.sic.migration.commons.domains.generic;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Workspace implements Serializable {
    private String path;
}
