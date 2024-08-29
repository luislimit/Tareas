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
import java.util.Objects;

/**
 *
 * @author Luis-Enrique.Varona
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "t_peticion")
public class Peticion extends DataBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String asunto;
    private Double porcentaje;
    
    @ManyToOne    
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    
    @Column(name = "fec_alta")
    private Long fecAlta;

    @Column(name = "fec_prevista_inicio")
    private Long fecPrevistaInicio;

    @Column(name = "fec_prevista_fin")
    private Long fecPrevistaFin;

    @Column(name = "fec_real_inicio")
    private Long fecRealInicio;

    @Column(name = "fec_real_fin")
    private Long fecRealFin;

    @Column(name = "horas_prevista")
    private Double horasPrevista;

    @Column(name = "horas_real")
    private Double horasReal;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name="id_estado")
    Estado estado;

    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name="id_subcategoria")
    private SubCategoria subCategoria;
    
    @Override
    public String toString(){
        return codigo + "-" + asunto;
    }    
    @Override
    public Long getPrimaryKey() {
        return id;
    }     
    
 @Override
    public boolean equals(Object o) {
        // Verifica si es el mismo objeto
        if (this == o) return true;
        // Verifica si el objeto es null o si las clases son diferentes
        if (o == null || getClass() != o.getClass()) return false;
        // Convierte el objeto al tipo Peticion
        Peticion peticion = (Peticion) o;
        // Compara los campos relevantes
        return Objects.equals(id, peticion.id);
    }

    @Override
    public int hashCode() {
        // Usa Objects.hash para generar el hash code basado en los campos relevantes
        return Objects.hash(id);
    }    
}