/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author Luis-Enrique.Varona
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table (name="t_subcategoria")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class SubCategoria extends DBEntity{
    
    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;

    private String codigo;    
    private String nombre;

    @Column(name = "fec_alta")
    private String fecAlta;    
    
    @Override
    public String toString(){
        return codigo +"-" +nombre;
    }     
}
