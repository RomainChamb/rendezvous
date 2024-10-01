package fr.barbebroux.rendezvous;


import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("fr/barbebroux/rendezvous")
@CucumberContextConfiguration
@SpringBootTest()
class RendezvousApplicationTests {

	@Test
	void contextLoads() {
	}

}
