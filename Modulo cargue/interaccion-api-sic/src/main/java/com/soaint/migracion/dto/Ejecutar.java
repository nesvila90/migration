package com.soaint.migracion.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ejecutar {
    private String uuid;
    private int idRegistro;
    private String mensajeEstadoProceso;
    private int estadoProceso;

    @Override
    public String toString()
    {
        return "[mensajeEstadoProceso = "+mensajeEstadoProceso+", estadoProceso = "+estadoProceso+", uuid = "+uuid+", idRegistro = "+idRegistro+"]";
    }
}
