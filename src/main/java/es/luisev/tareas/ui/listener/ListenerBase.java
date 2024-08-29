/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.listener;

import es.luisev.tareas.ui.DialogoBase;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

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
        
        if (!(obj instanceof JButton)){
            return;
        }
        String textBtn = ((JButton)obj).getText();

        if (textBtn.equals(dialogo.getBtnLimpiar().getText())) {
            evtLimpiar();
        } else if (textBtn.equals(dialogo.getBtnGuardar().getText())){
            if (evtGuardar()) {
                dialogo.dispose();
            }
        } else if (textBtn.equals(dialogo.getBtnCancelar().getText())) {
            evtCancelar();
        }
    }

    public abstract void evtLimpiar();

    public abstract boolean evtGuardar();

    public void evtCancelar() {
        dialogo.dispose();
    }
}
