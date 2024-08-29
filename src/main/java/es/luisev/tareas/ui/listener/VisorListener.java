/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.listener;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.Categoria;
import es.luisev.tareas.model.Filtro;
import es.luisev.tareas.model.DataBaseEntity;
import es.luisev.tareas.model.Imputacion;
import es.luisev.tareas.model.Peticion;
import es.luisev.tareas.model.SubCategoria;
import es.luisev.tareas.service.ImportarService;
import es.luisev.tareas.ui.table.model.PeticionTableModel;
import es.luisev.tareas.ui.MantenimientoCategoriaDialog;
import es.luisev.tareas.ui.MantenimientoImputacionDialog;
import es.luisev.tareas.ui.MantenimientoPeticionDialog;
import es.luisev.tareas.ui.MantenimientoSubCategoriaDialog;
import es.luisev.tareas.ui.VisorForm;
import es.luisev.tareas.ui.table.model.ImputacionTableModel;
import es.luisev.tareas.utils.AppHelper;
import es.luisev.tareas.utils.LiteralesSingleton;
import es.luisev.tareas.utils.UIHelper;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class VisorListener implements ActionListener, TreeSelectionListener, ChangeListener, ListSelectionListener {

    private final VisorForm pantalla;
    private DefaultMutableTreeNode root;

    private static final int PNL_PETICION = 0;
    private static final int PNL_IMPUTACION = 1;
    private static final int PNL_DOCUMENTO = 2;

    private static final Long VACIO = -1L;

    private List<Peticion> peticiones;

    private Filtro filtro;

    public VisorListener(VisorForm visorForm) {
        this.pantalla = visorForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(pantalla.getBtnBorrar())) {
            evtBtnBorrar();
        } else if (obj.equals(pantalla.getBtnEditar())) {
            evtBtnEditar();
        } else if (obj == pantalla.getBtnConfiguracion()) {

        } else if (obj.equals(pantalla.getBtnCrear())) {
            evtBtnCrear();
        } else if (obj == pantalla.getBtnCrearImputacion()) {
            evtBtnCrearImputacion();
        } else if (obj.equals(pantalla.getBtnExportar())) {
            evtBtnExportar();
        } else if (obj.equals(pantalla.getBtnImportar())) {
            evtBtnImportar();
        } else if (obj == pantalla.getBtnFiltrar()) {
            evtBtnFiltrar();
        }
    }

    private Peticion getPeticionNode() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) pantalla.getArbol().getLastSelectedPathComponent();
        if (selectedNode == null) {
            selectedNode = root;
        }
        Object object = selectedNode.getUserObject();
        Peticion peticion = null;

        if (object instanceof Categoria) {
            Categoria categoria = (Categoria) object;
            peticion = Peticion.builder().
                    categoria(categoria).
                    build();
        } else if (object instanceof SubCategoria) {
            SubCategoria subCategoria = (SubCategoria) object;
            peticion = Peticion.builder().
                    categoria(subCategoria.getCategoria()).
                    subCategoria(subCategoria).
                    build();
        } else if (object instanceof Peticion) {
            peticion = (Peticion) object;
        }
        return peticion;
    }

    private void selectNode(DataBaseEntity object) {
        rellenaArbol();
        selectNodeById(pantalla.getArbol(), object);
    }

    private void evtBtnCrear() {
        Peticion peticion = getPeticionNode();
        Object result;

        if (peticion == null) {
            result = UIHelper.showDialog(new MantenimientoCategoriaDialog(pantalla, null));
        } else if (peticion.getSubCategoria() == null) {
            SubCategoria subCategoria = SubCategoria.builder().categoria(peticion.getCategoria()).build();
            result = UIHelper.showDialog(new MantenimientoSubCategoriaDialog(pantalla, subCategoria));
        } else {
            peticion = Peticion.builder().categoria(peticion.getCategoria()).subCategoria(peticion.getSubCategoria()).build();
            result = UIHelper.showDialog(new MantenimientoPeticionDialog(pantalla, peticion));
        }
        //Actualizamos el arbol
        if (result != null) {
            selectNode((DataBaseEntity) result);
        }
    }

    private void evtBtnCrearImputacion() {
        Component focusedComponent = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        Peticion peticion = null;
        Imputacion imputacion;
        if (focusedComponent == pantalla.getArbol()) {
            peticion = getPeticionNode();
        } else if (focusedComponent == pantalla.getTblPeticion()) {
            peticion = getTblPeticionSelection();
        } else if (focusedComponent == pantalla.getTblImputacion()) {
            imputacion = getTblImputacionSelection();
            if (imputacion != null) {
                peticion = imputacion.getPeticion();
            }
        }
        imputacion = Imputacion.builder().peticion(peticion).build();
        UIHelper.showDialog(new MantenimientoImputacionDialog(pantalla, imputacion));

    }

    /**
     * Edita el elemento seleccionado
     */
    public void evtBtnEditar() {
        Component focusedComponent = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        Object result = null;
        if (focusedComponent == pantalla.getArbol()) {
            Peticion peticion = getPeticionNode();
            if (peticion.getId() != null) {
                result = UIHelper.showDialog(new MantenimientoPeticionDialog(pantalla, peticion));
            } else if (peticion.getSubCategoria() != null) {
                result = UIHelper.showDialog(new MantenimientoSubCategoriaDialog(pantalla, peticion.getSubCategoria()));
            } else {
                result = UIHelper.showDialog(new MantenimientoCategoriaDialog(pantalla, peticion.getCategoria()));
            }
        } else if (focusedComponent == pantalla.getTblPeticion()) {
            Peticion peticion = getTblPeticionSelection();
            if (peticion != null) {
                result = UIHelper.showDialog(new MantenimientoPeticionDialog(pantalla, peticion));
            }
        } else if (focusedComponent == pantalla.getTblImputacion()) {
            Imputacion imputacion = getTblImputacionSelection();
            if (imputacion != null) {
                if (UIHelper.showDialog(new MantenimientoImputacionDialog(pantalla, imputacion)) != null) {
                    updateTabPane();
                }
            }
        }
        // Si han ocurrido cambios que puedan implicar cambios en el arbol, lo refrescamos
        if (result != null) {
            selectNode((DataBaseEntity) result);
        }
    }

    /**
     * Borra el elemento seleccionado
     */
    public void evtBtnBorrar() {
        String message;
        try {
            Component focusedComponent = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
            DataBaseEntity object = null;
            if (focusedComponent == pantalla.getArbol()) {
                Peticion peticion = getPeticionNode();
                if (peticion.getId() != null) {

                    object = borrarPeticion(peticion);

                } else if (peticion.getSubCategoria() != null) {
                    message = UIHelper.getLiteral("confirmacion.borrar.subcategoria", peticion.getSubCategoria().toString());
                    if (!UIHelper.confirmAction(pantalla, message)) {
                        return;
                    }
                    AppHelper.getSubCategoriaService().delete(peticion.getSubCategoria().getId());
                    object = peticion.getCategoria();
                } else {
                    message = UIHelper.getLiteral("confirmacion.borrar.categoria", peticion.getCategoria().toString());
                    if (!UIHelper.confirmAction(pantalla, message)) {
                        return;
                    }
                    AppHelper.getCategoriaService().delete(peticion.getCategoria().getId());
                }
                if (object != null) {
                    selectNode(object);
                }
            } else if (focusedComponent == pantalla.getTblPeticion()) {
                Peticion peticion = getTblPeticionSelection();
                if (peticion != null) {
                    object = borrarPeticion(peticion);
                    if (object != null) {
                        selectNode(object);
                    }
                }
            } else if (focusedComponent == pantalla.getTblImputacion()) {
                Imputacion imputacion = getTblImputacionSelection();
                if (imputacion != null) {
                    message = UIHelper.getLiteral("confirmacion.borrar.imputacion");
                    if (!UIHelper.confirmAction(pantalla, message)) {
                        return;
                    }
                    AppHelper.getImputacionService().delete(imputacion.getId());
                    updateTabPane();
                }
            }
        } catch (TareasApplicationException e) {
            e.showErrors(pantalla);
        }
    }

    private DataBaseEntity borrarPeticion(Peticion peticion) throws TareasApplicationException {
        String message;
        message = UIHelper.getLiteral("confirmacion.borrar.peticion", peticion.toString());
        if (!UIHelper.confirmAction(pantalla, message)) {
            return null;
        }
        AppHelper.getPeticionService().delete(peticion.getId());
        return peticion.getSubCategoria() != null ? peticion.getSubCategoria() : peticion.getCategoria();
    }

    private List<Peticion> filtraPeticion(Long idCategoria, Long idSubCategoria) {
        List<Peticion> peticionesRama = peticiones.stream()
                .filter(p -> (idCategoria == null || (idCategoria.equals(VACIO) && p.getCategoria() == null) || (p.getCategoria() != null && Objects.equals(idCategoria, p.getCategoria().getId())))
                && (idSubCategoria == null || (idSubCategoria.equals(VACIO) && p.getSubCategoria() == null) || (p.getSubCategoria() != null && Objects.equals(idSubCategoria, p.getSubCategoria().getId()))))
                .collect(Collectors.toList());
        return peticionesRama;
    }

    private void rellenaRama(DefaultMutableTreeNode parentNode, Long idCategoria, Long idSubCategoria) {
        List<Peticion> peticionesRama = filtraPeticion(idCategoria, idSubCategoria);
        peticionesRama.stream().map(p -> new DefaultMutableTreeNode(p)).forEachOrdered(peticionesSinCategoriaNode -> {
            parentNode.add(peticionesSinCategoriaNode);
        });
    }

    public void rellenaArbol() {
        // Consultamos la información en la BBDD
        List<Categoria> categorias = AppHelper.getCategoriaService().findAll();
        peticiones = AppHelper.getPeticionService().findAll();
        //        
        JTree arbol = pantalla.getArbol();
        //Creamos la raíz
        root = new DefaultMutableTreeNode(UIHelper.getLiteral("arbol.raiz"));
        // Recorremos todas las categorías, subnodo de la raíz
        for (Categoria categoria : categorias) {
            DefaultMutableTreeNode categoriaNode = new DefaultMutableTreeNode(categoria);
            root.add(categoriaNode);
            //Filtramos para obtener las subcategorias de la categoria actual
            List<SubCategoria> subCategorias = AppHelper.getSubCategoriaService().findByCategoria(categoria);
            // Recorremos las categorias filtrada
            for (SubCategoria subCategoria : subCategorias) {
                DefaultMutableTreeNode SubCategoriaNode = new DefaultMutableTreeNode(subCategoria);
                categoriaNode.add(SubCategoriaNode);
                // Añadimos las peticionesRama vinculadas a la subcategoria
                rellenaRama(SubCategoriaNode, categoria.getId(), subCategoria.getId());
            }
        }
        arbol.setModel(new DefaultTreeModel(root));
        arbol.repaint();
        //tree.setCellRenderer(new CustomTreeCellRenderer());  
        TreePath path = new TreePath(root.getPath());
        arbol.setSelectionPath(path);
    }

    public void selectNodeById(JTree tree, DataBaseEntity object) {
        if (tree == null) {
            return;
        }
        //Si no se indica el objeto seleccionamos la raíz
        if (object == null) {
            TreePath rootPath = new TreePath(root.getPath());
            tree.setSelectionPath(rootPath);
            return;
        }
        DefaultMutableTreeNode raiz = (DefaultMutableTreeNode) tree.getModel().getRoot();
        DefaultMutableTreeNode node = findNodeById(raiz, object);

        if (node != null) {
            TreePath path = new TreePath(node.getPath());
            tree.setSelectionPath(path);
            tree.scrollPathToVisible(path);
            if (!node.isLeaf()) {
                tree.expandPath(path);
            }
        }
    }

    public static DefaultMutableTreeNode findNodeById(DefaultMutableTreeNode root, DataBaseEntity object) {
        if (root.getUserObject() instanceof DataBaseEntity) {
            DataBaseEntity treeObject = ((DataBaseEntity) root.getUserObject());
            if (treeObject.equals(object)) {
                return root;
            }
        }
        for (int i = 0; i < root.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) root.getChildAt(i);
            DefaultMutableTreeNode result = findNodeById(child, object);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    private void evtBtnFiltrar() {

    }

    /**
     * Se ejecuta al cambiar el elemento seleccionado el arbol
     *
     * @param e
     */
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        List<Peticion> peticionesFiltradas = null;

        Peticion peticion = getPeticionNode();

        //TODO: hacer algo similar si selecciona las tablas y no hay elementos seleccionados
        boolean isRoot = (peticion == null);
        pantalla.getBtnEditar().setEnabled(!isRoot);
        pantalla.getBtnBorrar().setEnabled(!isRoot);
        //Desmarcar la seleccion de las tablas
        pantalla.getTblPeticion().clearSelection();
        pantalla.getTblImputacion().clearSelection();
        pantalla.getTblDocumento().clearSelection();

        String texto = null;

        if (peticion == null) {
            texto = "btnCrearCategoria";
            peticionesFiltradas = peticiones;
        } else if (peticion.getId() == null) {
            Long idCategoria = null;
            Long idSubCategoria = null;
            if (peticion.getSubCategoria() != null) {
                idCategoria = peticion.getSubCategoria().getCategoria().getId();
                idSubCategoria = peticion.getSubCategoria().getId();
                texto = "btnCrearTarea";
            } else if (peticion.getCategoria() != null) {
                idCategoria = peticion.getCategoria().getId();
                texto = "btnCrearSubCategoria";
            }
            peticionesFiltradas = filtraPeticion(idCategoria, idSubCategoria);
        }
        PeticionTableModel tableModel = (PeticionTableModel) pantalla.getTblPeticion().getModel();
        // Si hay una petición la seleccionamos en la tabla
        if (peticion != null && peticion.getId() != null) {
            if (!peticion.equals(getTblPeticionSelection())) {
                peticionesFiltradas = tableModel.getData();
                int row = peticionesFiltradas.indexOf(peticion);
                if (row >= 0) {
                    pantalla.getTblPeticion().setRowSelectionInterval(row, row);
                } 
            }
        } else {
            //Actualizar el texto del botón crear
            UIHelper.setComponentText(pantalla.getBtnCrear(), texto);
            pantalla.getBtnCrear().repaint();
            tableModel.clearData();
            tableModel.setData(peticionesFiltradas);
        }
        updateTabPane();
    }

    /**
     * Se ejecuta al cambiar una pestaña
     *
     * @param e
     */
    @Override
    public void stateChanged(ChangeEvent e
    ) {
        updateTabPane();
    }

    /* Retorna la peticion seleccionada en la tabla de peticiones */
    private Peticion getTblPeticionSelection() {
        JTable tblPeticion = pantalla.getTblPeticion();
        int selectedRow = tblPeticion.getSelectedRow();
        List<Peticion> lista = ((PeticionTableModel) tblPeticion.getModel()).getData();
        Peticion peticion = null;
        if (selectedRow >= 0) {
            peticion = lista.get(selectedRow);
        }
        return peticion;
    }

    /* Retorna la peticion seleccionada en la tabla de peticiones */
    private Imputacion getTblImputacionSelection() {
        JTable tblImputacion = pantalla.getTblImputacion();
        int selectedRow = tblImputacion.getSelectedRow();
        List<Imputacion> lista = ((ImputacionTableModel) tblImputacion.getModel()).getData();
        Imputacion imputacion = null;
        if (selectedRow >= 0) {
            imputacion = lista.get(selectedRow);
        }
        return imputacion;
    }

    /**
     * Actualiza la información de la pestaña activa
     */
    private void updateTabPane() {
        switch (pantalla.getTbpPanel().getSelectedIndex()) {
            case PNL_PETICION:
                break;
            case PNL_IMPUTACION:
                Peticion peticion = getTblPeticionSelection();
                List<Peticion> peticionesFiltradas = null;
                if (peticion == null) {
                    peticionesFiltradas = ((PeticionTableModel) pantalla.getTblPeticion().getModel()).getData();
                } else {
                    peticionesFiltradas = new ArrayList();
                    peticionesFiltradas.add(peticion);
                }
                List<Imputacion> imputaciones = AppHelper.getImputacionService().findByPeticiones(peticionesFiltradas);
                ImputacionTableModel tableModel = (ImputacionTableModel) pantalla.getTblImputacion().getModel();
                tableModel.clearData();
                tableModel.setData(imputaciones);
                tableModel.fireTableDataChanged();
                pantalla.getTblImputacion().clearSelection();
                actualizaResumenImputacion(imputaciones);
                break;

            case PNL_DOCUMENTO:
        }
    }

    private void actualizaResumenImputacion(List<Imputacion> imputaciones) {
        Long fechaMin = null;
        Long fechaMax = null;
        Double totalHorasNormales = 0.0;
        Double totalHorasExtra = 0.0;
        Double totalHoras;
        if (imputaciones != null) {
            for (Imputacion imputacion : imputaciones) {
                Long fecha = imputacion.getFecha();
                if (fechaMin == null) {
                    fechaMin = fecha;
                    fechaMax = fecha;
                } else {
                    if (fecha != null && fecha < fechaMin) {
                        fechaMin = fecha;
                    }
                    if (fecha != null && fecha > fechaMax) {
                        fechaMax = fecha;
                    }
                }
                Double horas = imputacion.getHorasReal();
                if (imputacion.getExtra() == null || imputacion.getExtra().equals("N")) {
                    totalHorasNormales = totalHorasNormales + horas;
                } else {
                    totalHorasExtra = totalHorasExtra + horas;
                }
            }
        }
        pantalla.getTxtFechaMinima().setText(AppHelper.dateBdToString(fechaMin));
        pantalla.getTxtFechaMaxima().setText(AppHelper.dateBdToString(fechaMax));
        pantalla.getTxtHorasNormales().setText(totalHorasNormales.toString());
        pantalla.getTxtHorasExtra().setText(totalHorasExtra.toString());
        totalHoras = totalHorasNormales + totalHorasExtra;
        pantalla.getTxtTotalHoras().setText(totalHoras.toString());
    }

    private void evtBtnImportar() {
        try {
            String excelFilePath = "c:\\temp\\Peticiones.xlsx"; // Ruta al archivo Excel
            ImportarService.importFromExcel(excelFilePath);
            UIHelper.showMessage(pantalla, "importacion.finalizada.ok");
        } catch (TareasApplicationException e) {
            e.showErrors(pantalla);
        }
    }

    private void evtBtnExportar() {
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
      Peticion peticionTabla = getTblPeticionSelection();
      Peticion peticionArbol = getPeticionNode();     
      if (peticionArbol == null || (peticionTabla != null && !peticionTabla.equals(peticionArbol))){
          selectNode(peticionTabla);
      }
    }
}
