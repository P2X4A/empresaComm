package co.edu.unbosque.model.persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;

public class FileHandler {

	public static Properties loadSystemProperties() throws IOException{
		Properties propiedadesSistema = new Properties();
		propiedadesSistema.load(new FileInputStream(new File("PropiedadesCOM.properties")));
		return propiedadesSistema;
	}
		
	
}
