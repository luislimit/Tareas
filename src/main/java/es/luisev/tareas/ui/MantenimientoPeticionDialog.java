/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui;

import es.luisev.tareas.model.Categoria;
import es.luisev.tareas.model.Estado;
import es.luisev.tareas.model.Peticion;
import es.luisev.tareas.model.SubCategoria;
import es.luisev.tareas.model.Usuario;
import es.luisev.tareas.ui.listener.MantenimientoPeticionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class MantenimientoPeticionDialog extends DialogoBase {

    /**
     * Creates new form MantenimientoPeticionDialog
     *
     * @param parent
     * @param peticion
     */
    public MantenimientoPeticionDialog(JFrame parent, Peticion peticion) {
        super(parent, peticion);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblAsunto = new javax.swing.JLabel();
        txtAsunto = new javax.swing.JTextField();
        lblUsuario = new javax.swing.JLabel();
        cmbUsuario = new javax.swing.JComboBox<>();
        lblCategoria = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        lblSubCategoria = new javax.swing.JLabel();
        cmbSubCategoria = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        lblDescripcion = new javax.swing.JLabel();
        scpDescripcion = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        lblInicioPrevisto = new javax.swing.JLabel();
        txtInicioPrevisto = new javax.swing.JTextField();
        lblFinPrevisto = new javax.swing.JLabel();
        txtFinPrevisto = new javax.swing.JTextField();
        lblHorasPrevista = new javax.swing.JLabel();
        txtHorasPrevista = new javax.swing.JTextField();
        lblInicioReal = new javax.swing.JLabel();
        txtInicioReal = new javax.swing.JTextField();
        lblFinReal = new javax.swing.JLabel();
        txtFinReal = new javax.swing.JTextField();
        lblHorasReal = new javax.swing.JLabel();
        txtHorasReal = new javax.swing.JTextField();
        lblPorcentaje = new javax.swing.JLabel();
        txtPorcentaje = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento de Peticiones");

        lblCodigo.setText("Código");

        lblAsunto.setText("Asunto");

        lblUsuario.setText("Usuario");

        lblCategoria.setText("Categoria");

        lblSubCategoria.setText("Subcategoria");

        lblEstado.setText("Estado");

        lblDescripcion.setText("Descripción");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        scpDescripcion.setViewportView(txtDescripcion);

        lblInicioPrevisto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblInicioPrevisto.setText("Inicio Previsto");

        lblFinPrevisto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFinPrevisto.setText("Fin Previsto");

        lblHorasPrevista.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHorasPrevista.setText("Horas Previstas");

        lblInicioReal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblInicioReal.setText("Inicio Real");

        lblFinReal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFinReal.setText("Fin Real");

        txtFinReal.setToolTipText("");

        lblHorasReal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHorasReal.setText("Horas Reales");

        lblPorcentaje.setText("Porcentaje");

        txtPorcentaje.setToolTipText("");

        btnLimpiar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setToolTipText("");
        btnLimpiar.setMaximumSize(new java.awt.Dimension(65, 65));
        btnLimpiar.setMinimumSize(new java.awt.Dimension(65, 65));

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salvar.png"))); // NOI18N
        btnGuardar.setText("Guardar");

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salida_32.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDescripcion)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblInicioPrevisto, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtInicioPrevisto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblFinPrevisto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(79, 79, 79)
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(79, 79, 79)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(292, 292, 292))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtFinPrevisto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblHorasPrevista)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtHorasPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblInicioReal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtInicioReal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblFinReal, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFinReal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblHorasReal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtHorasReal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(scpDescripcion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                    .addComponent(lblCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCodigo))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtAsunto))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblSubCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbSubCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAsunto)
                    .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategoria)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSubCategoria)
                    .addComponent(cmbSubCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstado)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scpDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInicioPrevisto)
                    .addComponent(txtInicioPrevisto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFinPrevisto)
                    .addComponent(txtFinPrevisto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInicioReal)
                    .addComponent(txtInicioReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFinReal)
                    .addComponent(txtFinReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHorasReal)
                    .addComponent(txtHorasReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHorasPrevista)
                    .addComponent(txtHorasPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPorcentaje)
                    .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<Categoria> cmbCategoria;
    private javax.swing.JComboBox<Estado> cmbEstado;
    private javax.swing.JComboBox<SubCategoria> cmbSubCategoria;
    private javax.swing.JComboBox<Usuario> cmbUsuario;
    private javax.swing.JLabel lblAsunto;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFinPrevisto;
    private javax.swing.JLabel lblFinReal;
    private javax.swing.JLabel lblHorasPrevista;
    private javax.swing.JLabel lblHorasReal;
    private javax.swing.JLabel lblInicioPrevisto;
    private javax.swing.JLabel lblInicioReal;
    private javax.swing.JLabel lblPorcentaje;
    private javax.swing.JLabel lblSubCategoria;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JScrollPane scpDescripcion;
    private javax.swing.JTextField txtAsunto;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtFinPrevisto;
    private javax.swing.JTextField txtFinReal;
    private javax.swing.JTextField txtHorasPrevista;
    private javax.swing.JTextField txtHorasReal;
    private javax.swing.JTextField txtInicioPrevisto;
    private javax.swing.JTextField txtInicioReal;
    private javax.swing.JTextField txtPorcentaje;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void initListener() {
        initComponents();
        MantenimientoPeticionListener listener = new MantenimientoPeticionListener(this);
        btnGuardar.addActionListener(listener);
        btnCancelar.addActionListener(listener);
        btnLimpiar.addActionListener(listener);
    }

    @Override
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    @Override
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    @Override
    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JComboBox<Categoria> getCmbCategoria() {
        return cmbCategoria;
    }

    public JComboBox<Estado> getCmbEstado() {
        return cmbEstado;
    }

    public JComboBox<SubCategoria> getCmbSubCategoria() {
        return cmbSubCategoria;
    }

    public JComboBox<Usuario> getCmbUsuario() {
        return cmbUsuario;
    }

    public JTextField getTxtAsunto() {
        return txtAsunto;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTextArea getTxtDescripcion() {
        return txtDescripcion;
    }

    public JTextField getTxtFinPrevisto() {
        return txtFinPrevisto;
    }

    public JTextField getTxtFinReal() {
        return txtFinReal;
    }

    public JTextField getTxtHorasPrevista() {
        return txtHorasPrevista;
    }

    public JTextField getTxtHorasReal() {
        return txtHorasReal;
    }

    public JTextField getTxtInicioPrevisto() {
        return txtInicioPrevisto;
    }

    public JTextField getTxtInicioReal() {
        return txtInicioReal;
    }

    public JTextField getTxtPorcentaje() {
        return txtPorcentaje;
    }

    @Override
    protected void initLiterals() {
        setTitulo();
        setTexto(lblAsunto, "lblAsunto");
        setTexto(lblCategoria, "lblCategoria");
        setTexto(lblCodigo, "lblCodigo");
        setTexto(lblDescripcion, "lblDescripcion");
        setTexto(lblEstado, "lblEstado");
        setTexto(lblFinPrevisto, "lblFinPrevisto");
        setTexto(lblFinReal, "lblFinReal");
        setTexto(lblHorasPrevista, "lblHorasPrevista");
        setTexto(lblHorasReal, "lblHorasReal");
        setTexto(lblInicioPrevisto, "lblInicioPrevisto");
        setTexto(lblInicioReal, "lblInicioReal");
        setTexto(lblPorcentaje, "lblPorcentaje");
        setTexto(lblSubCategoria, "lblSubCategoria");
        setTexto(lblUsuario, "lblUsuario");
    }
}
