package com.github.ryoasai.embeddedjee6.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class HelloService {

	@Inject
	Hello hello;
	
    public String sayHello(String name) {
    	System.out.println("***************************");

    	System.out.println("hello = " + hello);
        return "Hello " + name + "!";
    }
}
