/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Luis-Enrique.Varona
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Filtro extends DataBaseEntity{
    Categoria categoria;
    SubCategoria subCategoria;
    Peticion peticion;
    //
    Boolean pendiente;
    Boolean enCurso;
    Boolean retenida;
    Boolean anulada;
    Boolean finalizada;
    Boolean cerrada;
    //
    String asuntoContiene;
    Long fechaInicioProgramadoDesde;
    Long fechaInicioProgramadoHasta;
    Long fechaInicioRealDesde;
    Long fechaInicioRealHasta;
    Double horasRealesDesde;
    Double horasRealesHasta;
    Double porcentajeRealizacionDesde;
    Double porcentajeRealizacionHasta;
    Usuario usuario;
    //
    Long fechaImputacionDesde;
    Long fechaImputacionHasta;
    Double horasImputadasDesde;
    Double horasImputadasHasta;
    int tipoHoras;
    //
    Long tipoDocumento;
    String nombreContiene;
    String ruta;
}
