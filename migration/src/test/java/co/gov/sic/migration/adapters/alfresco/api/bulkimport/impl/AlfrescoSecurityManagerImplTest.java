package co.gov.sic.migration.adapters.alfresco.api.bulkimport.impl;

import co.gov.sic.migration.adapters.alfresco.api.AlfrescoSecurityManager;
import co.gov.sic.migration.adapters.alfresco.domain.request.LoginRequest;
import co.gov.sic.migration.commons.utils.serializer.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ComponentScan
public class AlfrescoSecurityManagerImplTest {

    @Autowired
    private AlfrescoSecurityManager alfrescoSecurityManager;

    @Test
    public void logIn() {
        LoginRequest loginRequest = LoginRequest.builder().username("admin").password("alfresco2018").build();
        System.out.println("Marshall Object: "+ JSONUtil.marshal(loginRequest));
        System.out.println("Resultado login:" + alfrescoSecurityManager.logIn(loginRequest));

    }
}