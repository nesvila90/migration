package co.gov.sic.migration;

import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.service.mapper.business.assembly.assembler.XmlMetadataAssembler;
import co.gov.sic.migration.service.mapper.business.filler.FillerDocumentMetadata;
import co.gov.sic.migration.commons.utils.xml.builder.XmlBuilder;
import co.gov.sic.migration.persistence.entities.Ejecucion;
import co.gov.sic.migration.persistence.entities.Metadatos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class XmlTest {

    @Autowired
    private XmlMetadataAssembler xmlMetadataAssembler;

    String filePath;
    Map<String, Object> contentMetadataFile = new HashMap<>();

    private void fillData(){
        Metadatos metadatos = new Metadatos();
        metadatos.setTipoActo("primero");
        metadatos.setAutor("villar");
        metadatos.setNumeroRadicado("12333222");
        metadatos.setTipoDocumento("cm:content");
        metadatos.setRutaArchivo("");
        Ejecucion e = new Ejecucion();
        e.setUuid(UUID.randomUUID().toString());
        String workspace = "workspace:dummy";
        metadatos.setUuid(e.getUuid());
        metadatos.setTipoActo("acto");
        metadatos.setAutor("nestor");
        metadatos.setNumeroRadicado("323323");
        metadatos.setTipoDocumental("tipologia");
        metadatos.setIdregistro(12233223);
        metadatos.setNumeroActo(null);
        metadatos.setTipoActo(null);
        metadatos.setSerie("serieeee");
        metadatos.setSubserie("subserieee");
        metadatos.setEstadoProceso(0);
        String path = "C:\\Users\\User\\Desktop\\".concat("archivo prueba\\");
        String nombreArchivo = "ARCHIVO DE PRUEBA " + UUID.randomUUID().toString();
        String finalFilePath = path.concat(File.separator).concat(new File(nombreArchivo).getName()).concat(MetadatosKey.EXTENSION_FILE_METADATA);
        filePath = finalFilePath;
        //String nombreArchivo = "nóúíéáÉÁmbre-<<<!#$%&()=?¡archivo" + UUID.randomUUID().toString();
        nombreArchivo = nombreArchivo.replaceAll("[\\\\/:*?\"<>|]", "");
        nombreArchivo = nombreArchivo.replaceAll("[\\\\/:*?\"<>|¡=!#$%&]", "");
        contentMetadataFile = new HashMap<>();

        contentMetadataFile.put(MetadatosKey.TYPE, MetadatosKey.NODE_TYPE);
        contentMetadataFile.put(MetadatosKey.TARGET_PATH, workspace);
        contentMetadataFile.put(MetadatosKey.TITLE, nombreArchivo);
        contentMetadataFile.put(MetadatosKey.TIPO_ACTO, metadatos.getTipoActo());
        contentMetadataFile.put(MetadatosKey.NOMBRE_REMITENTE, metadatos.getAutor());
        contentMetadataFile.put(MetadatosKey.NUM_RADICADO, metadatos.getNumeroRadicado());
        contentMetadataFile.put(MetadatosKey.TIPOLOGIA_DOCUMENTAL, metadatos.getTipoDocumental());
        contentMetadataFile.put(MetadatosKey.ID_DOC_PRINCIPAL, metadatos.getIdregistro());
        contentMetadataFile.put(MetadatosKey.NUMERO_REFERIDO, metadatos.getNumeroActo());
        contentMetadataFile.put(MetadatosKey.X_TIPO, metadatos.getTipoActo());
        contentMetadataFile.put(MetadatosKey.UUID, metadatos.getUuid());
        contentMetadataFile.put(MetadatosKey.SERIE, metadatos.getSerie());
        contentMetadataFile.put(MetadatosKey.SUBSERIE, metadatos.getSubserie());
        contentMetadataFile.put(MetadatosKey.ESTADO_PROCESO, metadatos.getEstadoProceso());
        contentMetadataFile.put(MetadatosKey.ID_REGISTRO, metadatos.getIdregistro());

    }

    @Test
    public void testXml() throws ParserConfigurationException, TransformerException, MalformedURLException, UnsupportedEncodingException {
        fillData();
        Document doc = FillerDocumentMetadata.createMetadataPropertiesFile(contentMetadataFile);
        String xmlPath = XmlBuilder.trasnformDocument(filePath, doc, "UTF-8");
        File f = new File(xmlPath);
        System.out.println("Ruta archivo:".concat(xmlPath));
        System.out.println("Existe: " + f.exists());
        System.out.println("Archivos:".concat(f.getAbsolutePath()));
    }

    @Test
    public void assemblerTest() throws UnsupportedEncodingException, MalformedURLException, SystemException {
        fillData();
        System.out.println("RUTA: " + xmlMetadataAssembler.assembler(contentMetadataFile, filePath).getAbsolutePath());
    }


    interface MetadatosKey {

        String TYPE = "type";
        String TITLE = "cm:title";
        String TIPO_ACTO = "cm:tipoActo";
        String NOMBRE_REMITENTE = "cmcor:NombreRemitente";
        String NUM_RADICADO = "cmcor:NroRadicado";
        String TIPOLOGIA_DOCUMENTAL = "cmcor:TipologiaDocumental";
        String ID_DOC_PRINCIPAL = "cmcor:xIdentificadorDocPrincipal";
        String NUMERO_REFERIDO = "cmcor:xNumeroReferido";
        String X_TIPO = "cmcor:xTipo";
        String TARGET_PATH = "targetPath";
        String UUID = "uuid";
        String SERIE = "cmcor:serie";
        String SUBSERIE = "cmcor:subserie";
        String ESTADO_PROCESO = "estadoProceso";
        String EXTENSION_FILE_METADATA = ".metadata.properties.xml";
        String NODE_TYPE = "cm:content";
        String ID_REGISTRO = "idRegistro";

    }
}
