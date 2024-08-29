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
public class PeticionTableCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("lblCategoria");
        addColumn("lblSubCategoria");
        addColumn("lblPeticion");
        addColumn("lblAsunto");
        addColumn("lblInicioProgramado");
        addColumn("lblFinProgramado");
        addColumn("lblInicioReal");
        addColumn("lblFinReal");
        addColumn("lblHorasProgramada");
        addColumn("lblHorasReal");
        addColumn("lblEstado");

        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(Double.class);
        columnClasses.add(Double.class);
        columnClasses.add(String.class);

        columnSizes.add(80);
        columnSizes.add(80);
        columnSizes.add(100);
        columnSizes.add(600);
        columnSizes.add(80);
        columnSizes.add(80);
        columnSizes.add(80);
        columnSizes.add(80);
        columnSizes.add(80);
        columnSizes.add(80);
        columnSizes.add(80);
    }
}
