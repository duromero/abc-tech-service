package br.com.fiap.abctechservice.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HealthCheckServiceTests {

    @Autowired
    private HealthCheckComponent healthCheckComponent;

    @Test
    public void testingGetVersion() {
        Assertions.assertNotNull(healthCheckComponent.getVersion());
    }

    @Test
    public void testingGetName() {
        Assertions.assertNotNull(healthCheckComponent.getName());
    }

    @Test
    public void testingGetNamePlusVersion() {
        Assertions.assertNotNull(healthCheckComponent.getNamePlusVersion());
    }
}
