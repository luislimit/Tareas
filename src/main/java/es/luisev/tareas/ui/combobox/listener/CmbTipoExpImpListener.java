/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.combobox.listener;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.ui.ExportImportDialog;
import es.luisev.tareas.utils.AppHelper;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.io.File;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class CmbTipoExpImpListener implements ItemListener {

    ExportImportDialog pantalla;

    public CmbTipoExpImpListener(ExportImportDialog pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String plantilla = "";
        String directory = "";
        String fileName = "";

        pantalla.getTxtFichero().setEditable(true);
        pantalla.getBtnFichero().setEnabled(true);

        try {
            switch (pantalla.getCmbTipo().getSelectedIndex()) {
                case 0 -> { // Export estandar
                    plantilla = AppHelper.getConfigurationValue("template.peticiones");
                    directory = AppHelper.getConfigurationValue("directory.export.peticiones");
                    fileName = AppHelper.getConfigurationValue("prefix.export.peticiones");
                }

                case 1 -> { // Export Informe de seguimiento
                    directory = AppHelper.getConfigurationValue("directory.export.informe");
                    //BUscamos el ultimo informe generado, si no está tomamos la plantilla por defecto
                    File ultimo = ultimoInforme(new File(directory));
                    if (ultimo != null) {
                        plantilla = ultimo.getAbsolutePath();
                    } else {
                        plantilla = AppHelper.getConfigurationValue("template.informe");
                    }
                    directory = AppHelper.getConfigurationValue("directory.export.informe");
                    fileName = AppHelper.getConfigurationValue("prefix.export.informe");
                }
                case 2 -> { //Importar
                    plantilla = AppHelper.getConfigurationValue("directory.import");
                    File ultimo = ultimoInforme(new File(plantilla));
                    if (ultimo != null) {
                        plantilla = ultimo.getAbsolutePath();
                    } 
                    pantalla.getTxtFichero().setEditable(false);
                    pantalla.getBtnFichero().setEnabled(false);
                }
            }
        } catch (TareasApplicationException ex) {
            Logger.getLogger(CmbTipoExpImpListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        pantalla.getTxtPlantilla().setText(plantilla);
        if (!fileName.isEmpty()) {
            fileName = directory + fileName + "_" + AppHelper.getFechaAltaBd() + ".xlsx";
        }
        pantalla.getTxtFichero().setText(fileName);
    }

    private static File ultimoInforme(File carpeta) {
        File[] archivos = carpeta.listFiles();
        if (archivos == null || archivos.length == 0) {
            return null; // Si no hay archivos en la carpeta
        }

        // Usar un comparador para obtener el archivo con el nombre más largo
        return java.util.Arrays.stream(archivos)
                .filter(File::isFile) // Filtrar solo los archivos (excluir directorios)
                .max(Comparator.comparing(f -> f.getName())) // Comparar por el nombre
                .orElse(null); // Si no se encuentra, devolver null
    }
}
