package co.gov.sic.migration.service.migration.validator.impl;

import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.commons.utils.GenerarConsecutivoUtil;
import co.gov.sic.migration.service.migration.validator.BatchValidator;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@EnableConfigurationProperties
@ComponentScan
public class BatchValidatorImplTest {

    @Autowired
    private BatchValidator batchValidator;


    @Test
    public void createMigrationBatch() throws SystemException {
        /*String uuid = GenerarConsecutivoUtil.generarCosecutivo();
        batchValidator.createMigrationBatch(1, uuid).forEach(m -> {
                    System.out.println("Documento: " + m.getDocument().toString());
                    System.out.println("Workspace: " + m.getWorkspace().toString());
                    System.out.println("File: " + m.getFile().toString());
                }
        );
        */
    }
}