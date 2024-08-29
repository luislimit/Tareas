/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.combobox.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class CmbPeriodoFechaListener implements ActionListener {

    private final JComboBox cmbPeriodo;
    private JTextField txtDesde;
    private JTextField txtHasta;

    public CmbPeriodoFechaListener(JComboBox cmbPeriodo) {
        super();
        this.cmbPeriodo = cmbPeriodo;
    }

    public void setTxtDesde(JTextField txtDesde) {
        this.txtDesde = txtDesde;
    }

    public void setTxtHasta(JTextField txtHasta) {
        this.txtHasta = txtHasta;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int opcion = cmbPeriodo.getSelectedIndex();

        if (opcion <= 0 || txtDesde == null || txtHasta == null){
            return;
        }
        
        LocalDate currentDate = LocalDate.now();
        LocalDate desde = currentDate;
        LocalDate hasta = currentDate;
        switch (opcion){
            case 0 -> {
                return;
            }
            case 1 -> {// Hoy
                break;
            }
            case 2 -> {  //Semana Actual
                desde = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                hasta = desde.plusDays(6);
            }
            case 3 -> {  //Quincena Actual
                desde = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                hasta = desde.plusDays(6);
                desde = desde.minusDays(7);
            }
            case 4 -> {  //Mes Actual
                desde = currentDate.with(TemporalAdjusters.firstDayOfMonth());
                hasta = LocalDate.now();
            }
            case 5 -> {  //Año Actual
                desde = currentDate.with(TemporalAdjusters.firstDayOfYear());
                hasta = LocalDate.now();
            }
            case 6 -> {  //Ayer
                desde = LocalDate.now().minusDays(1);
                hasta = desde;
            }
            case 7 -> {  // Semana pasada
                desde = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                desde = desde.minusDays(7);
                hasta = desde.plusDays(6);
            }
            case 8 -> {  // Mes pasado
                desde = currentDate.with(TemporalAdjusters.firstDayOfMonth());
                hasta = desde.minusDays(1);
                desde = desde.minusMonths(1);
            }
            case 9 -> {  // Año pasado
                desde = currentDate.minusYears(1).with(TemporalAdjusters.firstDayOfYear());
                hasta = currentDate.minusYears(1).with(TemporalAdjusters.lastDayOfYear());
            }
        }
        // Formatear la fecha en formato dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String desdeStr = desde.format(formatter);
        String hastaStr = hasta.format(formatter);
        
        txtDesde.setText(desdeStr);
        txtHasta.setText(hastaStr);  
    }
}
