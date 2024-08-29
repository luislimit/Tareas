package es.luisev.tareas.utils;

import es.luisev.tareas.exception.TareasApplicationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author LVARONA
 *
 */
public class ConfigurationSingleton {
    
    private static ConfigurationSingleton instance;
    
    private final Properties properties;
    
    private ConfigurationSingleton() throws TareasApplicationException {
        properties = new Properties();

        //the base folder is ./, the root of the main.properties file  
        String literalesPath = "./configuration.properties";
        
        try (FileInputStream fistream = new FileInputStream(literalesPath)) {
            properties.load(new InputStreamReader(fistream, StandardCharsets.ISO_8859_1));
        } catch (IOException e) {
            TareasApplicationException.raise(e.getMessage());
        }
    }
    
    public static ConfigurationSingleton getInstance() throws TareasApplicationException {
        if (instance == null) {
            instance = new ConfigurationSingleton();
        }
        return instance;
    }

    // Permitir el refresh de los literales 
    public static ConfigurationSingleton getNewInstance() throws TareasApplicationException {
        instance = new ConfigurationSingleton();
        return instance;
    }

    /**
     * @param key
     * @return
     */
    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
