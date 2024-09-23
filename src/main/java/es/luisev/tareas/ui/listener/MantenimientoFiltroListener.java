/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.listener;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.*;
import es.luisev.tareas.ui.combobox.model.*;
import es.luisev.tareas.ui.DialogoBase;
import es.luisev.tareas.ui.MantenimientoFiltroDialog;
import es.luisev.tareas.ui.combobox.listener.CmbCategoriaListener;
import es.luisev.tareas.utils.UIHelper;
import javax.swing.JComboBox;
import javax.swing.JTextField;

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
        pantalla.getCmbCategoria().setSelectedIndex(-1);
        pantalla.getCmbSubCategoria().setSelectedIndex(-1);
        pantalla.getCmbCriteriosPeticion().setSelectedIndex(-1);
        //Peticion
        pantalla.getTxtAsuntoContiene().setText("");

        pantalla.getDchInicioPrevistoDesde().setDate(null);
        pantalla.getDchInicioPrevistoHasta().setDate(null);
        pantalla.getDchFinPrevistoDesde().setDate(null);
        pantalla.getDchFinPrevistoHasta().setDate(null);
        pantalla.getDchInicioRealDesde().setDate(null);
        pantalla.getDchInicioRealHasta().setDate(null);
        pantalla.getDchFinRealDesde().setDate(null);
        pantalla.getDchFinRealHasta().setDate(null);

        pantalla.getTxtHorasPrevistaDesde().setText("");
        pantalla.getTxtHorasPrevistaHasta().setText("");        
        pantalla.getTxtHorasRealDesde().setText("");
        pantalla.getTxtHorasRealHasta().setText("");
        pantalla.getTxtPorcentajeDesde().setText("");
        pantalla.getTxtPorcentajeHasta().setText("");

        pantalla.getCmbUsuario().setSelectedIndex(-1);
        //Horas
        pantalla.getDchImputacionDesde().setDate(null);
        pantalla.getDchImputacionHasta().setDate(null);
        pantalla.getTxtHorasImputadasDesde().setText("");
        pantalla.getTxtHorasImputadasHasta().setText("");
        pantalla.getTxtImputaContiene().setText("");
        pantalla.getCmbTipoHoras().setSelectedIndex(0);
        //Documento
        pantalla.getCmbTipoDocumento().setSelectedIndex(0);
        pantalla.getTxtNombreContiene().setText("");
        pantalla.getTxtRuta().setText("");
        pantalla.repaint();
    }

    @Override
    public boolean evtGuardar() {
        Filtro filtro = new Filtro();
        filtro.setCategoria((Categoria) pantalla.getCmbCategoria().getSelectedItem());
        filtro.setSubCategoria((SubCategoria) pantalla.getCmbSubCategoria().getSelectedItem());
        filtro.setTipoListado(pantalla.getCmbCriteriosPeticion().getSelectedIndex());
        filtro.setEstado((Estado) pantalla.getCmbEstado().getSelectedItem());
        //
        //
        filtro.setAsuntoContiene(pantalla.getTxtAsuntoContiene().getText());
        filtro.setInicioPrevistoDesde(UIHelper.getDateDB(pantalla.getDchInicioPrevistoDesde()));
        filtro.setInicioPrevistoHasta(UIHelper.getDateDB(pantalla.getDchInicioPrevistoHasta()));
        filtro.setFinPrevistoDesde(UIHelper.getDateDB(pantalla.getDchFinPrevistoDesde()));
        filtro.setFinPrevistoHasta(UIHelper.getDateDB(pantalla.getDchFinPrevistoHasta()));

        filtro.setInicioRealDesde(UIHelper.getDateDB(pantalla.getDchInicioRealDesde()));
        filtro.setInicioRealHasta(UIHelper.getDateDB(pantalla.getDchInicioRealHasta()));
        filtro.setFinRealDesde(UIHelper.getDateDB(pantalla.getDchFinRealDesde()));
        filtro.setFinRealHasta(UIHelper.getDateDB(pantalla.getDchFinRealHasta()));
        
        filtro.setHorasPrevistaDesde(UIHelper.getDouble(pantalla.getTxtHorasPrevistaDesde()));
        filtro.setHorasPrevistaHasta(UIHelper.getDouble(pantalla.getTxtHorasPrevistaHasta()));
        filtro.setHorasRealesDesde(UIHelper.getDouble(pantalla.getTxtHorasRealDesde()));
        filtro.setHorasRealesHasta(UIHelper.getDouble(pantalla.getTxtHorasRealHasta()));

        filtro.setPorcentajeDesde(UIHelper.getDouble(pantalla.getTxtPorcentajeDesde()));
        filtro.setPorcentajeHasta(UIHelper.getDouble(pantalla.getTxtPorcentajeHasta()));

        setComboBoxItem(pantalla.getCmbUsuario(), filtro.getUsuario());
        //
        filtro.setImputacionDesde(UIHelper.getDateDB(pantalla.getDchImputacionDesde()));
        filtro.setImputacionHasta(UIHelper.getDateDB(pantalla.getDchImputacionHasta()));
        filtro.setImputacionContiene(pantalla.getTxtImputaContiene().getText());
        filtro.setHorasImputadasDesde(UIHelper.getDouble(pantalla.getTxtHorasImputadasDesde()));
        filtro.setHorasImputadasHasta(UIHelper.getDouble(pantalla.getTxtHorasImputadasHasta()));
        filtro.setTipoHoras(pantalla.getCmbTipoHoras().getSelectedIndex());
        //
        filtro.setTipoDocumento((TipoDocumento) pantalla.getCmbTipoDocumento().getSelectedItem());
        filtro.setNombreContiene(pantalla.getTxtNombreContiene().getText());
        filtro.setRuta(pantalla.getTxtRuta().getText());

        pantalla.setReturnObject(filtro);
        return true;
    }

    private void iniciaDialogo() {
        JComboBox cmbCategoria = pantalla.getCmbCategoria();
        JComboBox cmbSubCategoria = pantalla.getCmbSubCategoria();
        JComboBox cmbCriterio = pantalla.getCmbCriteriosPeticion();
        //
        cmbCriterio.setModel(new CmbCriteriosPeticionModel());
        //
        cmbCategoria.setModel(new CmbCategoriaModel());
        cmbSubCategoria.setModel(new CmbSubCategoriaModel());
        // Al cambiar la categoría, se rellenan las subCategorías
        CmbCategoriaListener cmbCategoriaListener = new CmbCategoriaListener(cmbSubCategoria);
        cmbCategoria.addItemListener(cmbCategoriaListener);
        //
        pantalla.getCmbUsuario().setModel(new CmbUsuarioModel());
        pantalla.getCmbEstado().setModel(new CmbEstadoModel());
        //pantalla.getCmbTipoDocumento().setModel(new CmbTipoDocumento());
        //
        Filtro filtro = (Filtro) pantalla.getParamObject();
        if (filtro == null) {
            evtLimpiar();
        } else {
            try {
                setComboBoxItem(cmbCategoria, filtro.getCategoria());
                setComboBoxItem(cmbSubCategoria, filtro.getSubCategoria());
                cmbCriterio.setSelectedIndex(filtro.getTipoListado());
                pantalla.getCmbEstado().setSelectedItem(filtro.getEstado());
                //
                pantalla.getTxtAsuntoContiene().setText(filtro.getAsuntoContiene());
                UIHelper.setDateChooserValue(pantalla.getDchInicioPrevistoDesde(), filtro.getInicioPrevistoDesde());
                UIHelper.setDateChooserValue(pantalla.getDchInicioPrevistoHasta(), filtro.getInicioPrevistoHasta());
                UIHelper.setDateChooserValue(pantalla.getDchFinPrevistoDesde(), filtro.getFinPrevistoDesde());
                UIHelper.setDateChooserValue(pantalla.getDchFinPrevistoHasta(), filtro.getFinPrevistoHasta());
                
                UIHelper.setDateChooserValue(pantalla.getDchInicioRealDesde(), filtro.getInicioRealDesde());
                UIHelper.setDateChooserValue(pantalla.getDchInicioRealHasta(), filtro.getInicioRealHasta());
                UIHelper.setDateChooserValue(pantalla.getDchFinRealDesde(), filtro.getFinRealDesde());
                UIHelper.setDateChooserValue(pantalla.getDchFinRealHasta(), filtro.getFinRealHasta());
                
                setTextDouble(pantalla.getTxtHorasPrevistaDesde(), filtro.getHorasPrevistaDesde());
                setTextDouble(pantalla.getTxtHorasPrevistaHasta(), filtro.getHorasPrevistaHasta());
                setTextDouble(pantalla.getTxtHorasRealDesde(), filtro.getHorasRealesDesde());
                setTextDouble(pantalla.getTxtHorasRealHasta(), filtro.getHorasRealesHasta());
                
                setTextDouble(pantalla.getTxtPorcentajeDesde(), filtro.getPorcentajeDesde());
                setTextDouble(pantalla.getTxtPorcentajeHasta(), filtro.getPorcentajeHasta());
                setComboBoxItem(pantalla.getCmbUsuario(), filtro.getUsuario());
                //
                UIHelper.setDateChooserValue(pantalla.getDchImputacionDesde(), filtro.getImputacionDesde());
                UIHelper.setDateChooserValue(pantalla.getDchImputacionHasta(), filtro.getImputacionHasta());
                pantalla.getTxtImputaContiene().setText(filtro.getImputacionContiene());
                setTextDouble(pantalla.getTxtHorasImputadasDesde(), filtro.getHorasImputadasDesde());
                setTextDouble(pantalla.getTxtHorasImputadasHasta(), filtro.getHorasImputadasHasta());
                pantalla.getCmbTipoHoras().setSelectedIndex(filtro.getTipoHoras());
                //
                setComboBoxItem(pantalla.getCmbTipoDocumento(), filtro.getTipoDocumento());
                pantalla.getTxtNombreContiene().setText(filtro.getNombreContiene());
                pantalla.getTxtRuta().setText(filtro.getRuta());
            } catch (TareasApplicationException ex) {
                UIHelper.showErrors(dialogo, ex);
            }
        }
    }

    private void setTextDouble(JTextField txtField, Double valor) {
        txtField.setText(valor == null ? null : valor.toString());
    }

    private void setComboBoxItem(JComboBox comboBox, Object valor) {
        if (valor == null) {
            comboBox.setSelectedIndex(-1);
        } else {
            comboBox.setSelectedItem(valor);
        }
    }
}
