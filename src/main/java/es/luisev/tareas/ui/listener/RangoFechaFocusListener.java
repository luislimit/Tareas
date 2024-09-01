/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.listener;

import com.toedter.calendar.JDateChooser;
import es.luisev.tareas.ui.DialogoBase;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class RangoFechaFocusListener implements FocusListener {

    private final JTextField txtField;
    private final JDateChooser dateChooser;
    private final DialogoBase dialog;

    public RangoFechaFocusListener(DialogoBase dialog, JTextField txtField) {
        this.dialog = dialog;
        this.txtField = txtField;
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy"); // Formato de la fecha

        // Escuchar cambios en el JDateChooser para actualizar el JTextField
        dateChooser.getDateEditor().addPropertyChangeListener("date", evt -> {
            Date selectedDate = (Date) evt.getNewValue();
            if (selectedDate != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                txtField.setText(dateFormat.format(selectedDate));
            }
            dateChooser.setVisible(false);
            dialog.remove(dateChooser);
        });
    }

    @Override
    public void focusGained(FocusEvent e) {
        // Mostrar el calendario en el punto del JTextField
        Point location = dialog.getLocation();
        dateChooser.setBounds(location.x, location.y + txtField.getHeight(), 200, 200);
        dateChooser.setVisible(true);

        // Agregar el calendario al DialogoBase
        dialog.add(dateChooser);
        dialog.repaint(); // Refrescar el diálogo para asegurar que el calendario se muestre correctamente
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Ocultar el calendario si el JTextField pierde el foco
        dateChooser.setVisible(false);
        dialog.remove(dateChooser);
        dialog.repaint(); // Refrescar el diálogo para reflejar la eliminación del calendario
    }

}
