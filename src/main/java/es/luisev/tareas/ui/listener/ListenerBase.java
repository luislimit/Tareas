/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.listener;

import es.luisev.tareas.ui.DialogoBase;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Luis-Enrique.Varona
 */
public abstract class ListenerBase implements ActionListener {

    protected DialogoBase dialogo;

    public ListenerBase(DialogoBase dialogo) {
        this.dialogo = dialogo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(dialogo.getBtnLimpiar())) {
            evtLimpiar();
        } else if (obj.equals(dialogo.getBtnGuardar())) {
            if (evtGuardar()) {
                dialogo.dispose();
            }
        } else if (obj.equals(dialogo.getBtnCancelar())) {
            evtCancelar();
        }
    }

    public abstract void evtLimpiar();

    public abstract boolean evtGuardar();

    public void evtCancelar() {
        dialogo.dispose();
    }
}
