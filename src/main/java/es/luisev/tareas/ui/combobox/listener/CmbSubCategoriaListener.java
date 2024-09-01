/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.combobox.listener;

import es.luisev.tareas.model.Peticion;
import es.luisev.tareas.model.SubCategoria;
import es.luisev.tareas.ui.combobox.model.CmbPeticionModel;
import es.luisev.tareas.utils.AppHelper;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class CmbSubCategoriaListener implements ItemListener {

    private final JComboBox cmbPeticion;

    public CmbSubCategoriaListener(JComboBox cmbPeticion) {
        super();
        this.cmbPeticion = cmbPeticion;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        List<Peticion> peticiones = null;
        SubCategoria subCategoria = (SubCategoria) e.getItem();
        if (subCategoria != null && subCategoria.getId() != null) {
            Long idCategoria = subCategoria.getCategoria().getId();
            Long idSubCategoria = subCategoria.getId();
            peticiones = AppHelper.getPeticionService().findByCategoriaSubCategoria(idCategoria, idSubCategoria);
        }
        cmbPeticion.setModel(new CmbPeticionModel(peticiones));
        if (peticiones != null && peticiones.size() == 1) {
            cmbPeticion.setSelectedIndex(1);
        } else{
            cmbPeticion.setSelectedIndex(-1);
        }
    }
}
