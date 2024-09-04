/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.listener;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.Categoria;
import es.luisev.tareas.model.SubCategoria;
import es.luisev.tareas.service.SubCategoriaService;
import es.luisev.tareas.ui.DialogoBase;
import es.luisev.tareas.ui.MantenimientoSubCategoriaDialog;
import es.luisev.tareas.ui.combobox.model.CmbCategoriaModel;
import es.luisev.tareas.utils.AppHelper;
import es.luisev.tareas.utils.UIHelper;
/**
 *
 * @author Luis-Enrique.Varona
 */
public final class MantenimientoSubCategoriaListener extends ListenerBase {

    private final MantenimientoSubCategoriaDialog pantalla;
    
    public MantenimientoSubCategoriaListener(DialogoBase dialogo) {
        super(dialogo);
        this.pantalla = (MantenimientoSubCategoriaDialog) dialogo;
        iniciaDialogo();
    }

    @Override
    public void evtLimpiar() {
        SubCategoria paramSubCategoria = (SubCategoria) pantalla.getParamObject();
        if (paramSubCategoria == null) {
            paramSubCategoria = new SubCategoria();
        }
        pantalla.getCmbCategoria().setSelectedItem(paramSubCategoria.getCategoria());
        pantalla.getTxtCodigo().setText(paramSubCategoria.getCodigo());
        pantalla.getTxtNombre().setText(paramSubCategoria.getNombre());
    }

    @Override
    public boolean evtGuardar() {
         SubCategoria paramSubCategoria = (SubCategoria) pantalla.getParamObject();

        if (!UIHelper.confirmAction(pantalla, UIHelper.getLiteral("confirmacion.guardar") )){
           return false;
        } 
        SubCategoriaService service = AppHelper.getSubCategoriaService();
        try {
            String codigo = pantalla.getTxtCodigo().getText();
            String nombre = pantalla.getTxtNombre().getText();
            Categoria categoria = (Categoria)pantalla.getCmbCategoria().getSelectedItem();
            
            SubCategoria p = SubCategoria.builder().
                    id(paramSubCategoria.getId()).
                    categoria(categoria).
                    fecAlta(paramSubCategoria.getFecAlta()).
                    nombre(nombre).
                    codigo(codigo).
                    build();

            if (paramSubCategoria.getId() == null) {
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
        pantalla.getCmbCategoria().setModel(new CmbCategoriaModel());
        evtLimpiar();        
    }
}
