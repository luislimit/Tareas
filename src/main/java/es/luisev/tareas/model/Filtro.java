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
public class Filtro {
    Categoria categoria;
    SubCategoria subCategoria;
    Peticion peticion;
    Estado estado;
    //
    String asuntoContiene;
    Long inicioPrevistoDesde;
    Long inicioPrevistoHasta;
    Long inicioRealDesde;
    Long inicioRealHasta;
    
    Long finPrevistoDesde;
    Long finPrevistoHasta;
    Long finRealDesde;
    Long finRealHasta;    
    
    Double horasPrevistaDesde;
    Double horasPrevistaHasta;
    Double horasRealesDesde;
    Double horasRealesHasta;
    
    Double porcentajeDesde;
    Double porcentajeHasta;
    Usuario usuario;
    //
    Long imputacionDesde;
    Long imputacionHasta;
    Double horasImputadasDesde;
    Double horasImputadasHasta;
    int tipoHoras;
    //
    TipoDocumento tipoDocumento;
    String nombreContiene;
    String ruta;
}
