/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.listener;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.Categoria;
import es.luisev.tareas.model.Estado;
import es.luisev.tareas.model.Imputacion;
import es.luisev.tareas.model.Peticion;
import es.luisev.tareas.model.SubCategoria;
import es.luisev.tareas.model.Usuario;
import es.luisev.tareas.service.ImputacionService;
import es.luisev.tareas.ui.DialogoBase;
import es.luisev.tareas.ui.MantenimientoImputacionDialog;
import es.luisev.tareas.ui.combobox.listener.CmbCategoriaListener;
import es.luisev.tareas.ui.combobox.listener.CmbSubCategoriaListener;
import es.luisev.tareas.ui.combobox.model.CmbCategoriaModel;
import es.luisev.tareas.ui.combobox.model.CmbSubCategoriaModel;
import es.luisev.tareas.ui.combobox.model.CmbUsuarioModel;
import es.luisev.tareas.utils.AppHelper;
import es.luisev.tareas.utils.UIHelper;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author Luis-Enrique.Varona
 */
public final class MantenimientoImputacionListener extends ListenerBase {

    private final MantenimientoImputacionDialog pantalla;

    public MantenimientoImputacionListener(DialogoBase dialogo) {
        super(dialogo);
        this.pantalla = (MantenimientoImputacionDialog) dialogo;
        iniciaDialogo();
    }

    @Override
    public void evtLimpiar() {
        try {
            Imputacion paramImputacion = (Imputacion) pantalla.getParamObject();
            Categoria categoria = null;
            SubCategoria subCategoria = null;
            Peticion peticion = null;
            String descripcion = null;
            String fecha = AppHelper.getFechaAltaBd(); //Fecha actual por defecto
            String horas = null;
            String extra = "N";
            
            if (paramImputacion != null) {
                if (paramImputacion.getPeticion() != null) {
                    categoria = paramImputacion.getPeticion().getCategoria();
                    subCategoria = paramImputacion.getPeticion().getSubCategoria();
                    peticion = paramImputacion.getPeticion();
                }
                descripcion= paramImputacion.getDescripcion();
                fecha = paramImputacion.getFecha()!=null?paramImputacion.getFecha():fecha;
                horas = paramImputacion.getHorasReal()!=null?paramImputacion.getHorasReal().toString():null;
                extra = paramImputacion.getExtra()!=null?paramImputacion.getExtra():extra;
            }
            pantalla.getCmbCategoria().setSelectedItem(categoria);
            pantalla.getCmbSubCategoria().setSelectedItem(subCategoria);
            pantalla.getCmbPeticion().setSelectedItem(peticion);
            pantalla.getTxtDescripcion().setText(descripcion);
            pantalla.getDchFecha().setDate(AppHelper.fromFechaDbToDate(fecha));
            pantalla.getTxtHoras().setText(horas);
            pantalla.getTxtHoras().requestFocus();
            pantalla.getChkExtra().setSelected(extra.equals("S"));
        } catch (TareasApplicationException ex) {
            Logger.getLogger(MantenimientoImputacionListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean evtGuardar() {
        Imputacion paramImputacion = (Imputacion) pantalla.getParamObject();

        if (!UIHelper.confirmAction(pantalla, UIHelper.getLiteral("confirmacion.guardar"))) {
            return false;
        }
        ImputacionService service = AppHelper.getImputacionService();
        try {
            String descripcion = pantalla.getTxtDescripcion().getText();
            String fecha = UIHelper.getDateDB(pantalla.getDchFecha());
            Double horas = UIHelper.getDouble(pantalla.getTxtHoras());
            Long id = paramImputacion == null ? null : paramImputacion.getId();
            Peticion peticion = (Peticion) pantalla.getCmbPeticion().getSelectedItem();
            Usuario usuario = (Usuario) pantalla.getCmbUsuario().getSelectedItem();
            Estado estado = (peticion == null) ? null : peticion.getEstado();
            String fecAlta = paramImputacion == null ? null : paramImputacion.getFecAlta();
            String extra = pantalla.getChkExtra().isSelected()?"S":"N";

            Imputacion p = Imputacion.builder().
                    id(id).
                    peticion(peticion).
                    usuario(usuario).
                    estado(estado).
                    estadoPrevio(estado).
                    fecAlta(fecAlta).
                    fecha(fecha).
                    horasReal(horas).
                    descripcion(descripcion).
                    extra(extra).
                    build();

            if (id == null) {
                service.insert(p);
            } else {
                service.update(p);
            }
            pantalla.setReturnObject(p);
            return true;
        } catch (TareasApplicationException e) {
            UIHelper.showErrors(dialogo, e);
            return false;
        }
    }

    private void iniciaDialogo() {
        JComboBox cmbCategoria = pantalla.getCmbCategoria();
        JComboBox cmbSubCategoria = pantalla.getCmbSubCategoria();
        JComboBox cmbPeticion = pantalla.getCmbPeticion();
        //
        cmbCategoria.setModel(new CmbCategoriaModel());
        cmbSubCategoria.setModel(new CmbSubCategoriaModel());
        // Al cambiar la categoría, se rellenan las subCategorías
        CmbCategoriaListener cmbCategoriaListener = new CmbCategoriaListener(cmbSubCategoria, cmbPeticion);
        cmbCategoria.addItemListener(cmbCategoriaListener);
        // Al cambiar la SubCategoría, se rellenan las peticiones
        CmbSubCategoriaListener cmbSubCategoriaListener = new CmbSubCategoriaListener(cmbPeticion);
        cmbSubCategoria.addItemListener(cmbSubCategoriaListener);
        //
        pantalla.getCmbUsuario().setModel(new CmbUsuarioModel());
        //
        evtLimpiar();
    }
}
