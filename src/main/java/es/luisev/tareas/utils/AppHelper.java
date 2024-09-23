/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.utils;

import es.luisev.tareas.exception.TareasApplicationException;
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
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class AppHelper {

    public static Object getBean(String name) {
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

    /**
     * Recibe una cadena en formato YYYYMMDD, retorna DD/MM/YYYY
     *
     * @param strFecha
     * @return
     */
    public static String dateBdToString(String strFecha) {
        if (strFecha == null) {
            return null;
        }
        // Extraer partes de la fecha usando substring
        String dia = strFecha.substring(6, 8);  // DD
        String mes = strFecha.substring(4, 6);  // MM
        String año = strFecha.substring(0, 4);  // YYYY

        // Formatear la fecha en DD/MM/YYYY
        return dia + "/" + mes + "/" + año;
    }

    public static String fromDateToFechaDb(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public static String getFechaAltaBd() {
        return fromDateToFechaDb(new Date());
    }

    public static Date fromFechaDbToDate(String dateDb) throws TareasApplicationException {
        if (dateDb == null) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            return sdf.parse(dateDb);
        } catch (ParseException e) {
            throw new TareasApplicationException(e.getMessage());
        }
    }

    public static Usuario getDefaultUser() {
        AppGlobalSingleton appGlobalSingleton = AppGlobalSingleton.getInstance();
        return (Usuario) appGlobalSingleton.getProperty(Constantes.DEFAULT_USER);
    }

    public static String getConfigurationValue(String clave) throws TareasApplicationException {
        ConfigurationSingleton configSingleton = ConfigurationSingleton.getInstance();
        String valor = configSingleton.getValue(clave);
        if (valor == null || valor.isEmpty()) {
            throw new TareasApplicationException("No existe la clave [" + clave + "] en el fichero de configuración");
        }
        return valor;
    }

}
