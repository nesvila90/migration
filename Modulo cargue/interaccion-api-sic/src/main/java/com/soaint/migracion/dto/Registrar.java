package com.soaint.migracion.dto;

import lombok.*;

@Builder
@Data
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registrar
{
    private String uuid;
    private int idRegistro;
    private String mensajeEstadoProceso;
    private int estadoProceso;

    public String getMensajeEstadoProceso ()
    {
        return mensajeEstadoProceso;
    }

    public void setMensajeEstadoProceso (String mensajeEstadoProceso)
    {
        this.mensajeEstadoProceso = mensajeEstadoProceso;
    }

    public int getEstadoProceso ()
    {
        return estadoProceso;
    }

    public void setEstadoProceso (int estadoProceso)
    {
        this.estadoProceso = estadoProceso;
    }

    public String getUuid ()
    {
        return uuid;
    }

    public void setUuid (String uuid)
    {
        this.uuid = uuid;
    }

    public int getIdRegistro ()
    {
        return idRegistro;
    }

    public void setIdRegistro (int idRegistro)
    {
        this.idRegistro = idRegistro;
    }

    @Override
    public String toString()
    {
        return "[mensajeEstadoProceso = "+mensajeEstadoProceso+", estadoProceso = "+estadoProceso+", uuid = "+uuid+", idRegistro = "+idRegistro+"]";
    }
}