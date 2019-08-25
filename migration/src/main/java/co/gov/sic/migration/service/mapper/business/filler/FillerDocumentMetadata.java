package co.gov.sic.migration.service.mapper.business.filler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Map;

public class FillerDocumentMetadata {

    private static final Logger LOGGER = LogManager.getLogger(FillerDocumentMetadata.class);

    private FillerDocumentMetadata() { }

    public static Document createMetadataPropertiesFile(Map<String, Object> contentMetadataFile)
            throws ParserConfigurationException {

        LOGGER.info("EXECUTE: FillerDocumentMetadata - createMetadataPropertiesFile - Parameters - contentMetadataFile: {}", contentMetadataFile.toString());

        final String ENTRY = "entry";
        final String KEY = "key";

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element rootElement = document.createElement("properties");
        document.appendChild(rootElement);

        for (Map.Entry<String, Object> metadata : contentMetadataFile.entrySet()) {
            if (metadata != null && metadata.getValue() != null && !metadata.getValue().equals("") && !metadata.getValue().toString().trim().isEmpty() ) {
                LOGGER.info("EXECUTE: FillerDocumentMetadata - Filling Map Metadata - KEY: {} - VALUE: {}", metadata.getKey(), metadata.getValue().toString());
                Element element = document.createElement(ENTRY);
                Attr attribute = document.createAttribute(KEY);
                attribute.setValue(metadata.getKey());
                element.setAttributeNode(attribute);
                element.appendChild(document.createTextNode(metadata.getValue().toString()));
                rootElement.appendChild(element);
            }
        }
        return document;

    }


}
