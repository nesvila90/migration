package co.gov.sic.migration;

import co.gov.sic.migration.adapters.alfresco.api.bulkimport.impl.AlfrescoEndpointManagerImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@ComponentScan
public class EnpointTest {


    @Autowired
    private AlfrescoEndpointManagerImpl alfrescoEndpointManager;

    @Test
    public void status(){
        System.out.println("Resultado Test Importacion" + alfrescoEndpointManager.bulkImportStatus());
    }

}
