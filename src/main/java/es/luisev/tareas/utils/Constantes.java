/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.utils;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class Constantes {

    // 
    public static final String SPRING_CONTEXT = "SPRING_CONTEXT";
    // Constantes para los nombre de servicios
    public static final String SERVICIO_CATEGORIA = "SERVICIO_CATEGORIA";
    public static final String SERVICIO_SUBCATEGORIA = "SERVICIO_SUBCATEGORIA";
    public static final String SERVICIO_ESTADO = "SERVICIO_ESTADO";
    public static final String SERVICIO_IMPUTACION = "SERVICIO_IMPUTACION";
    public static final String SERVICIO_PETICION = "SERVICIO_PETICION";
    public static final String SERVICIO_USUARIO = "SERVICIO_USUARIO";
    public static final String SERVICIO_TIPO_DOCUMENTO = "SERVICIO_TIPO_DOCUMENTO";
    public static final String SERVICIO_DOCUMENTO = "SERVICIO_DOCUMENTO";

    public static final String DEFAULT_USER = "default_user";

    public static final String QUERY_IMPUTACION = "SELECT i.peticion FROM Imputacion i WHERE "
            + "(:imputacionDesde is null or (i.fecha is not null and i.fecha >= :imputacionDesde)) and "
            + "(:imputacionHasta is null or (i.fecha is not null and i.fecha <= :imputacionHasta)) "
            + "GROUP BY i.peticion.id "
            + "HAVING "
            +  "(:horasImputadasDesde is null or SUM(i.horasReal) >= :horasImputadasDesde) and "
            +  "(:horasImputadasHasta is null or SUM(i.horasReal) <= :horasImputadasHasta) ";
    
    public static final String QUERY_PETICION
            = "SELECT p FROM Peticion p WHERE "
            + "(:idCategoria is null or p.categoria.id = :idCategoria) and "
            + "(:idSubCategoria is null or p.subCategoria.id = :idSubCategoria) and "
            + "(:idPeticion is null or p.id = :idPeticion) and "
            + "(:idEstado is null or p.estado.id = :idEstado) and "
            + //
            "(:inicioPrevistoDesde is null or (p.fecPrevistaInicio is not null and p.fecPrevistaInicio >= :inicioPrevistoDesde)) and "
            + "(:inicioPrevistoHasta is null or (p.fecPrevistaInicio is not null and p.fecPrevistaInicio <= :inicioPrevistoHasta)) and "
            + "(:inicioRealDesde is null or (p.fecRealInicio is not null and p.fecRealInicio >= :inicioRealDesde)) and "
            + "(:inicioRealHasta is null or (p.fecRealInicio is not null and p.fecRealInicio <= :inicioRealHasta)) and "
            ///
            +"(:finPrevistoDesde is null or (p.fecPrevistaFin is not null and p.fecPrevistaFin >= :finPrevistoDesde)) and "
            + "(:finPrevistoHasta is null or (p.fecPrevistaFin is not null and p.fecPrevistaFin <= :finPrevistoHasta)) and "
            + "(:finRealDesde is null or (p.fecRealFin is not null and p.fecRealFin >= :finRealDesde)) and "
            + "(:finRealHasta is null or (p.fecRealFin is not null and p.fecRealFin <= :finRealHasta)) and "
             //
            +"(:horasPrevistaDesde is null or (p.horasPrevista is not null and p.horasPrevista >= :horasPrevistaDesde)) and "
            + "(:horasPrevistaHasta is null or (p.horasPrevista is not null and p.horasPrevista <= :horasPrevistaHasta)) and "
            + "(:horasRealDesde is null or (p.horasReal is not null and p.horasReal >= :horasRealDesde)) and "
            + "(:horasRealHasta is null or (p.horasReal is not null and p.horasReal <= :horasRealHasta)) and "
            + //
            "(:porcentajeDesde is null or (p.porcentaje is not null and p.porcentaje >= :porcentajeDesde)) and "
            + "(:porcentajeHasta is null or (p.porcentaje is not null and p.porcentaje <= :porcentajeHasta)) and "
            //
            + "("
            +  "(:asuntoContiene is null) or "
            +  "(p.codigo is not null and p.codigo LIKE :asuntoContiene) or "
            +  "(p.asunto is not null and p.asunto LIKE :asuntoContiene) or "
            +  "(p.descripcion is not null and p.descripcion LIKE :asuntoContiene)"
            + ") and "
            + "(" 
            +  "(:horasImputadasDesde is null and :horasImputadasHasta is null and :imputacionDesde is null and :imputacionHasta is null) or "
            +  "p IN (" + QUERY_IMPUTACION + ")"
            + ")";

}
