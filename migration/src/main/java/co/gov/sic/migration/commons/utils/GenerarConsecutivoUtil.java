package co.gov.sic.migration.commons.utils;

import java.util.UUID;

public class GenerarConsecutivoUtil {

    //TODO: METODO STATICO GENERADOR DE UUID - UUID
    public static String generarCosecutivo() {
        return UUID.randomUUID().toString();
    }


}
