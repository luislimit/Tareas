/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.table.model;

import es.luisev.tareas.model.Documento;
import es.luisev.tareas.ui.table.cabecera.Cabecera;
import es.luisev.tareas.utils.AppHelper;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class DocumentoTableModel extends DefaultTableModel<Documento> {

    public DocumentoTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Documento row = data.get(rowIndex);

        switch (columnIndex) {
            case 0 -> {
                return rowIndex+1;
            }
            case 1 -> {
                return row.getPeticion().getCategoria()==null?null:row.getPeticion().getCategoria().getCodigo();
            }
            case 2 -> {
                return row.getPeticion().getSubCategoria()==null?null:row.getPeticion().getSubCategoria().getCodigo();
            }
            case 3 -> {
                return row.getPeticion().getCodigo();
            }
            case 4 -> {
                return row.getTipoDocumento();
            }
            case 5 -> {
                return row.getFichero();
            }            
            case 6 -> {
                return row.getDescripcion();
            }            
            default -> {
            }
        }
        return null;
    }
}

