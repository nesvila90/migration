package co.gov.sic.migration.commons.utils.converter;

import co.gov.sic.migration.service.state.execution.business.EjecucionBoundary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AplicacionEstadoConverterTest {

    @Autowired
    private EjecucionBoundary ejecucionBoundary;

    @Test
    public void aplicacionEstadoEntityToDto() {
    }

    @Test
    public void aplicacionEstadoDtoToEntity() {
    }

    @Test
    public void aplicacionEstadoDtoToGeneralStatus() {
    }
}