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
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
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
@Table(name = "t_peticion")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Peticion extends DBEntity{

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
    
    @OneToMany(mappedBy = "peticion", fetch = FetchType.LAZY) //, cascade = CascadeType.ALL, orphanRemoval = true
    private List<Imputacion> imputaciones;
    
    @Override
    public String toString(){
        return codigo + "-" + asunto;
    }    
}
