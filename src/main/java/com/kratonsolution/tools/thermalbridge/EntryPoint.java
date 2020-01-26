package com.kratonsolution.tools.thermalbridge;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kratonsolution.tools.thermalbridge.service.Handler;

import io.undertow.Undertow;

@SpringBootApplication
public class EntryPoint implements ApplicationRunner {
	
	public static void main(String[] args) {
		
		SpringApplication.run(EntryPoint.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println("Thermal Bridge application version 1.0");
		System.out.println("Starting web server, listening on port 8080 .....");
		
		Undertow.builder().addHttpListener(8080,"localhost").setHandler(new Handler()).build().start();
	}
}