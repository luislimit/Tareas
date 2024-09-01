/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.combobox.model;

import es.luisev.tareas.model.Usuario;
import es.luisev.tareas.utils.AppHelper;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class CmbUsuarioModel extends AbstractListModel<Usuario> implements ComboBoxModel<Usuario> {

    /**
     *
     */
    private static final long serialVersionUID = -3700195174395455435L;

    private final List<Usuario> lista;

    private Usuario selection = null;

    public CmbUsuarioModel() {
        super();
        lista = AppHelper.getUsuarioService().findAll();
        lista.add(0, null);
        setDefault();
    }

    public CmbUsuarioModel(List<Usuario> lista) {
        super();
        this.lista = new ArrayList<>();
        if (lista != null) {
            this.lista.add(null);
            this.lista.addAll(lista);
        }
        setDefault();
    }

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Usuario getElementAt(int index) {
        return lista.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (Usuario) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

    private void setDefault() {
        //Debe buscar el usuario por defecto y seleccionarlo
        if (lista.size() > 0) {
            setSelectedItem(this.lista.get(1));
        }
    }
}
