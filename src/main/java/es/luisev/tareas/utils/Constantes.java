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

    public static final int SHEET_PETICION = 0;
    public static final int SHEET_IMPUTACION = 1;
    // Columnas de la hoja de Petición
    public static final int COL_CATEGORIA_COD = 0;
    public static final int COL_CATEGORIA_DES = 1;
    public static final int COL_SUBCATEGORIA_COD = 2;
    public static final int COL_SUBCATEGORIA_DES = 3;
    public static final int COL_PETICION_COD = 4;
    public static final int COL_PETICION_ASUNTO = 5;
    public static final int COL_HORA_PREVISTA = 6;
    public static final int COL_HORA_REAL = 7;
    public static final int COL_PORCENTAJE = 8;
    public static final int COL_ESTADO = 9;
    public static final int COL_DESCRIPCION = 10;
    public static final int COL_FECHA_PREV_INICIO = 11;
    public static final int COL_FECHA_PREV_FIN = 12;
    public static final int COL_FECHA_REAL_INICIO = 13;
    public static final int COL_FECHA_REAL_FIN = 14;
    public static final int COL_FECHA_ALTA = 15;
    public static final int COL_INCIDENCIA = 16;
    // Columnas de la hoja de Imputación
    public static final int COL_IMPUTA_CATEGORIA_COD = 0;
    public static final int COL_IMPUTA_SUBCATEGORIA_COD = 1;
    public static final int COL_IMPUTA_PETICION_COD = 2;
    public static final int COL_IMPUTA_FECHA = 3;
    public static final int COL_IMPUTA_HORA = 4;
    public static final int COL_IMPUTA_EXTRA = 5;
    public static final int COL_IMPUTA_OBSERVACIONES = 6;
    public static final int COL_IMPUTA_FECHA_ALTA = 7;
    public static final int COL_IMPUTA_INCIDENCIA = 8;      
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

    
    private static final String WHERE_IMPUTACION = 
             "(:imputacionDesde is null or (i.fecha is not null and i.fecha >= :imputacionDesde)) and "
            + "(:imputacionHasta is null or (i.fecha is not null and i.fecha <= :imputacionHasta)) and "
            + "(:imputacionContiene is null or (i.descripcion is not null and i.descripcion like :imputacionContiene)) and "
            + "(:horasImputadasDesde is null or (i.horasReal is not null and i.horasReal >= :horasImputadasDesde)) and "
            + "(:horasImputadasHasta is null or (i.horasReal is not null and i.horasReal <= :horasImputadasHasta)) and "            
            + "(:extra is null or (i.extra is not null and i.extra = :extra)) ";
      
public static final String QUERY_PETICION
            = "SELECT p FROM Peticion p LEFT JOIN Imputacion i ON p.id = i.peticion.id" 
            + " WHERE "
            + "(:idCategoria is null or p.categoria.id = :idCategoria) and "
            + "(:idSubCategoria is null or p.subCategoria.id = :idSubCategoria) and "
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
            +  "(:tipoListado <= 0) or "
            +  "(:tipoListado = 1 and (p.horasReal is null or p.horasReal < p.horasPrevista)) or" 
            +  "(:tipoListado = 2 and ((p.horasPrevista is null and p.horasReal>0) or p.horasReal > p.horasPrevista)) or " 
            +  "(:tipoListado = 3 and p.fecPrevistaInicio < p.fecRealInicio) or " 
            +  "(:tipoListado = 4 and p.fecPrevistaFin < p.fecRealFin)"             
            + ")";        
   
    public static final String QUERY_IMPUTACION = 
             "SELECT i FROM Imputacion i WHERE " + WHERE_IMPUTACION +  " and i.peticion IN :peticiones";
 
}