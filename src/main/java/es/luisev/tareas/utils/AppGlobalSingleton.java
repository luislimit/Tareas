/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.utils;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class AppGlobalSingleton {
	
	private static AppGlobalSingleton instance;
	
	@Getter
	@Setter
	private HashMap<String, Object> properties;
	
	private AppGlobalSingleton() {
		properties = new HashMap<>();
	}
	
	public static AppGlobalSingleton getInstance() {
		if (instance == null) {
			instance = new AppGlobalSingleton();
		}
		return instance;
	}
	
	/**
	 * @param key
	 * @return
	 */
	public Object getProperty(String key) {
		return properties.get(key);
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}
}
