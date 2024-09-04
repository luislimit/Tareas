/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.utils;

import com.toedter.calendar.JDateChooser;
import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.DBEntity;
import es.luisev.tareas.ui.DialogoBase;
import es.luisev.tareas.ui.table.cabecera.Cabecera;
import es.luisev.tareas.ui.table.model.DefaultTableModel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class UIHelper {

    /**
     * @param component
     */
    public static void centerOnScreen(java.awt.Window component) {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - component.getWidth()) / 2;
        final int y = (screenSize.height - component.getHeight()) / 2;
        component.setLocation(x, y);
    }

    /**
     * @param component
     */
    public static void show(java.awt.Window component) {
        if (!Objects.isNull(component)) {
            centerOnScreen(component);
            component.setVisible(Boolean.TRUE);
        }
    }

    public static Object showDialog(DialogoBase dialogo) {
        show(dialogo);
        return dialogo.getReturnObject();
    }

    public static void selectComboBoxById(JComboBox comboBox, DBEntity dBEntity) {
        if (dBEntity == null || dBEntity.getId() == null) {
            comboBox.setSelectedIndex(-1);
            return;
        }
        comboBox.setSelectedItem(dBEntity);
    }

    public static boolean confirmAction(Component component, String clave, String... params) {
        String titulo = getLiteral("confirmacion.titulo");
        String message = getLiteral(clave, params);
        int option = JOptionPane.showConfirmDialog(component, message, titulo, JOptionPane.YES_NO_OPTION);
        return (option == JOptionPane.YES_OPTION);
    }

    public static String getLiteral(String key, String... params) {
        String texto = null;
        try {
            texto = LiteralesSingleton.getInstance().getLiteral(key);
        } catch (TareasApplicationException ex) {
            Logger.getLogger(UIHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (texto == null || texto.isEmpty()) {
            texto = key;
        }
        // Recorrer el array usando sus índices
        for (int i = 0; i < params.length; i++) {
            String id = ":" + String.valueOf(i+1);
            texto = texto.replace(id, params[i]);
        }
        return texto;
    }

    public static void setWindowTitle(Component window) {
        String clave = window.getClass().getSimpleName();
        String titulo = getLiteral(clave);
        if (window instanceof JDialog) {
            ((JDialog) window).setTitle(titulo);
        } else {
            ((JFrame) window).setTitle(titulo);
        }
    }

    public static void setComponentText(Component component, String clave) {
        if (component == null) {
            return;
        }
        String texto = getLiteral(clave);
        if (component instanceof JLabel) {
            ((JLabel) component).setText(texto);
        } else {
            ((AbstractButton) component).setText(texto);
        }
    }

    /**
     * Muestra un mensaje por pantalla
     *
     * @param component
     * @param clave
     */
    public static void showMessage(Component component, String clave) {
        JOptionPane.showMessageDialog(component, getLiteral(clave));
    }

    /**
     * Muestra un mensaje por pantalla
     *
     * @param component
     * @param clave
     * @param params
     */
    public static void showMessage(Component component, String clave, String... params) {
        JOptionPane.showMessageDialog(component, getLiteral(clave, params));
    }

    public static void showErrors(Component component, Exception e) {
        JOptionPane.showMessageDialog(component, e.getMessage());
    }

    public static void setTableModel(JTable table, DefaultTableModel tableModel) {
        table.setModel(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Cabecera cabecera = tableModel.getCabecera();
        // Dar el ancho a las columnas de la tabla
        for (int i = 0; i < cabecera.getColumnSizes().size(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(cabecera.getColumnSizes().get(i));
        }
    }

    public static Long getDateDB(JDateChooser dateChooser) {
        Date date = dateChooser.getDate();
        if (date == null) {
            return null;
        }
        return date.getTime();
    }

    public static void setDateChooserValue(JDateChooser dchField, Long valor) {
        dchField.setDate((valor == null) ? null : new Date(valor));
    }

    public static Double getDouble(JTextField textField) {
        if (textField == null || textField.getText().isEmpty()) {
            return null;
        }
        String texto = textField.getText();
        return Double.parseDouble(texto);
    }
}
