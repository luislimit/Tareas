/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.listener;

import es.luisev.tareas.ui.VisorForm;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class VisorMouseListener implements MouseListener {

    private final VisorForm pantalla;

    public VisorMouseListener(VisorForm pantalla) {
        this.pantalla = pantalla;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if (obj instanceof JTable) {
            JTable tab = (JTable) obj;
            if (tab.getSelectedRow() == -1) {
                pantalla.getBtnEditar().setEnabled(false);
                pantalla.getBtnBorrar().setEnabled(false);
                return;
            }
            pantalla.getBtnEditar().setEnabled(true);
            pantalla.getBtnBorrar().setEnabled(true);
        }
        if (e.getClickCount() == 2) {
            pantalla.getVisorListener().evtBtnEditar();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
