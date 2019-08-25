package co.gov.sic.migration.commons.constants;

public interface AlfrescoConstants {

    String INITIATE_ENDPOINT = "bulk/import/initiate.json?";
    String STATUS_ENDPOINT = "bulk/import/status.json";
    String RESUME_ENDPOINT = "bulk/import/resume.json";
    String PAUSE_ENDPOINT = "bulk/import/pause.json";
    String STOP_ENDPOINT = "bulk/import/stop.json";

    String DEFAULT_TARGET_PATH_IMPORT = "targetPath=/";
    String DEFAULT_BEAN_MIGRATION = "&sourceBeanId=bit.fs.source";
    String SOURCE_DIRECTORY = "&sourceDirectory=";
    String REPLACE_EXISTING = "&replaceExisting=";

}
