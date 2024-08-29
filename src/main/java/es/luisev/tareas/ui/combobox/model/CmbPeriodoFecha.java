/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.combobox.model;

import es.luisev.tareas.utils.UIHelper;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class CmbPeriodoFecha extends DefaultComboBoxModel<String> {

    /**
     *
     */
    private static final long serialVersionUID = -3700195174395455435L;

    public CmbPeriodoFecha() {
        super();
        rellena("");
        rellena("lblFechaActual"); // Fecha Actual
        rellena("lblSemanaActual"); // Semana Actual
        rellena("lblQuincenaActual"); // Quincena Actual
        rellena("lblMesActual"); // Mes Actual
        rellena("lblAnoActual"); // Año Actual
        rellena("lblAyer"); // Ayer
        rellena("lblSemanaPasada"); // Semana pasada
        rellena("lblMesPasado"); // Mes pasado
        rellena("lblAnoPasado"); // Año pasado
    }

    private void rellena(String clave) {
        String texto = clave;
        if (!texto.isEmpty()){
            texto = UIHelper.getLiteral(texto);
        }
        addElement(texto);
    }
}
