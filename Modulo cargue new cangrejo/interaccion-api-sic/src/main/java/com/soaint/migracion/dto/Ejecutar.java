package com.soaint.migracion.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ejecutar {
    private String uuid;
    private String mensajeEstadoProceso;
    private int idDocumento;
    private int estadoProceso;


}
