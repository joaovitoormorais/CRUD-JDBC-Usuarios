package aula2.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropeteries {
	
	//Implementar o padrão singleton
	
	public static Properties loadProperties() {
		Properties prop = new Properties();
		
		ClassLoader classLoader = ReadProperties.class.getClassLoader();
		
		// Caminho para o arquivo de propriedades que está na pasta:
		//resources/application.properties
		String fileName = "aplication.properties";
		
		try(InputStream input = ClassLoader.getSystemResourceAsStream(fileName)) {
			prop.load(input);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}

}
