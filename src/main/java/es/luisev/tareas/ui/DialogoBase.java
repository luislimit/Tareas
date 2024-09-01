/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui;

import es.luisev.tareas.utils.UIHelper;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 *
 * @author Luis-Enrique.Varona
 */
public abstract class DialogoBase extends JDialog {

    protected Object paramObject;
    protected Object returnObject = null;
    protected final VisorForm frameParent;

    public DialogoBase(VisorForm frame, Object paramObject, boolean modal) {
        super(frame, modal);
        this.paramObject = paramObject;
        this.frameParent = frame;
        inicializa();
    }

    public DialogoBase(VisorForm frame, Object paramObject) {
        this(frame, paramObject, true);
    }

    private void inicializa() {
        initListener();
        initLiterals();
        setTexto(getBtnLimpiar(), "btnLimpiar");
        setTexto(getBtnGuardar(), "btnGuardar");
        setTexto(getBtnCancelar(), "btnCancelar");
    }

    protected abstract void initLiterals();

    protected abstract void initListener();

    public abstract JButton getBtnCancelar();

    public abstract JButton getBtnGuardar();

    public abstract JButton getBtnLimpiar();

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    public Object getParamObject() {
        return paramObject;
    }

    public void setTitulo() {
        UIHelper.setWindowTitle(this);
    }

    public void setTexto(Component component, String clave) {
        UIHelper.setComponentText(component, clave);
    }

    public VisorForm getFrameParent() {
        return frameParent;
    }
}
