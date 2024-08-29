/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Luis-Enrique.Varona
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "t_imputacion")
public class Imputacion extends DataBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_peticion")
    Peticion peticion;
    
    @ManyToOne    
    @JoinColumn(name="id_usuario")
    Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_estado")
    Estado estado;
    
    @ManyToOne
    @JoinColumn(name="id_estado_previo")
    Estado estadoPrevio;    
    
    @Column(name = "fec_alta")
    private Long fecAlta;

    private Long fecha;

    @Column(name = "horas_real")
    private Double horasReal;
    
    @Column(name = "extra")
    private String extra;

    private String descripcion;
    
    @Override
    public Long getPrimaryKey() {
        return id;
    }       
}
