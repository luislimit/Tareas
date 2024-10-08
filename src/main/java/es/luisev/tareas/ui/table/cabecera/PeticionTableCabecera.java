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
        addColumn("lblNro");
        addColumn("lblCategoria");
        addColumn("lblSubCategoria");
        
        addColumn("lblPeticion");
        addColumn("lblAsunto");
        addColumn("lblInicioPrevisto");
        
        addColumn("lblFinPrevisto");
        addColumn("lblInicioReal");
        addColumn("lblFinReal");
        
        addColumn("lblHorasPrevista");
        addColumn("lblHorasReal");
        addColumn("lblPorcentaje");
        addColumn("lblEstado");

        columnClasses.add(Integer.class);
        columnClasses.add(String.class);
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

        columnSizes.add(50);
        columnSizes.add(80);
        columnSizes.add(80);
        
        columnSizes.add(100);
        columnSizes.add(600);
        columnSizes.add(80);
        
        columnSizes.add(100);
        columnSizes.add(100);
        columnSizes.add(80);
        
        columnSizes.add(100);
        columnSizes.add(100);
        columnSizes.add(80);
        columnSizes.add(80);
    }
}
