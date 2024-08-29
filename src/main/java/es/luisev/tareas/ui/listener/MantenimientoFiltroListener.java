/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.listener;

import es.luisev.tareas.ui.DialogoBase;
import es.luisev.tareas.ui.MantenimientoFiltroDialog;
import es.luisev.tareas.ui.combobox.listener.CmbCategoriaListener;
import es.luisev.tareas.ui.combobox.listener.CmbSubCategoriaListener;
import es.luisev.tareas.ui.combobox.model.CmbCategoriaModel;
import es.luisev.tareas.ui.combobox.model.CmbSubCategoriaModel;
import es.luisev.tareas.ui.combobox.model.CmbUsuarioModel;
import javax.swing.JComboBox;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class MantenimientoFiltroListener extends ListenerBase {

    private final MantenimientoFiltroDialog pantalla;
    
    public MantenimientoFiltroListener(DialogoBase dialogo) {
        super(dialogo);
        this.pantalla = (MantenimientoFiltroDialog) dialogo;
        iniciaDialogo();
    }
    
    @Override
    public void evtLimpiar() {
        pantalla.getCmbCategoria().setSelectedIndex(0);
        pantalla.getCmbSubCategoria().setSelectedIndex(0);
        pantalla.getCmbPeticion().setSelectedIndex(0);
        //Peticion
        pantalla.getTxtAsuntoContiene().setText("");
        pantalla.getTxtFechaInicioProgramadoDesde().setText("");
        pantalla.getTxtFechaInicioProgramadoHasta().setText("");
        pantalla.getTxtFechaInicioRealDesde().setText("");
        pantalla.getTxtFechaInicioRealHasta().setText("");
        pantalla.getTxtHorasRealesDesde().setText("");
        pantalla.getTxtHorasRealesHasta().setText("");
        pantalla.getTxtlPorcentajeRealizacionDesde().setText("");
        pantalla.getTxtlPorcentajeRealizacionHasta().setText("");
        pantalla.getCmbUsuario().setSelectedIndex(0);
        //Horas
        pantalla.getTxtFechaImputacionDesde().setText("");
        pantalla.getTxtFechaImputacionHasta().setText("");
        pantalla.getTxtHorasImputadasDesde().setText("");
        pantalla.getTxtHorasImputadasHasta().setText("");
        pantalla.getCmbTipoHoras().setSelectedIndex(-1);
        //Documento
        pantalla.getCmbTipoDocumento().setSelectedIndex(-1);
        pantalla.getTxtNombreContiene().setText("");
        pantalla.getTxtRuta().setText("");        
    }
    
    @Override
    public boolean evtGuardar() {
        return true;
    }    
    
    private void iniciaDialogo() {
        JComboBox cmbCategoria = pantalla.getCmbCategoria();
        JComboBox cmbSubCategoria = pantalla.getCmbSubCategoria();
        //
        cmbCategoria.setModel(new CmbCategoriaModel());
        cmbSubCategoria.setModel(new CmbSubCategoriaModel());
        // Al cambiar la categoría, se rellenan las subCategorías
        CmbCategoriaListener cmbCategoriaListener = new CmbCategoriaListener(cmbSubCategoria);
        cmbCategoria.addItemListener(cmbCategoriaListener);
        // Al cambiar la SubCategoría, se rellenan las peticiones
        CmbSubCategoriaListener cmbSubCategoriaListener = new CmbSubCategoriaListener(pantalla.getCmbPeticion());
        cmbSubCategoria.addItemListener(cmbSubCategoriaListener);
        //
        pantalla.getCmbUsuario().setModel(new CmbUsuarioModel());
        //
        evtLimpiar();
    }
}
