package br.com.fiap.abctechservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HealthCheckServiceTests {

    @Autowired
    private HealthCheckService healthCheckService;

    @Test
    public void testingGetVersion() {
        Assertions.assertNotNull(healthCheckService.getVersion());
    }

    @Test
    public void testingGetName() {
        Assertions.assertNotNull(healthCheckService.getName());
    }

    @Test
    public void testingGetNamePlusVersion() {
        Assertions.assertNotNull(healthCheckService.getNamePlusVersion());
    }
}
