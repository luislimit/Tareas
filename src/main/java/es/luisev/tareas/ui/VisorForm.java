/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui;

import es.luisev.tareas.ui.combobox.listener.CmbPeriodoFechaListener;
import es.luisev.tareas.ui.combobox.model.CmbPeriodoFecha;
import es.luisev.tareas.ui.table.cabecera.Cabecera;
import es.luisev.tareas.ui.table.cabecera.ImputacionTableCabecera;
import es.luisev.tareas.ui.table.cabecera.PeticionTableCabecera;
import es.luisev.tareas.ui.table.model.ImputacionTableModel;
import es.luisev.tareas.ui.table.model.PeticionTableModel;
import es.luisev.tareas.ui.listener.VisorListener;
import es.luisev.tareas.ui.listener.VisorMouseListener;
import es.luisev.tareas.utils.UIHelper;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class VisorForm extends JFrame {

    VisorListener visorListener;

    /**
     * Creates new form VisorForm
     */
    public VisorForm() {
        initComponents();
        // Configurar la ventana para abrirse maximizada
        initForm();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbar = new javax.swing.JToolBar();
        btnCrear = new javax.swing.JButton();
        btnCrearImputacion = new javax.swing.JButton();
        btnCrearDocumento = new javax.swing.JButton();
        sepEditar = new javax.swing.JToolBar.Separator();
        btnEditar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        sepFiltrarExcel = new javax.swing.JToolBar.Separator();
        btnExportar = new javax.swing.JButton();
        btnImportar = new javax.swing.JButton();
        sepUsuario1 = new javax.swing.JToolBar.Separator();
        btnConfiguracion = new javax.swing.JButton();
        btnInformacion = new javax.swing.JButton();
        sepUsuario = new javax.swing.JToolBar.Separator();
        btnFiltrar = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        cmbPeriodoFecha = new javax.swing.JComboBox<>();
        pnlVisor = new javax.swing.JPanel();
        sppArbol = new javax.swing.JSplitPane();
        scpArbol = new javax.swing.JScrollPane();
        arbol = new javax.swing.JTree();
        tbpPanel = new javax.swing.JTabbedPane();
        pnlPeticion = new javax.swing.JPanel();
        scpPeticion = new javax.swing.JScrollPane();
        tblPeticion = new javax.swing.JTable();
        pnlImputacion = new javax.swing.JPanel();
        scpImputacion = new javax.swing.JScrollPane();
        tblImputacion = new javax.swing.JTable();
        pnlImputacionPie = new javax.swing.JPanel();
        lblFechaMinima = new javax.swing.JLabel();
        txtFechaMinima = new javax.swing.JTextField();
        lblFechaMaxima = new javax.swing.JLabel();
        txtFechaMaxima = new javax.swing.JTextField();
        lblHorasNormales = new javax.swing.JLabel();
        txtHorasNormales = new javax.swing.JTextField();
        lblHorasExtra = new javax.swing.JLabel();
        txtHorasExtra = new javax.swing.JTextField();
        lblTotalHoras = new javax.swing.JLabel();
        txtTotalHoras = new javax.swing.JTextField();
        pnlDocumento = new javax.swing.JPanel();
        scpDocumento = new javax.swing.JScrollPane();
        tblDocumento = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Visor de Peticiones");
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(1080, 800));

        toolbar.setRollover(true);

        btnCrear.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crear.png"))); // NOI18N
        btnCrear.setText("Categoría");
        btnCrear.setToolTipText("Nueva Categoría");
        btnCrear.setFocusable(false);
        btnCrear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCrear.setMaximumSize(new java.awt.Dimension(60, 60));
        btnCrear.setMinimumSize(new java.awt.Dimension(60, 60));
        btnCrear.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(btnCrear);

        btnCrearImputacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnCrearImputacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/horas.png"))); // NOI18N
        btnCrearImputacion.setText("Imputación");
        btnCrearImputacion.setToolTipText("Nueva Imputación");
        btnCrearImputacion.setFocusable(false);
        btnCrearImputacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCrearImputacion.setMaximumSize(new java.awt.Dimension(60, 60));
        btnCrearImputacion.setMinimumSize(new java.awt.Dimension(60, 60));
        btnCrearImputacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(btnCrearImputacion);

        btnCrearDocumento.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnCrearDocumento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/documentos.png"))); // NOI18N
        btnCrearDocumento.setText("Doc");
        btnCrearDocumento.setToolTipText("Nuevo Documento");
        btnCrearDocumento.setFocusable(false);
        btnCrearDocumento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCrearDocumento.setMaximumSize(new java.awt.Dimension(60, 60));
        btnCrearDocumento.setMinimumSize(new java.awt.Dimension(60, 60));
        btnCrearDocumento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(btnCrearDocumento);
        toolbar.add(sepEditar);

        btnEditar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setToolTipText("Editar");
        btnEditar.setFocusable(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setMaximumSize(new java.awt.Dimension(60, 60));
        btnEditar.setMinimumSize(new java.awt.Dimension(60, 60));
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(btnEditar);

        btnBorrar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/borrar.png"))); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.setToolTipText("Borrar");
        btnBorrar.setFocusable(false);
        btnBorrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBorrar.setMaximumSize(new java.awt.Dimension(60, 60));
        btnBorrar.setMinimumSize(new java.awt.Dimension(60, 60));
        btnBorrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(btnBorrar);
        toolbar.add(sepFiltrarExcel);

        btnExportar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/excel.png"))); // NOI18N
        btnExportar.setText("Exportar");
        btnExportar.setToolTipText("Exportar a Excel");
        btnExportar.setFocusable(false);
        btnExportar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExportar.setMaximumSize(new java.awt.Dimension(60, 60));
        btnExportar.setMinimumSize(new java.awt.Dimension(60, 60));
        btnExportar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(btnExportar);

        btnImportar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnImportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/importar.png"))); // NOI18N
        btnImportar.setText("Importar");
        btnImportar.setToolTipText("Importar desde Excel");
        btnImportar.setFocusable(false);
        btnImportar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImportar.setMaximumSize(new java.awt.Dimension(60, 60));
        btnImportar.setMinimumSize(new java.awt.Dimension(60, 60));
        btnImportar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(btnImportar);
        toolbar.add(sepUsuario1);

        btnConfiguracion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuracion.png"))); // NOI18N
        btnConfiguracion.setText("Configura");
        btnConfiguracion.setToolTipText("Configuración");
        btnConfiguracion.setFocusable(false);
        btnConfiguracion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConfiguracion.setMaximumSize(new java.awt.Dimension(60, 60));
        btnConfiguracion.setMinimumSize(new java.awt.Dimension(60, 60));
        btnConfiguracion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(btnConfiguracion);

        btnInformacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnInformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/info.png"))); // NOI18N
        btnInformacion.setText("Info");
        btnInformacion.setToolTipText("Información");
        btnInformacion.setFocusable(false);
        btnInformacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnInformacion.setMaximumSize(new java.awt.Dimension(60, 60));
        btnInformacion.setMinimumSize(new java.awt.Dimension(60, 60));
        btnInformacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(btnInformacion);
        toolbar.add(sepUsuario);

        btnFiltrar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/filtrar.png"))); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.setToolTipText("Filtrar");
        btnFiltrar.setFocusable(false);
        btnFiltrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFiltrar.setMaximumSize(new java.awt.Dimension(60, 60));
        btnFiltrar.setMinimumSize(new java.awt.Dimension(60, 60));
        btnFiltrar.setPreferredSize(new java.awt.Dimension(60, 60));
        btnFiltrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(btnFiltrar);

        lblUsuario.setText(" LVARONA ");
        lblUsuario.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        lblUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        toolbar.add(lblUsuario);

        cmbPeriodoFecha.setMaximumSize(new java.awt.Dimension(120, 24));
        cmbPeriodoFecha.setMinimumSize(new java.awt.Dimension(120, 24));
        cmbPeriodoFecha.setName(""); // NOI18N
        cmbPeriodoFecha.setPreferredSize(new java.awt.Dimension(120, 24));
        toolbar.add(cmbPeriodoFecha);

        sppArbol.setDividerLocation(150);

        scpArbol.setViewportView(arbol);

        sppArbol.setLeftComponent(scpArbol);

        tblPeticion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Categoría", "SubCategoría", "Petición", "Asunto", "Inicio Programado", "Fin Programado", "Inicio Real", "Fin Real", "Horas Programadas", "Horas Reales", "Estado"
            }
        ));
        tblPeticion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblPeticion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scpPeticion.setViewportView(tblPeticion);

        javax.swing.GroupLayout pnlPeticionLayout = new javax.swing.GroupLayout(pnlPeticion);
        pnlPeticion.setLayout(pnlPeticionLayout);
        pnlPeticionLayout.setHorizontalGroup(
            pnlPeticionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPeticionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpPeticion, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPeticionLayout.setVerticalGroup(
            pnlPeticionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPeticionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpPeticion, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpPanel.addTab("Peticiones", pnlPeticion);

        tblImputacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Categoria", "SubCategoria", "Peticion", "Fecha", "Horas", "Tipo", "Descripción", "Estado"
            }
        ));
        tblImputacion.setToolTipText("");
        tblImputacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblImputacion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scpImputacion.setViewportView(tblImputacion);

        lblFechaMinima.setText("Fecha mínima");

        txtFechaMinima.setEditable(false);

        lblFechaMaxima.setText("Fecha Máxima");

        txtFechaMaxima.setEditable(false);

        lblHorasNormales.setText("Horas normales");

        txtHorasNormales.setEditable(false);
        txtHorasNormales.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblHorasExtra.setText("Horas Extra");

        txtHorasExtra.setEditable(false);
        txtHorasExtra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblTotalHoras.setText("Total Horas");

        txtTotalHoras.setEditable(false);
        txtTotalHoras.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout pnlImputacionPieLayout = new javax.swing.GroupLayout(pnlImputacionPie);
        pnlImputacionPie.setLayout(pnlImputacionPieLayout);
        pnlImputacionPieLayout.setHorizontalGroup(
            pnlImputacionPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImputacionPieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFechaMinima)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFechaMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFechaMaxima)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblHorasNormales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHorasNormales, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(lblHorasExtra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHorasExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(lblTotalHoras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlImputacionPieLayout.setVerticalGroup(
            pnlImputacionPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImputacionPieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlImputacionPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaMinima)
                    .addComponent(txtFechaMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaMaxima)
                    .addComponent(txtFechaMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHorasNormales)
                    .addComponent(txtHorasNormales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHorasExtra)
                    .addComponent(txtHorasExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalHoras))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlImputacionLayout = new javax.swing.GroupLayout(pnlImputacion);
        pnlImputacion.setLayout(pnlImputacionLayout);
        pnlImputacionLayout.setHorizontalGroup(
            pnlImputacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImputacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlImputacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scpImputacion)
                    .addComponent(pnlImputacionPie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlImputacionLayout.setVerticalGroup(
            pnlImputacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImputacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpImputacion, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlImputacionPie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbpPanel.addTab("Imputaciones", pnlImputacion);

        tblDocumento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Categoría", "SubCategoría", "Petición", "Documento", "Ruta", "Tipo documento", "Observaciones"
            }
        ));
        tblDocumento.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblDocumento.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scpDocumento.setViewportView(tblDocumento);

        javax.swing.GroupLayout pnlDocumentoLayout = new javax.swing.GroupLayout(pnlDocumento);
        pnlDocumento.setLayout(pnlDocumentoLayout);
        pnlDocumentoLayout.setHorizontalGroup(
            pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDocumentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDocumentoLayout.setVerticalGroup(
            pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDocumentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpPanel.addTab("Documentos", pnlDocumento);

        sppArbol.setRightComponent(tbpPanel);

        javax.swing.GroupLayout pnlVisorLayout = new javax.swing.GroupLayout(pnlVisor);
        pnlVisor.setLayout(pnlVisorLayout);
        pnlVisorLayout.setHorizontalGroup(
            pnlVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sppArbol)
        );
        pnlVisorLayout.setVerticalGroup(
            pnlVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sppArbol)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVisor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlVisor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JTree arbol;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnCrearDocumento;
    private javax.swing.JButton btnCrearImputacion;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnImportar;
    private javax.swing.JButton btnInformacion;
    private javax.swing.JComboBox<String> cmbPeriodoFecha;
    private javax.swing.JLabel lblFechaMaxima;
    private javax.swing.JLabel lblFechaMinima;
    private javax.swing.JLabel lblHorasExtra;
    private javax.swing.JLabel lblHorasNormales;
    private javax.swing.JLabel lblTotalHoras;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlDocumento;
    private javax.swing.JPanel pnlImputacion;
    private javax.swing.JPanel pnlImputacionPie;
    private javax.swing.JPanel pnlPeticion;
    private javax.swing.JPanel pnlVisor;
    private javax.swing.JScrollPane scpArbol;
    private javax.swing.JScrollPane scpDocumento;
    private javax.swing.JScrollPane scpImputacion;
    private javax.swing.JScrollPane scpPeticion;
    private javax.swing.JToolBar.Separator sepEditar;
    private javax.swing.JToolBar.Separator sepFiltrarExcel;
    private javax.swing.JToolBar.Separator sepUsuario;
    private javax.swing.JToolBar.Separator sepUsuario1;
    private javax.swing.JSplitPane sppArbol;
    private javax.swing.JTable tblDocumento;
    private javax.swing.JTable tblImputacion;
    private javax.swing.JTable tblPeticion;
    private javax.swing.JTabbedPane tbpPanel;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JTextField txtFechaMaxima;
    private javax.swing.JTextField txtFechaMinima;
    private javax.swing.JTextField txtHorasExtra;
    private javax.swing.JTextField txtHorasNormales;
    private javax.swing.JTextField txtTotalHoras;
    // End of variables declaration//GEN-END:variables

    public JTree getArbol() {
        return arbol;
    }

    public JButton getBtnBorrar() {
        return btnBorrar;
    }

    public JButton getBtnConfiguracion() {
        return btnConfiguracion;
    }

    public JButton getBtnCrearImputacion() {
        return btnCrearImputacion;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnExcel() {
        return btnExportar;
    }

    public JButton getBtnFiltrar() {
        return btnFiltrar;
    }

    public JButton getBtnInformacion() {
        return btnInformacion;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public JTable getTblDocumento() {
        return tblDocumento;
    }

    public JTable getTblImputacion() {
        return tblImputacion;
    }

    public JTable getTblPeticion() {
        return tblPeticion;
    }

    public JButton getBtnCrearDocumento() {
        return btnCrearDocumento;
    }

    public JTextField getTxtFechaMaxima() {
        return txtFechaMaxima;
    }

    public JTextField getTxtFechaMinima() {
        return txtFechaMinima;
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    public JTextField getTxtHorasExtra() {
        return txtHorasExtra;
    }

    public JTextField getTxtHorasNormales() {
        return txtHorasNormales;
    }

    public JTextField getTxtTotalHoras() {
        return txtTotalHoras;
    }

    public JTabbedPane getTbpPanel() {
        return tbpPanel;
    }

    private void setTexto(Component component, String clave) {
        UIHelper.setComponentText(component, clave);
    }

    public VisorListener getVisorListener() {
        return visorListener;
    }

    public JButton getBtnExportar() {
        return btnExportar;
    }

    public JButton getBtnImportar() {
        return btnImportar;
    }

    public JComboBox<String> getCmbPeriodoFecha() {
        return cmbPeriodoFecha;
    }
    
    private void initForm() {
        initListener();
        initLiterals();
        initModels();
        cmbPeriodoFecha.setVisible(false);
        visorListener.rellenaArbol();
    }
    private void initListener() {
        visorListener = new VisorListener(this);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        btnCrear.addActionListener(visorListener);
        btnCrearImputacion.addActionListener(visorListener);
        btnCrearDocumento.addActionListener(visorListener);

        btnBorrar.addActionListener(visorListener);
        btnEditar.addActionListener(visorListener);
        btnExportar.addActionListener(visorListener);
        btnImportar.addActionListener(visorListener);        
        btnFiltrar.addActionListener(visorListener);
        btnInformacion.addActionListener(visorListener);
        btnConfiguracion.addActionListener(visorListener);
        arbol.addTreeSelectionListener(visorListener);
        tbpPanel.addChangeListener(visorListener);
        
        VisorMouseListener mouseListener = new VisorMouseListener(this);
        tblPeticion.addMouseListener(mouseListener);
        tblImputacion.addMouseListener(mouseListener);
        tblDocumento.addMouseListener(mouseListener);
        arbol.addMouseListener(mouseListener);
        tblPeticion.getSelectionModel().addListSelectionListener(visorListener);
        cmbPeriodoFecha.addActionListener(new CmbPeriodoFechaListener(cmbPeriodoFecha));
    }
    
    public void initLiterals() {
        UIHelper.setWindowTitle(this);
        setTexto(btnFiltrar, "btnFiltrar");
        //
        setTexto(btnCrear, "btnCrearCategoria");
        setTexto(btnCrearImputacion, "btnCrearImputacion");
        setTexto(btnCrearDocumento, "btnCrearDocumento");        
        //
        setTexto(btnBorrar, "btnBorrar");
        setTexto(btnEditar, "btnEditar");
        //
        setTexto(btnExportar, "btnExportar");
        setTexto(btnImportar, "btnImportar");
        setTexto(btnInformacion, "btnInformacion");
        setTexto(btnConfiguracion, "btnConfiguracion");
    }

    public void initModels() {
        Cabecera cabeceraPeticion = new PeticionTableCabecera();
        PeticionTableModel peticionTableModel = new PeticionTableModel(cabeceraPeticion);
        UIHelper.setTableModel(tblPeticion, peticionTableModel);
        //
        Cabecera cabeceraImputacion = new ImputacionTableCabecera();
        ImputacionTableModel imputacionTableModel = new ImputacionTableModel(cabeceraImputacion);
        UIHelper.setTableModel(tblImputacion, imputacionTableModel);
        
        cmbPeriodoFecha.setModel(new CmbPeriodoFecha());
    }
}
