/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.ui.table.model;

import es.luisev.tareas.ui.table.cabecera.Cabecera;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author Luis-Enrique.Varona
 */
public abstract class DefaultTableModel <T> extends AbstractTableModel{
	@Getter
	protected List<T> data;
	
	@Getter
	protected Cabecera cabecera;
	
	/**
	 * @param cabecera
	 */
	public DefaultTableModel(Cabecera cabecera) {
		this.data = new ArrayList<>();
		this.cabecera = cabecera;
	}
        
	/**
	 * @param newData
	 */
	public void setData(List<T> newData) {
		if (!CollectionUtils.isEmpty(this.data)) {
			this.data.clear();
		}
		
		if (!CollectionUtils.isEmpty(newData)) {
			this.data.addAll(newData);
		}
	}      
        
	/**
	 * @param newData
	 */
	public void addData(T newData) {
		this.data.add(newData);
		this.fireTableDataChanged();
	}
	
	/**
	 * @param object
	 */
	public void removeData(T object) {
		this.data.remove(object);
		this.fireTableDataChanged();
	}        
        
	/**
	 * 
	 */
	public void clearData() {
		this.data.clear();
		this.fireTableDataChanged();
	}

	/**
	 *
	 */
	@Override
	public String getColumnName(int column) {
		return cabecera.getColumnIdentifiers().get(column);
	}
        
	/**
	 *
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return cabecera.getColumnClasses().get(columnIndex);
	}        

	/**
	 *
	 */
	@Override
	public int getRowCount() {
		return data.size();
	}

	/**
	 *
	 */
	@Override
	public int getColumnCount() {
		return cabecera.getColumnIdentifiers().size();
	}

	/**
	 *
	 */
	//public abstract Object getValueAt(int rowIndex, int columnIndex);
	
	/**
	 * @param rowIndex
	 * @return
	 */
	public T getSelectedRow(int rowIndex) {
		return (rowIndex != -1) ? data.get(rowIndex) : null;
	}        
    
}
