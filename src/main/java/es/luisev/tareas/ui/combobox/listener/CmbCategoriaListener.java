/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.combobox.listener;

import es.luisev.tareas.model.Categoria;
import es.luisev.tareas.model.Peticion;
import es.luisev.tareas.model.SubCategoria;
import es.luisev.tareas.ui.combobox.model.CmbPeticionModel;
import es.luisev.tareas.ui.combobox.model.CmbSubCategoriaModel;
import es.luisev.tareas.utils.AppHelper;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class CmbCategoriaListener implements ItemListener {

    private final JComboBox cmbSubCategoria;

    public CmbCategoriaListener(JComboBox cmbSubCategoria) {
        super();
        this.cmbSubCategoria = cmbSubCategoria;
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        Categoria categoria = (Categoria) e.getItem();
        List<SubCategoria> subCategorias = AppHelper.getSubCategoriaService().findByCategoria(categoria);
        cmbSubCategoria.setModel(new CmbSubCategoriaModel(subCategorias));
        if (subCategorias.size() <= 1) {
            cmbSubCategoria.setSelectedIndex(subCategorias.size());
        }
    }
}
