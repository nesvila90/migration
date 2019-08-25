package co.gov.sic.migration.commons.domains.generic;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Document implements Serializable {

    private String path;
}
