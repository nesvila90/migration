package co.gov.sic.migration.service.migration.folder;

import co.gov.sic.migration.commons.exceptions.SystemException;

import java.io.File;

public interface TemporalFolderBuilder {

    File getTemporalFolder(String uuid) throws SystemException;

}
