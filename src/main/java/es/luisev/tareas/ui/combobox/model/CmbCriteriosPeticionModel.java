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
public class CmbCriteriosPeticionModel extends DefaultComboBoxModel<String> {

    /**
     *
     */
    private static final long serialVersionUID = -3700195174395455435L;

    public CmbCriteriosPeticionModel() {
        super();
        rellena("");
        rellena("Sin horas imputadas"); // 
        rellena("Margen de Imputacion (Estimadas > Reales)"); // 
        rellena("Horas sobrepasadas (Reales > Estimadas)"); // 
        rellena("Fecha Inicio atrasada (Inicio Estimado < Inicio Real)"); // 
        rellena("Fecha Fin Real atrasada (Fin Estimado < Fin Real)"); // 
    }

    private void rellena(String clave) {
        String texto = clave;
        if (!texto.isEmpty()){
            texto = UIHelper.getLiteral(texto);
        }
        addElement(texto);
    }
}
