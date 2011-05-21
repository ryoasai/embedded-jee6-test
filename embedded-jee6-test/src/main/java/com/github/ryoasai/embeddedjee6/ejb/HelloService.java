package com.github.ryoasai.embeddedjee6.ejb;

import javax.ejb.Stateless;

@Stateless
public class HelloService {

    public String sayHello(String name) {
    	System.out.println("***************************");

        return "Hello " + name + "!";
    }
}
