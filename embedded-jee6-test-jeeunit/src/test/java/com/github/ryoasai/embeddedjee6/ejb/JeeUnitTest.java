package com.github.ryoasai.embeddedjee6.ejb;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.jeeunit.JeeunitRunner;

/**
 * EJB test using jeeunit.
 * 
 * @author Ryo
 */
@RunWith(JeeunitRunner.class)
public class JeeUnitTest {

	Logger log = LoggerFactory.getLogger(JeeUnitTest.class);
	
	@Inject
	HelloService helloService;

	@Test
	public void test() throws Exception {
		assertThat(helloService.sayHello("Test"), is("Hello Test!"));
		log.debug("HelloService tested successfully");
	}
}
