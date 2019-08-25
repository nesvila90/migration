package co.gov.sic.migration.commons.utils.converter;

import co.gov.sic.migration.commons.enums.EstadoParametro;

public class EstadoParametroFinder {


    public static String getEstadoParametroName(EstadoParametro estado) {
        switch (estado) {
            case PENDIENTE:
                return estado.getName();
            case ESCANEANDO:
                return estado.getName();
            case PAUSANDO:
                return estado.getName();
            case DETENIENDO:
                return estado.getName();
            case NUNCA_SE_EJECUTO:
                return estado.getName();
            case FINALIZO:
                return estado.getName();
            case FALLO:
                return estado.getName();
            case DETENIDO:
                return estado.getName();
            default:
                return estado.getName();
        }
    }

    public static Integer getEstadoParametroId(EstadoParametro estado) {
        switch (estado) {
            case PENDIENTE:
                return estado.getId();
            case ESCANEANDO:
                return estado.getId();
            case PAUSANDO:
                return estado.getId();
            case DETENIENDO:
                return estado.getId();
            case NUNCA_SE_EJECUTO:
                return estado.getId();
            case FINALIZO:
                return estado.getId();
            case FALLO:
                return estado.getId();
            case DETENIDO:
                return estado.getId();
            default:
                return estado.getId();
        }
    }

    public static EstadoParametro getEstadoParametroId(Integer estado) {
        switch (estado) {
            case 0:
                return EstadoParametro.PENDIENTE;
            case 1:
                return EstadoParametro.ESCANEANDO;
            case 2:
                return EstadoParametro.PAUSANDO;
            case 3:
                return EstadoParametro.DETENIENDO;
            case 4:
                return EstadoParametro.NUNCA_SE_EJECUTO;
            case 5:
                return EstadoParametro.FINALIZO;
            case 6:
                return EstadoParametro.FALLO;
            case 7:
                return EstadoParametro.DETENIDO;
            default:
                return EstadoParametro.FALLO;
        }
    }
}
