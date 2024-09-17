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
        String plantilla = null;
        String directory = null;
        String fileName = null;

        try {
            switch (pantalla.getCmbTipo().getSelectedIndex()) {
                case 0 -> { // Export estandar
                    plantilla = AppHelper.getConfigurationValue("template.peticiones");
                    directory = AppHelper.getConfigurationValue("directory.export.peticiones");
                    fileName = AppHelper.getConfigurationValue("prefix.export.peticiones");
                    pantalla.getTxtPlantilla().setEditable(true);
                }

                case 1 -> { // Export Informe de seguimiento
                    plantilla = AppHelper.getConfigurationValue("template.informe");
                    directory = AppHelper.getConfigurationValue("directory.export.informe");
                    fileName = AppHelper.getConfigurationValue("prefix.export.informe");
                    pantalla.getTxtPlantilla().setEditable(true);
                }
                case 2 -> { //Importar
                    directory = AppHelper.getConfigurationValue("directory.import");
                    pantalla.getTxtPlantilla().setEditable(false);
                }
            }
        } catch (TareasApplicationException ex) {
            Logger.getLogger(CmbTipoExpImpListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        pantalla.getTxtPlantilla().setText(plantilla);
        pantalla.getTxtFichero().setText(directory + (fileName == null?null :fileName + "_" + AppHelper.getFechaAltaBd() + ".xlsx"));
    }
}
