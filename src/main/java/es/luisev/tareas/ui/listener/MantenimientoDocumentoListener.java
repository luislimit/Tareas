/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.listener;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.Categoria;
import es.luisev.tareas.model.Documento;
import es.luisev.tareas.model.Peticion;
import es.luisev.tareas.model.SubCategoria;
import es.luisev.tareas.model.TipoDocumento;
import es.luisev.tareas.model.Usuario;
import es.luisev.tareas.service.DocumentoService;
import es.luisev.tareas.ui.DialogoBase;
import es.luisev.tareas.ui.MantenimientoDocumentoDialog;
import es.luisev.tareas.ui.combobox.listener.CmbCategoriaListener;
import es.luisev.tareas.ui.combobox.listener.CmbSubCategoriaListener;
import es.luisev.tareas.ui.combobox.model.CmbCategoriaModel;
import es.luisev.tareas.ui.combobox.model.CmbSubCategoriaModel;
import es.luisev.tareas.ui.combobox.model.CmbTipoDocumentoModel;
import es.luisev.tareas.ui.combobox.model.CmbUsuarioModel;
import es.luisev.tareas.utils.AppHelper;
import es.luisev.tareas.utils.UIHelper;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis-Enrique.Varona
 */
public final class MantenimientoDocumentoListener extends ListenerBase {

    private final MantenimientoDocumentoDialog pantalla;

    public MantenimientoDocumentoListener(DialogoBase dialogo) {
        super(dialogo);
        this.pantalla = (MantenimientoDocumentoDialog) dialogo;
        iniciaDialogo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnBuscarFichero())) {
            evtBtnBuscarFichero();
        } else if (obj.equals(pantalla.getBtnVerFichero())) {
            evtBtnVerFichero();
        } else {
            super.actionPerformed(e);
        }
    }

    private void evtBtnBuscarFichero() {
        String rutaDefecto = pantalla.getTxtFichero().getText();
        try {
            File file = UIHelper.showDialogNewFile(pantalla, rutaDefecto);
            if (file != null) {
                pantalla.getTxtFichero().setText(file.getAbsolutePath());
            }
        } catch (IOException ex) {
            Logger.getLogger(ExportImportListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void evtBtnVerFichero() {
        try {
            String archivo = pantalla.getTxtFichero().getText();
            if (archivo == null){
                throw new IOException(UIHelper.getLiteral("error.documento.fichero.vacio"));
            }
            File file = new File(archivo);
            if (!file.exists()){
                throw new IOException(UIHelper.getLiteral("No existe el fichero :1", archivo));
            }
            // Abrir el archivo usando la aplicación predeterminada del sistema
            Desktop.getDesktop().open(new File(archivo));
        } catch (IOException ex) {
            UIHelper.showErrors(dialogo, ex);
        }
    }

    @Override
    public void evtLimpiar() {
        Documento paramDocumento = (Documento) pantalla.getParamObject();
        Categoria categoria = null;
        SubCategoria subCategoria = null;
        Peticion peticion = null;
        String descripcion = null;
        String fichero = null;
        TipoDocumento tipoDocumento = null;

        if (paramDocumento != null) {
            if (paramDocumento.getPeticion() != null) {
                categoria = paramDocumento.getPeticion().getCategoria();
                subCategoria = paramDocumento.getPeticion().getSubCategoria();
                peticion = paramDocumento.getPeticion();
            }
            descripcion = paramDocumento.getDescripcion();
            fichero = paramDocumento.getFichero() != null ? paramDocumento.getFichero() : fichero;
            tipoDocumento = paramDocumento.getTipoDocumento();
        }
        pantalla.getCmbCategoria().setSelectedItem(categoria);
        pantalla.getCmbSubCategoria().setSelectedItem(subCategoria);
        pantalla.getCmbPeticion().setSelectedItem(peticion);

        pantalla.getCmbTipoDocumento().setSelectedItem(tipoDocumento);
        pantalla.getTxtFichero().setText(fichero);

        pantalla.getTxtDescripcion().setText(descripcion);
    }

    @Override
    public boolean evtGuardar() {
        Documento paramDocumento = (Documento) pantalla.getParamObject();

        if (!UIHelper.confirmAction(pantalla, UIHelper.getLiteral("confirmacion.guardar"))) {
            return false;
        }

        try {
            DocumentoService documentoService = AppHelper.getDocumentoService();
            //
            String descripcion = pantalla.getTxtDescripcion().getText();
            Long id = paramDocumento == null ? null : paramDocumento.getId();
            Peticion peticion = (Peticion) pantalla.getCmbPeticion().getSelectedItem();
            Usuario usuario = (Usuario) pantalla.getCmbUsuario().getSelectedItem();
            String fecAlta = paramDocumento == null ? null : paramDocumento.getFecAlta();
            TipoDocumento tipoDocumento = (TipoDocumento) pantalla.getCmbTipoDocumento().getSelectedItem();
            String fichero = pantalla.getTxtFichero().getText();

            Documento p = Documento.builder().
                    id(id).
                    peticion(peticion).
                    usuario(usuario).
                    tipoDocumento(tipoDocumento).
                    fichero(fichero).
                    fecAlta(fecAlta).
                    descripcion(descripcion).
                    build();

            if (id == null) {
                documentoService.insert(p);
            } else {
                documentoService.update(p);
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
        pantalla.getCmbTipoDocumento().setModel(new CmbTipoDocumentoModel());
        //
        evtLimpiar();
    }
}
