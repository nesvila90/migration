package co.gov.sic.migration.service.migration.loader.impl;

import co.gov.sic.migration.commons.domains.generic.BatchFiltered;
import co.gov.sic.migration.commons.domains.generic.Document;
import co.gov.sic.migration.commons.domains.generic.GeneralStatus;
import co.gov.sic.migration.commons.domains.generic.Workspace;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.commons.utils.GenerarConsecutivoUtil;
import co.gov.sic.migration.persistence.entities.Metadatos;
import co.gov.sic.migration.service.migration.loader.MigrationLoader;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@EnableConfigurationProperties
@ComponentScan
public class MigrationLoaderImplTest {

    @Autowired
    MigrationLoader migrationLoader;

    @Test
    public void prepareBatchMigrationOnStage() throws SystemException {

        String uuid = GenerarConsecutivoUtil.generarCosecutivo();
        List<BatchFiltered<Metadatos, Workspace, Document>> docs =
                migrationLoader.createMigrationBatch(1, uuid);
        GeneralStatus path = migrationLoader.prepareBatchMigrationOnStage(docs, uuid);

        System.out.println("Ruta temporal" + path.toString());


    }

    @Test
    public void createMigrationBatch() {
    }
}