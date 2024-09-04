/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.listener;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.Categoria;
import es.luisev.tareas.service.CategoriaService;
import es.luisev.tareas.ui.DialogoBase;
import es.luisev.tareas.ui.MantenimientoCategoriaDialog;
import es.luisev.tareas.utils.AppHelper;
import es.luisev.tareas.utils.UIHelper;
/**
 *
 * @author Luis-Enrique.Varona
 */
public final class MantenimientoCategoriaListener extends ListenerBase {

    private final MantenimientoCategoriaDialog pantalla;
    
    public MantenimientoCategoriaListener(DialogoBase dialogo) {
        super(dialogo);
        this.pantalla = (MantenimientoCategoriaDialog) dialogo;
        iniciaDialogo();
    }

    @Override
    public void evtLimpiar() {
        Categoria paramCategoria = (Categoria) pantalla.getParamObject();
        if (paramCategoria == null) {
            paramCategoria = new Categoria();
        }
        pantalla.getTxtCodigo().setText(paramCategoria.getCodigo());
        pantalla.getTxtNombre().setText(paramCategoria.getNombre());
    }

    @Override
    public boolean evtGuardar() {
        Categoria paramCategoria = (Categoria) pantalla.getParamObject();
        if (!UIHelper.confirmAction(pantalla, "confirmacion.guardar")){
           return false;
        } 
        CategoriaService service = AppHelper.getCategoriaService();
        try {
            String codigo = pantalla.getTxtCodigo().getText();
            String nombre = pantalla.getTxtNombre().getText();
            Long fecAlta = paramCategoria == null? null: paramCategoria.getFecAlta();
            Long id = paramCategoria == null? null: paramCategoria.getId();
            
            Categoria p = Categoria.builder().
                    id(id).
                    nombre(nombre).
                    codigo(codigo).
                    fecAlta(fecAlta).
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

    private void iniciaDialogo(){
        evtLimpiar();        
    }
}
