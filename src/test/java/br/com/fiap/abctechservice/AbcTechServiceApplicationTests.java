package br.com.fiap.abctechservice;

import br.com.fiap.abctechservice.service.HealthCheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AbcTechServiceApplicationTests {

	@Autowired
	private HealthCheckService healthCheckService;

	@Test
	void contextLoads() {
	}

	@Test
	public void getVersion() {
		System.out.println(healthCheckService.getVersion());
	}

}
