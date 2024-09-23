/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.service;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class ServiceSupport {

    public static String getLikeString(String texto) {
        String likeString = texto;
        if (texto == null || texto.isEmpty()) {
            return null;
        }
        if (!texto.contains("%")) {
            likeString = "%" + likeString + "%";
        }
        return likeString;
    }
}
