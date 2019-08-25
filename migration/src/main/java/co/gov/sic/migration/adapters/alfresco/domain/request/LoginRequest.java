package co.gov.sic.migration.adapters.alfresco.domain.request;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {

    private String username;
    private String password;

}
