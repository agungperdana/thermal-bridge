package com.kratonsolution.tools.thermalbridge;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.kratonsolution.tools.thermalbridge.service.Handler;
import com.kratonsolution.tools.thermalbridge.ui.MainWindow;

import io.undertow.Undertow;

@SpringBootApplication
public class EntryPoint implements ApplicationRunner {
	
	public static void main(String[] args) {
		
		new SpringApplicationBuilder(EntryPoint.class).headless(false).run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Undertow.builder().addHttpListener(8080,"localhost").setHandler(new Handler()).build().start();
		new MainWindow();
	}
}