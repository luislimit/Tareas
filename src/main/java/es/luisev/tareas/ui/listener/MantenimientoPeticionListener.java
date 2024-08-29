/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.listener;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.Categoria;
import es.luisev.tareas.model.Estado;
import es.luisev.tareas.model.Peticion;
import es.luisev.tareas.model.SubCategoria;
import es.luisev.tareas.model.Usuario;
import es.luisev.tareas.service.PeticionService;
import es.luisev.tareas.ui.DialogoBase;
import es.luisev.tareas.ui.MantenimientoPeticionDialog;
import es.luisev.tareas.ui.combobox.listener.CmbCategoriaListener;
import es.luisev.tareas.ui.combobox.model.CmbCategoriaModel;
import es.luisev.tareas.ui.combobox.model.CmbEstadoModel;
import es.luisev.tareas.ui.combobox.model.CmbSubCategoriaModel;
import es.luisev.tareas.ui.combobox.model.CmbUsuarioModel;
import es.luisev.tareas.utils.AppHelper;
import es.luisev.tareas.utils.UIHelper;

/**
 *
 * @author Luis-Enrique.Varona
 */
public final class MantenimientoPeticionListener extends ListenerBase {

    private final MantenimientoPeticionDialog pantalla;

    public MantenimientoPeticionListener(DialogoBase dialogo) {
        super(dialogo);
        this.pantalla = (MantenimientoPeticionDialog) dialogo;
        iniciaDialogo();
    }

    @Override
    public void evtLimpiar() {
        Peticion paramPeticion = (Peticion) pantalla.getParamObject();
        if (paramPeticion == null) {
            paramPeticion = new Peticion();
        }
        pantalla.getTxtCodigo().setText(paramPeticion.getCodigo());
        pantalla.getTxtAsunto().setText(paramPeticion.getAsunto());
        if (paramPeticion.getId() != null) {
            pantalla.getCmbUsuario().setSelectedItem(paramPeticion.getUsuario());
        }
        pantalla.getCmbCategoria().setSelectedItem(paramPeticion.getCategoria());
        pantalla.getCmbSubCategoria().setSelectedItem(paramPeticion.getSubCategoria());
        pantalla.getCmbEstado().setSelectedItem(paramPeticion.getEstado());
        pantalla.getTxtDescripcion().setText(paramPeticion.getDescripcion());
        pantalla.getTxtInicioPrevisto().setText(AppHelper.dateBdToString(paramPeticion.getFecPrevistaInicio()));
        pantalla.getTxtFinPrevisto().setText(AppHelper.dateBdToString(paramPeticion.getFecPrevistaFin()));
        pantalla.getTxtInicioReal().setText(AppHelper.dateBdToString(paramPeticion.getFecRealInicio()));
        pantalla.getTxtFinReal().setText(AppHelper.dateBdToString(paramPeticion.getFecRealFin()));
        pantalla.getTxtHorasPrevista().setText(paramPeticion.getHorasPrevista() == null ? "" : paramPeticion.getHorasPrevista().toString());
        pantalla.getTxtHorasReal().setText(paramPeticion.getHorasReal() == null ? "" : paramPeticion.getHorasReal().toString());
        pantalla.getTxtPorcentaje().setText(paramPeticion.getPorcentaje() == null ? "" : paramPeticion.getPorcentaje().toString());
    }

    @Override
    public boolean evtGuardar() {
        Peticion paramPeticion = (Peticion) pantalla.getParamObject();

        if (!UIHelper.confirmAction(pantalla, UIHelper.getLiteral("confirmacion.guardar"))) {
            return false;
        }
        PeticionService peticionService = AppHelper.getPeticionService();
        try {
            String codigo = pantalla.getTxtCodigo().getText();
            String asunto = pantalla.getTxtAsunto().getText();
            Usuario usuario = (Usuario) pantalla.getCmbUsuario().getSelectedItem();
            Categoria categoria = (Categoria) pantalla.getCmbCategoria().getSelectedItem();
            SubCategoria subCategoria = (SubCategoria) pantalla.getCmbSubCategoria().getSelectedItem();
            Estado estado = (Estado) pantalla.getCmbEstado().getSelectedItem();
            String descripcion = pantalla.getTxtDescripcion().getText();
            Long fecPrevistaInicio = AppHelper.getDateBD(pantalla.getTxtInicioPrevisto());
            Long fecPrevistaFin = AppHelper.getDateBD(pantalla.getTxtFinPrevisto());
            Long fecRealInicio = AppHelper.getDateBD(pantalla.getTxtInicioReal());
            Long fecRealFin = AppHelper.getDateBD(pantalla.getTxtFinReal());
            Double horasPrevista = AppHelper.getDouble(pantalla.getTxtHorasPrevista());
            Double horasReal = AppHelper.getDouble(pantalla.getTxtHorasReal());
            Double porcentaje = AppHelper.getDouble(pantalla.getTxtPorcentaje());
            Long fecAlta = paramPeticion.getFecAlta();

            Peticion p = Peticion.builder().
                    id(paramPeticion.getId()).
                    asunto(asunto).
                    codigo(codigo).
                    descripcion(descripcion).
                    fecAlta(fecAlta).
                    fecPrevistaFin(fecPrevistaFin).
                    fecPrevistaInicio(fecPrevistaInicio).
                    fecRealFin(fecRealFin).
                    fecRealInicio(fecRealInicio).
                    horasPrevista(horasPrevista).
                    horasReal(horasReal).
                    porcentaje(porcentaje).
                    categoria(categoria).
                    subCategoria(subCategoria).
                    estado(estado).
                    usuario(usuario).build();

            if (paramPeticion.getId() == null) {
                peticionService.insert(p);
            } else {
                //peticionService.update(paramPeticion.getId(), p);
                peticionService.update(p);
            }
            pantalla.setReturnObject(p);
            return true;
        } catch (TareasApplicationException ex1) {
            ex1.showErrors(dialogo);
            return false;
        }
    }

    private void iniciaDialogo() {
        pantalla.getCmbCategoria().setModel(new CmbCategoriaModel());
        pantalla.getCmbSubCategoria().setModel(new CmbSubCategoriaModel());
        pantalla.getCmbEstado().setModel(new CmbEstadoModel());
        pantalla.getCmbUsuario().setModel(new CmbUsuarioModel());
        CmbCategoriaListener cmbCategoriaListener = new CmbCategoriaListener(pantalla.getCmbSubCategoria());
        pantalla.getCmbCategoria().addItemListener(cmbCategoriaListener);
        evtLimpiar();
    }
}
