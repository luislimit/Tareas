/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.utils;

import es.luisev.tareas.model.Usuario;
import es.luisev.tareas.service.CategoriaService;
import es.luisev.tareas.service.DocumentoService;
import es.luisev.tareas.service.EstadoService;
import es.luisev.tareas.service.ImputacionService;
import es.luisev.tareas.service.PeticionService;
import es.luisev.tareas.service.SubCategoriaService;
import es.luisev.tareas.service.TipoDocumentoService;
import es.luisev.tareas.service.UsuarioService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class AppHelper{

    public static Object getBean(String name){
        AppGlobalSingleton appGlobalSingleton = AppGlobalSingleton.getInstance();
        ApplicationContext context = (ApplicationContext) appGlobalSingleton.getProperty(Constantes.SPRING_CONTEXT);

        return context.getBean(name);
    }

    public static CategoriaService getCategoriaService() {
        return (CategoriaService) getBean(Constantes.SERVICIO_CATEGORIA);
    }

    public static EstadoService getEstadoService() {
        return (EstadoService) getBean(Constantes.SERVICIO_ESTADO);
    }

    public static ImputacionService getImputacionService() {
        return (ImputacionService) getBean(Constantes.SERVICIO_IMPUTACION);
    } 
    
    public static PeticionService getPeticionService() {
        return (PeticionService) getBean(Constantes.SERVICIO_PETICION);
    } 

    public static SubCategoriaService getSubCategoriaService() {
        return (SubCategoriaService) getBean(Constantes.SERVICIO_SUBCATEGORIA);
    }

    public static UsuarioService getUsuarioService() {
        return (UsuarioService) getBean(Constantes.SERVICIO_USUARIO);
    }  
    
    public static TipoDocumentoService getTipoDocumentoService() {
        return (TipoDocumentoService) getBean(Constantes.SERVICIO_TIPO_DOCUMENTO);
    }      
    
    public static DocumentoService getDocumentoService() {
        return (DocumentoService) getBean(Constantes.SERVICIO_DOCUMENTO);
    }      
        
    public static String dateBdToString(Long fechaBd){
        if (fechaBd == null){
            return null;
        }
        Date date = new Date(fechaBd);
        // Crear un formateador de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // Convertir la fecha a una cadena con el formato deseado
        return sdf.format(date);
    } 
    
    public static Long getDateBD(JTextField textField){
        if (textField == null){
            return null;
        }
        String dateString = textField.getText();
        // Crear un formateador de fecha con el mismo formato que la cadena
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Long fechaBd = null;
        try {
            // Convertir la cadena a un objeto Date
            Date date = sdf.parse(dateString);
            // Convertir a Long
            fechaBd = date.getTime();
        } catch (ParseException e) {}
        return fechaBd;
    }     
    
    public static Double getDouble(JTextField textField){
        if (textField == null || textField.getText().isEmpty()){
            return null;
        }
        String texto = textField.getText();
        return Double.parseDouble(texto);
    }
    
    public static Usuario getDefaultUser(){
        AppGlobalSingleton appGlobalSingleton = AppGlobalSingleton.getInstance();
        return (Usuario) appGlobalSingleton.getProperty(Constantes.DEFAULT_USER);
    }
}
