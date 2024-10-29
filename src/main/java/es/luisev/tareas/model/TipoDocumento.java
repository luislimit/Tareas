/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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
@Table (name="t_tipodocumento")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class TipoDocumento extends DBEntity{
    
    @ToString.Include
    private String nombre;
    
    @Override
    public String toString(){
        return nombre;
    }       
   
 }