/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.table.model;

import es.luisev.tareas.model.Imputacion;
import es.luisev.tareas.ui.table.cabecera.Cabecera;
import es.luisev.tareas.utils.AppHelper;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class ImputacionTableModel extends DefaultTableModel<Imputacion> {

    public ImputacionTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Imputacion row = data.get(rowIndex);

        switch (columnIndex) {
            case 0 -> {
                return row.getPeticion().getCategoria()==null?null:row.getPeticion().getCategoria().getCodigo();
            }
            case 1 -> {
                return row.getPeticion().getSubCategoria()==null?null:row.getPeticion().getSubCategoria().getCodigo();
            }
            case 2 -> {
                return row.getPeticion().getCodigo();
            }
            case 3 -> {
                return AppHelper.dateBdToString(row.getFecha());
            }
            case 4 -> {
                return row.getHorasReal();
            }
            case 5 -> {
                return row.getExtra();
            }
            case 6 -> {
                return row.getDescripcion();
            }            
            case 7 -> {
                return row.getEstado().getNombre();
            }
            default -> {
            }
        }
        return null;
    }
}

