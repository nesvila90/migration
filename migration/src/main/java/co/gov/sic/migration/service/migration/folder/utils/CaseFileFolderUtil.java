package co.gov.sic.migration.service.migration.folder.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CaseFileFolderUtil {

    private static final Logger LOGGER = LogManager.getLogger(CaseFileFolderUtil.class);

    private CaseFileFolderUtil(){}

    public static final String nameBuilder(final String idCaseFile, final String caseFileName){
        LOGGER.info("EXECUTE: CaseFileFolderUtil - nameBuilder - Parameters - idCaseFile: {} - caseFileName: {} ", idCaseFile ,caseFileName);
        String path = new StringBuilder().append(idCaseFile).append("-").append(caseFileName).toString().trim();
        LOGGER.info("EXECUTE: CaseFileFolderUtil - nameBuilder  PATH GENERATED: {} ", path);
        return path;
    }
}
