package co.gov.sic.migration.service.migration.folder.impl;

import co.gov.sic.migration.commons.enums.CodigoError;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.commons.exceptions.builder.ExceptionBuilder;
import co.gov.sic.migration.commons.utils.bundles.AlfrescoBundleUtil;
import co.gov.sic.migration.service.mapper.business.filler.FillerDocumentMetadata;
import co.gov.sic.migration.service.migration.folder.TemporalFolderBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class TemporalFolderBuilderImpl implements TemporalFolderBuilder {

    private static final Logger LOGGER = LogManager.getLogger(TemporalFolderBuilderImpl.class);

    @Override
    public File getTemporalFolder(String uuid) throws SystemException {
        try {
            LOGGER.info("TemporalFolderBuilderImpl - getTemporalFolder - Parameters - uuid: {}", uuid);
            String temporalPath = findTemporalPath();
            String temporalFolderPath = createTemporalFolder(uuid, temporalPath);

            LOGGER.info("EXECUTE: TemporalFolderBuilderImpl - temporalPath {} - temporalFolderPath - uuid: {}", temporalPath, temporalFolderPath);
            File folder = new File(temporalFolderPath);
            folder.setExecutable(true, false);
            folder.setReadable(true, false);
            folder.setWritable(true, false);
            return !temporalFolderPath.isEmpty() ? folder
                     : null;
        } catch (NullPointerException n) {
            LOGGER.error("ERROR: TemporalFolderBuilderImpl - getTemporalFolder - No existe la ruta de la carpeta temporal.");
            throw ExceptionBuilder.newBuilder()
                    .withCode(CodigoError.FAIL_CREATE_FOLDER)
                    .withMessage("No existe la ruta de la carpeta temporal.")
                    .buildSystemException();
        } catch (RuntimeException r) {
            LOGGER.error("ERROR: TemporalFolderBuilderImpl - getTemporalFolder - No existe la propiedad que define la ruta de la carpeta temporal, verifique la configuración de la ruta");
            throw ExceptionBuilder.newBuilder()
                    .withCode(CodigoError.FAIL_CREATE_FOLDER)
                    .withMessage("No existe la propiedad que define la ruta de la carpeta temporal, verifique la configuración de la ruta.")
                    .buildSystemException();
        }
    }

    private String findTemporalPath() throws RuntimeException {
        String folderPath = AlfrescoBundleUtil.getProperty("host.folder");
        return folderPath;
    }

    private String createTemporalFolder(String uuid, String temporalPath) throws NullPointerException {

        String path = temporalPath.concat(uuid).concat(File.separator);
        File folder = new File(path);
        folder.setExecutable(true, false);
        folder.setReadable(true, false);
        folder.setWritable(true, false);
        return folder.getAbsolutePath();

    }

}