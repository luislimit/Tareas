/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.listener;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.Filtro;
import es.luisev.tareas.model.Imputacion;
import es.luisev.tareas.model.Peticion;
import es.luisev.tareas.service.ExportarService;
import es.luisev.tareas.service.ImportarService;
import es.luisev.tareas.ui.DialogoBase;
import es.luisev.tareas.ui.ExportImportDialog;
import es.luisev.tareas.ui.VisorForm;
import es.luisev.tareas.ui.combobox.listener.CmbTipoExpImpListener;
import es.luisev.tareas.ui.table.model.PeticionTableModel;
import es.luisev.tareas.utils.AppHelper;
import es.luisev.tareas.utils.UIHelper;
import java.util.List;
import javax.swing.JTable;


/**
 *
 * @author Luis-Enrique.Varona
 */
public final class ExportImportListener extends ListenerBase {

    private final ExportImportDialog pantalla;
    VisorForm visorForm;
    Filtro filtro;

    public ExportImportListener(DialogoBase dialogo) {
        super(dialogo);
        this.pantalla = (ExportImportDialog) dialogo;
        this.visorForm = (VisorForm) pantalla.getParent();
        filtro = visorForm.getVisorListener().getFiltro();
        iniciaDialogo();
    }

    @Override
    public void evtLimpiar() {
        pantalla.getCmbTipo().setSelectedIndex(-1);
        pantalla.getTxtPlantilla().setText("");
        pantalla.getTxtFichero().setText("");
    }

    @Override
    public boolean evtGuardar() {
        if (!UIHelper.confirmAction(pantalla, UIHelper.getLiteral("confirmacion.exportar"))) {
            return false;
        }
        try {
            // Lanzar el proceso dependiendo de la opcion seleccionada
            switch (pantalla.getCmbTipo().getSelectedIndex()) {
                case 0 ->
                    exportStandard();
                case 1 ->
                    exportInforme();
                case 2 ->
                    importEstandar();  
            }

        } catch (TareasApplicationException e) {
            UIHelper.showErrors(dialogo, e);
        }
        //No cerrar la pantalla aunque todo sea correcto
        return false;
    }

    private void exportStandard() throws TareasApplicationException {
        String plantillaName = pantalla.getTxtPlantilla().getText();
        String fileName = pantalla.getTxtFichero().getText();
        //
        JTable tblPeticion = ((VisorForm) pantalla.getParent()).getTblPeticion();
        List<Peticion> listaPeticion = ((PeticionTableModel) tblPeticion.getModel()).getData();
        List<Imputacion> listaImputacion = AppHelper.getImputacionService().findByCriteria(listaPeticion, filtro);

        ExportarService.exportPeticiones(plantillaName, fileName, listaPeticion, listaImputacion);

        UIHelper.showMessage(pantalla, "exportar.ok", fileName);
    }

    private void exportInforme() throws TareasApplicationException {
        String plantillaName = pantalla.getTxtPlantilla().getText();
        String fileName = pantalla.getTxtFichero().getText();

        JTable tblPeticion = ((VisorForm) pantalla.getParent()).getTblPeticion();
        List<Peticion> listaPeticion = ((PeticionTableModel) tblPeticion.getModel()).getData();
        List<Imputacion> listaImputacion = AppHelper.getImputacionService().findByCriteria(listaPeticion, filtro);
        
        ExportarService.expInformeSeguimiento(plantillaName, fileName, listaPeticion, listaImputacion);

        UIHelper.showMessage(pantalla, "exportar.ok", fileName);
    }

    private void importEstandar() {
        String fileName = pantalla.getTxtFichero().getText();
        if (UIHelper.confirmAction(pantalla, "confirmacion.importar")) {
            try {
                ImportarService.importFromExcel(fileName);
                UIHelper.showMessage(pantalla, "importacion.finalizada.ok");
            } catch (TareasApplicationException e) {
                UIHelper.showErrors(pantalla, e);
            }
        }
    }

    private void iniciaDialogo() {
        evtLimpiar();
        pantalla.getCmbTipo().addItemListener(new CmbTipoExpImpListener(pantalla));
    }

}
