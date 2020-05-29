package com.server;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 
 *	@author Andrey Cherdakov
 */

@SpringBootApplication
public class ServerApp
{
	@SuppressWarnings("resource")
	public static void main( String[] args ) throws InterruptedException
    {
    	/*
    	 * Run application
    	 */
    	ConfigurableApplicationContext ctx = new SpringApplicationBuilder().sources(ServerApp.class).run(args);
        System.out.println("Press 'Enter' to terminate");
        new Scanner(System.in).nextLine();
        System.out.println("Exit");
        /*
         * Exit the program when the user pressed enter
         */
        SpringApplication.exit(ctx, () -> 0);
    }

}
