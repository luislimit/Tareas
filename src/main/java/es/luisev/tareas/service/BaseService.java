/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.service;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author Luis-Enrique.Varona
 */
public class BaseService {

    public boolean isInRange(Long valor, Long desde, Long hasta) {
        return isInRange(
                valor != null ? valor.doubleValue() : null,
                desde != null ? desde.doubleValue() : null,
                hasta != null ? hasta.doubleValue() : null
        );
    }

    public boolean isInRange(Double valor, Double desde, Double hasta) {
        return (valor == null) || (desde == null && hasta == null)
                || (desde == null && hasta != null && Double.compare(valor, hasta) <= 0)
                || (desde != null && hasta == null && Double.compare(valor, desde) >= 0)
                || (Double.compare(valor, desde) >= 0 && Double.compare(valor, hasta) <= 0);
    }
    
    public static String getFechaAlta(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }
}
