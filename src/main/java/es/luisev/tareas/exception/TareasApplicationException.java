/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.exception;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class TareasApplicationException extends Exception{
    
    public TareasApplicationException(String message){
          super(message);
    }
    
    public static void raise(String message) throws TareasApplicationException{
        throw new TareasApplicationException(message);
    }
}
