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
public class LiteralesSingleton {
    
    private static LiteralesSingleton instance;
    
    private final Properties properties;
    
    private LiteralesSingleton() throws TareasApplicationException {
        properties = new Properties();

        //the base folder is ./, the root of the main.properties file  
        String literalesPath = "./literales.properties";
        
        try (FileInputStream fistream = new FileInputStream(literalesPath)) {
            properties.load(new InputStreamReader(fistream, StandardCharsets.ISO_8859_1));
        } catch (IOException e) {
            TareasApplicationException.raise(e.getMessage());
        }
    }
    
    public static LiteralesSingleton getInstance() throws TareasApplicationException {
        if (instance == null) {
            instance = new LiteralesSingleton();
        }
        return instance;
    }

    // Permitir el refresh de los literales 
    public static LiteralesSingleton getNewInstance() throws TareasApplicationException {
        instance = new LiteralesSingleton();
        return instance;
    }

    /**
     * @param key
     * @return
     */
    public String getLiteral(String key) {
        return properties.getProperty(key);
    }
}
