package co.gov.sic.migration;

import co.gov.sic.migration.adapters.alfresco.cmis.dao.MigrateLocation;
import co.gov.sic.migration.adapters.alfresco.domain.request.CmisDTO;
import co.gov.sic.migration.commons.exceptions.SystemException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@EnableConfigurationProperties
@ComponentScan
public class CmisTest {

    @Autowired
    private MigrateLocation migrateLocation;

    @Test
    public void cmisGeneric() {
        CmisDTO locationParam = CmisDTO.builder()
                .codigoDependecia("7100")
                .codigoUnidadAdministrativaPadre("12")
                .serie("12")
                .subserie("9")
                .build();

        CmisDTO locationParam2 = CmisDTO.builder()
                .codigoDependecia("0141")
                .codigoUnidadAdministrativaPadre("0140")
                .serie("0141-12")
                .subserie("0141-12-05")
                .build();

        try {
            System.out.println("Resultado con Subserie" + migrateLocation.findLocationDocument(locationParam));
            System.out.println("Resultado sin Subserie" + migrateLocation.findLocationDocument(locationParam2));
        } catch (SystemException e) {
            e.printStackTrace();
        }

    }
}
