package com.soaint.migracion.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegistrarEjecucion {

    private String uuid;
    private String mensajeEstadoProceso;
    private int idRegistro;
    private EstadoDTO estadoProceso;
}
