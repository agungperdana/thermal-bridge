/**
 * 
 */
package com.kratonsolution.tools.thermalbridge.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.kratonsolution.tools.thermalbridge.ui.model.Config;

/**
 * @author agung
 *
 */
public class ConfigService {

	private Gson gson = new Gson();

	public static final String FILE_NAME = "config.json";

	public Config load() {

		System.out.println(new File(".").getAbsolutePath());
		
		Config config = null;

		try {

			config = gson.fromJson(new FileReader(FILE_NAME), Config.class);			
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(config == null) {

			config = new Config();
			write(config);
		}
		
		return config;
	}

	public void update(Config config) {
		write(config);
	}
	
	private void write(Config config) {
		
		try {
			
			FileWriter writer = new FileWriter(FILE_NAME);
			
			gson.toJson(config, writer);

			writer.flush();
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
