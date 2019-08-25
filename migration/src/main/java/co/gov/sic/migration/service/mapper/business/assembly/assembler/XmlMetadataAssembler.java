package co.gov.sic.migration.service.mapper.business.assembly.assembler;

import co.gov.sic.migration.commons.exceptions.SystemException;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Map;

public interface XmlMetadataAssembler {

    File assembler(Map<String, Object> metadata, String filePath) throws SystemException, MalformedURLException, UnsupportedEncodingException;

}
