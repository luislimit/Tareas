/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.table.model;

import es.luisev.tareas.model.Peticion;
import es.luisev.tareas.ui.table.cabecera.Cabecera;
import es.luisev.tareas.utils.AppHelper;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class PeticionTableModel extends DefaultTableModel<Peticion> {

    public PeticionTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Peticion row = data.get(rowIndex);

        switch (columnIndex) {
            case 0 -> {
                return rowIndex+1;
            }
            case 1 -> {
                return row.getCategoria()==null?null:row.getCategoria().getCodigo();
            }
            case 2 -> {
                return row.getSubCategoria()==null?null:row.getSubCategoria().getCodigo();
            }
            case 3 -> {
                return row.getCodigo();
            }
            case 4 -> {
                return row.getAsunto();
            }
            case 5 -> {
                return AppHelper.dateBdToString(row.getFecPrevistaInicio());
            }
            case 6 -> {
                return AppHelper.dateBdToString(row.getFecPrevistaFin());
            }
            case 7 -> {
                return AppHelper.dateBdToString(row.getFecRealInicio());
            }
            case 8 -> {
                return AppHelper.dateBdToString(row.getFecRealFin());
            }
            case 9 -> {
                return row.getHorasPrevista();
            }
            case 10 -> {
                return row.getHorasReal();
            }
            case 11 -> {
                return row.getPorcentaje();
            }
            case 12 -> {
                return row.getEstado()==null?null:row.getEstado().getNombre();
            }
            default -> {
            }
        }
        return null;
    }
}