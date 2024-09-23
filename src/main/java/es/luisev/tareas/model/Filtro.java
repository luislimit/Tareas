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
    int tipoListado;
    Estado estado;
    //
    String asuntoContiene;
    String inicioPrevistoDesde;
    String inicioPrevistoHasta;
    String inicioRealDesde;
    String inicioRealHasta;
    
    String finPrevistoDesde;
    String finPrevistoHasta;
    String finRealDesde;
    String finRealHasta;    
    
    Double horasPrevistaDesde;
    Double horasPrevistaHasta;
    Double horasRealesDesde;
    Double horasRealesHasta;
    
    Double porcentajeDesde;
    Double porcentajeHasta;
    Usuario usuario;
    //
    String imputacionDesde;
    String imputacionHasta;
    String imputacionContiene;
    Double horasImputadasDesde;
    Double horasImputadasHasta;
    int tipoHoras;
    //
    TipoDocumento tipoDocumento;
    String nombreContiene;
    String ruta;
}
