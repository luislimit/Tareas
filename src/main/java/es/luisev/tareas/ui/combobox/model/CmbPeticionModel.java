/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.combobox.model;

import es.luisev.tareas.model.Peticion;
import es.luisev.tareas.utils.AppHelper;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class CmbPeticionModel extends AbstractListModel<Peticion> implements ComboBoxModel<Peticion> {

    /**
     *
     */
    private static final long serialVersionUID = -3700195174395455435L;

    private final List<Peticion> lista;

    private Peticion selection = null;

    public CmbPeticionModel() {
        super();
        lista = AppHelper.getPeticionService().findAll();
    }

    public CmbPeticionModel(List<Peticion> lista) {
        super();
        this.lista = new ArrayList<>();
        if (lista != null) {
            this.lista.add(null);
            this.lista.addAll(lista);
        }
    }

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Peticion getElementAt(int index) {
        return lista.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (Peticion) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
