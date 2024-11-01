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
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
            String id = ":" + String.valueOf(i + 1);
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
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Cabecera cabecera = tableModel.getCabecera();
        // Dar el ancho a las columnas de la tabla
        for (int i = 0; i < cabecera.getColumnSizes().size(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(cabecera.getColumnSizes().get(i));
        }
    }

    public static String getDateDB(JDateChooser dateChooser) {
        Date date = dateChooser.getDate();
        return AppHelper.fromDateToFechaDb(date);
    }

    public static void setDateChooserValue(JDateChooser dchField, String valor) throws TareasApplicationException {
        dchField.setDate((valor == null) ? null : AppHelper.fromFechaDbToDate(valor));
    }

    public static Double getDouble(JTextField textField) {
        if (textField == null || textField.getText().isEmpty()) {
            return null;
        }
        String texto = textField.getText();
        return Double.parseDouble(texto);
    }

    public static JFileChooser getJFileChooser(String rutaInicial) throws IOException {
        File file = new File(rutaInicial);

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(getLiteral("dialogo.select.file.title"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (file.isDirectory()) {
            chooser.setCurrentDirectory(file);
        } else {
            chooser.setSelectedFile(file);
        }
        chooser.setAcceptAllFileFilterUsed(false);

        return chooser;
    }

    public static File showDialogOpenFile(Component component, String rutaInicial) throws IOException {
        JFileChooser chooser = getJFileChooser(rutaInicial);
        int state = chooser.showOpenDialog(component);
        if (state == JFileChooser.APPROVE_OPTION) { // si elige guardar en el archivo
            return chooser.getSelectedFile();
        }
        return null;
    }

    public static File showDialogNewFile(Component component, String rutaInicial) throws IOException {
        //Si no se indica la ruta nos posicionamos en la ruta de inicio de la aplicacion
        if (rutaInicial == null || rutaInicial.isBlank()){
            rutaInicial = System.getProperty("user.dir");
        }
        JFileChooser chooser = getJFileChooser(rutaInicial);
        int state = chooser.showSaveDialog(component);
        if (state == JFileChooser.APPROVE_OPTION) { // si elige guardar en el archivo
            return chooser.getSelectedFile();
        }
        return null;
    }

    public static Object getTableSelection(JTable table) {
        int selectedRow = table.getSelectedRow();
        return selectedRow == -1? null: ((DefaultTableModel) table.getModel()).getSelectedRow(selectedRow);
    }    
}
