package co.gov.sic.migration.service.mapper.business.assembly.assembler.impl;

import co.gov.sic.migration.commons.enums.EstadoParametro;
import co.gov.sic.migration.commons.enums.Etapa;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.commons.utils.xml.builder.XmlBuilder;
import co.gov.sic.migration.service.mapper.business.assembly.assembler.XmlMetadataAssembler;
import co.gov.sic.migration.service.mapper.business.filler.FillerDocumentMetadata;
import co.gov.sic.migration.service.state.execution.business.EjecucionBoundary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Map;

@Service
public class XmlMetadataAssemblerImpl implements XmlMetadataAssembler {

    private static final Logger LOGGER = LogManager.getLogger(XmlMetadataAssemblerImpl.class);

    private EjecucionBoundary ejecucionBoundary;

    @Autowired
    public XmlMetadataAssemblerImpl(EjecucionBoundary ejecucionBoundary) {
        this.ejecucionBoundary = ejecucionBoundary;
    }

    @Override
    public File assembler(Map<String, Object> metadata, String filePath) throws SystemException, MalformedURLException, UnsupportedEncodingException {

        try {
            LOGGER.info("EXECUTE: XmlMetadataAssemblyImpl - createStructure - Parameters - metadata: {}, filepath {}", metadata.toString(), filePath);
            Document document = FillerDocumentMetadata.createMetadataPropertiesFile(metadata);
            String xmlFilePath = XmlBuilder.trasnformDocument(filePath, document, "UTF-8");
            LOGGER.info("EXECUTE: XML File Path Generated: {}", xmlFilePath.isEmpty() ? "EMPTY PATH" : xmlFilePath);
            return new File(xmlFilePath);
        } catch (ParserConfigurationException | TransformerException e) {
            LOGGER.error("ERROR: XmlMetadataAssemblyImpl - createStructure - Parameters - metadata: {}, filepath {}", metadata.toString(), filePath);
            LOGGER.error("ERROR: XmlMetadataAssemblyImpl - createStructure - Exception: ", e);
            ejecucionBoundary.updateExecutionState(metadata.get("uuid").toString(), Integer.parseInt(metadata.get("idRegistro").toString()),
                    EstadoParametro.FALLO,
                    "Hubo un error en el proceso de análisis gramatical o trasnformación del documento.",
                    Etapa.INICIADA);
            return null;
        }

    }

}
