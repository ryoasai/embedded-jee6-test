package com.github.ryoasai.embeddedjee6.ejb;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EJB test using standard JavaEE 6 embedded EJB container API.
 * 
 * @author Ryo
 */
public class EmbeddedEJBWithGlassfishTest {
	Logger log = LoggerFactory.getLogger(EmbeddedEJBWithGlassfishTest.class);
	Map<String, Object> containerProps = new HashMap<String, Object>();

	EJBContainer container;

	@Before
	public void setUp() throws Exception {
		containerProps.put(EJBContainer.MODULES, getDeployTargets());
		container = EJBContainer.createEJBContainer(containerProps);
	}

	private File[] getDeployTargets() {
		File baseDir = new File(System.getProperty("user.dir"));

		File[] files = { 
			new File(baseDir, "../embedded-jee6-test/target/classes"), 
		};

		return files;
	}

	@After
	public void tearDown() throws Exception {
		if (container != null) {
			container.close();
		}

		log.debug("EmbeddedTest completed.");
	}

	@Test
	public void test() throws Exception {
		Context ic = container.getContext();

		log.debug("Looking up HelloService EJB.");
		HelloService helloService = (HelloService) ic
				.lookup("java:global/classes/HelloService");
		log.debug("Invoking HelloService [" + helloService + "]");

		assertThat(helloService.sayHello("Test"), is("Hello Test!"));
		log.debug("HelloService tested successfully");
	}
}
