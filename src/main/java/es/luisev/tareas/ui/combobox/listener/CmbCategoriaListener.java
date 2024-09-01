/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.combobox.listener;

import es.luisev.tareas.model.Categoria;
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
    private final JComboBox cmbPeticion;

    public CmbCategoriaListener(JComboBox cmbSubCategoria) {
        this(cmbSubCategoria, null);
    }
    
    public CmbCategoriaListener(JComboBox cmbSubCategoria, JComboBox cmbPeticion) {
        super();
        this.cmbSubCategoria = cmbSubCategoria;
        this.cmbPeticion = cmbPeticion;
    }    

    @Override
    public void itemStateChanged(ItemEvent e) {
        Categoria categoria = (Categoria) e.getItem();
        List<SubCategoria> subCategorias = AppHelper.getSubCategoriaService().findByCategoria(categoria);
        cmbSubCategoria.setModel(new CmbSubCategoriaModel(subCategorias));
        cmbSubCategoria.setSelectedIndex(subCategorias.size() == 1? 1: -1);
        if (cmbPeticion != null) {
            cmbPeticion.setModel(new CmbPeticionModel(null));
        }
        
    }
}
