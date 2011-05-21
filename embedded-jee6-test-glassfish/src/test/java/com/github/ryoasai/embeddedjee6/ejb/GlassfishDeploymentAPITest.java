package com.github.ryoasai.embeddedjee6.ejb;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.net.URI;

import javax.naming.InitialContext;

import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishRuntime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EJB test using Glassfish specific API.
 * 
 * @author Ryo
 */
public class GlassfishDeploymentAPITest {
	GlassFish glassfish;

	Logger log = LoggerFactory.getLogger(GlassfishDeploymentAPITest.class);
	
	private File getDeployTarget() {
		return new File(System.getProperty("user.dir")
				+ "/../embedded-jee6-test", "/target/classes");
	}
	
	@Before
	public void setUp() throws Exception {
		glassfish = GlassFishRuntime.bootstrap().newGlassFish();
		glassfish.start();

		Deployer deployer = glassfish.getDeployer();
		URI uri = getDeployTarget().toURI();
		log.debug("Deploying [" + uri + "]");
		deployer.deploy(uri);
	}

	@After
	public void tearDown() throws Exception {
		glassfish.stop();
		glassfish.dispose();

		log.debug("EmbeddedTest completed.");
	}

	@Test
	public void test() throws Exception {
		InitialContext ic = new InitialContext();

		log.debug("Looking up HelloService EJB.");
		HelloService helloService = (HelloService) ic
				.lookup("java:global/classes/HelloService");
		log.debug("Invoking HelloService [" + helloService + "]");

		assertThat(helloService.sayHello("Test"), is("Hello Test!"));
		log.debug("HelloService tested successfully");
	}
}
