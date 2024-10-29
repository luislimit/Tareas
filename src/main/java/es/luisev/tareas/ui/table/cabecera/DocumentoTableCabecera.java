/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.table.cabecera;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class DocumentoTableCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("lblNro");
        addColumn("lblCategoria");
        addColumn("lblSubCategoria");
        addColumn("lblPeticion");
        addColumn("lblTipo");
        addColumn("lblFichero");
        addColumn("lblDescripcion");

        columnClasses.add(Integer.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);

        columnSizes.add(20);
        columnSizes.add(30);
        columnSizes.add(30);
        columnSizes.add(50);
        columnSizes.add(100);
        columnSizes.add(400);
        columnSizes.add(200);
    }
}
