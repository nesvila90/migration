package co.gov.sic.migration.adapters.alfresco.domain.response;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CountRate {

    private Integer Count;
    private Double Rate;
}
