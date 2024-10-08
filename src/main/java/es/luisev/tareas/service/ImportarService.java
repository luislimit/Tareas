/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.service;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.*;
import es.luisev.tareas.utils.AppHelper;
import es.luisev.tareas.utils.Constantes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class ImportarService {

    private static CategoriaService categoriaService;
    private static SubCategoriaService subCategoriaService;
    private static PeticionService peticionService;
    private static Usuario usuario;

    public static void importFromExcel(String fileName) throws TareasApplicationException {
        try (FileInputStream fis = new FileInputStream(new File(fileName));
                Workbook workbook = new XSSFWorkbook(fis)) {

            categoriaService = AppHelper.getCategoriaService();
            subCategoriaService = AppHelper.getSubCategoriaService();
            peticionService = AppHelper.getPeticionService();
            usuario = AppHelper.getDefaultUser();

            int cantErrorPeticion = importPeticion(workbook);
            int cantErrorImputacion = importImputacion(workbook);

            if (cantErrorPeticion > 0 || cantErrorImputacion > 0) {
                // Guardar los cambios en el archivo Excel, si hubo errores
                FileOutputStream fos = new FileOutputStream(fileName);
                workbook.write(fos);
                workbook.close();
                fos.close();
                fis.close();
                throw new TareasApplicationException("Proceso finalizado con errores");
            }
            workbook.close();
            fis.close();
        } catch (IOException e) {
            TareasApplicationException ex = new TareasApplicationException(e.getMessage());
            throw ex;
        }
    }

    private static int importPeticion(Workbook workbook) throws IOException {
        // Obtener la primera hoja del Excel
        Sheet sheet = workbook.getSheetAt(Constantes.SHEET_PETICION);
        int cantError = 0;

        // Iterar sobre las filas
        for (Row row : sheet) {
            // ignoramos la cabecera del fichero
            if (row.getRowNum() > 0) {
                try {
                    Categoria categoria = new Categoria();
                    SubCategoria subCategoria = new SubCategoria();
                    Peticion peticion = new Peticion();

                    // Iterar sobre las celdas de la fila
                    for (Cell cell : row) {
                        // Obtener el tipo de celda y leer el valor
                        switch (cell.getColumnIndex()) {
                            case Constantes.COL_CATEGORIA_COD ->
                                categoria.setCodigo(cell.getStringCellValue());
                            case Constantes.COL_CATEGORIA_DES ->
                                categoria.setNombre(cell.getStringCellValue());
                            case Constantes.COL_SUBCATEGORIA_COD ->
                                subCategoria.setCodigo(cell.getStringCellValue());
                            case Constantes.COL_SUBCATEGORIA_DES ->
                                subCategoria.setNombre(cell.getStringCellValue());
                            case Constantes.COL_PETICION_COD ->
                                peticion.setCodigo(cell.getStringCellValue());
                            case Constantes.COL_PETICION_ASUNTO ->
                                peticion.setAsunto(cell.getStringCellValue());
                            case Constantes.COL_HORA_PREVISTA ->
                                peticion.setHorasPrevista(cell.getNumericCellValue());
                            case Constantes.COL_HORA_REAL ->
                                peticion.setHorasReal(cell.getNumericCellValue());
                            case Constantes.COL_PORCENTAJE ->
                                peticion.setPorcentaje(cell.getNumericCellValue());
                            case Constantes.COL_ESTADO ->
                                peticion.setEstado(getEstado(cell.getStringCellValue()));
                            case Constantes.COL_DESCRIPCION ->
                                peticion.setDescripcion(cell.getStringCellValue());
                            case Constantes.COL_FECHA_PREV_INICIO ->
                                peticion.setFecPrevistaInicio(getDateValue(cell));
                            case Constantes.COL_FECHA_PREV_FIN ->
                                peticion.setFecPrevistaFin(getDateValue(cell));
                            case Constantes.COL_FECHA_REAL_INICIO ->
                                peticion.setFecRealInicio(getDateValue(cell));
                            case Constantes.COL_FECHA_REAL_FIN ->
                                peticion.setFecRealFin(getDateValue(cell));
                        }
                    }
                    //Insertamos Categoría si no existe
                    Categoria categoriaDB = categoriaService.findByCodigo(categoria);
                    categoria = (categoriaDB == null) ? categoriaService.insert(categoria) : categoriaDB;
                    //Insertamos SubCategoría si no existe
                    subCategoria.setCategoria(categoria);
                    SubCategoria subCategoriaDB = subCategoriaService.findByCodigo(subCategoria);
                    subCategoria = (subCategoriaDB == null) ? subCategoriaService.insert(subCategoria) : subCategoriaDB;
                    // Insertamos la peticion si no existe su código
                    Peticion peticionDB = peticionService.findByCodigo(peticion.getCodigo());
                    if (peticionDB == null) {
                        peticion.setCategoria(categoria);
                        peticion.setSubCategoria(subCategoria);
                        peticion.setUsuario(usuario);
                        peticionService.insert(peticion);
                    }
                } catch (TareasApplicationException e) {
                    Cell cellIncidencia = row.getCell(Constantes.COL_INCIDENCIA);
                    if (cellIncidencia == null) {
                        cellIncidencia = row.createCell(Constantes.COL_INCIDENCIA);
                    }
                    cellIncidencia.setCellValue(e.toString());
                    cantError++;
                }
            }
        }
        return cantError;
    }

    private static int importImputacion(Workbook workbook) throws IOException {
        ImputacionService imputacionService = AppHelper.getImputacionService();
        // Obtener la primera hoja del Excel
        Sheet sheet = workbook.getSheetAt(Constantes.SHEET_IMPUTACION);
        int cantError = 0;

        // Iterar sobre las filas
        for (Row row : sheet) {
            // ignoramos la cabecera del fichero
            if (row.getRowNum() > 0) {
                try {
                    Categoria categoria = new Categoria();
                    SubCategoria subCategoria = new SubCategoria();
                    Peticion peticion = new Peticion();
                    Imputacion imputacion = new Imputacion();

                    // Iterar sobre las celdas de la fila
                    for (Cell cell : row) {
                        // Obtener el tipo de celda y leer el valor
                        switch (cell.getColumnIndex()) {
                            case Constantes.COL_IMPUTA_CATEGORIA_COD ->
                                categoria.setCodigo(cell.getStringCellValue());
                            case Constantes.COL_IMPUTA_SUBCATEGORIA_COD ->
                                subCategoria.setCodigo(cell.getStringCellValue());
                            case Constantes.COL_IMPUTA_PETICION_COD ->
                                peticion.setCodigo(cell.getStringCellValue());
                            case Constantes.COL_IMPUTA_FECHA ->
                                imputacion.setFecha(getDateValue(cell));
                            case Constantes.COL_IMPUTA_HORA ->
                                imputacion.setHorasReal(cell.getNumericCellValue());
                            case Constantes.COL_IMPUTA_EXTRA ->
                                imputacion.setExtra(cell.getStringCellValue());
                            case Constantes.COL_IMPUTA_OBSERVACIONES ->
                                imputacion.setDescripcion(cell.getStringCellValue());
                        }
                    }
                    //Insertamos Categoría si no existe
                    Categoria categoriaDB = categoriaService.findByCodigo(categoria);
                    subCategoria.setCategoria(categoria);
                    SubCategoria subCategoriaDB = subCategoriaService.findByCodigo(subCategoria);
                    Peticion peticionDB = peticionService.findByCodigo(peticion.getCodigo());
                    if (peticionDB == null) {
                        throw new TareasApplicationException("No existe la Petición");
                    }
                    if (categoriaDB != null && !peticionDB.getCategoria().getId().equals(categoriaDB.getId())) {
                        throw new TareasApplicationException("No coincide la categoria con la de la Petición => " + categoria.getCodigo());
                    }
                    if (subCategoriaDB != null && !peticionDB.getSubCategoria().getId().equals(subCategoriaDB.getId())) {
                        throw new TareasApplicationException("No coincide la Subcategoria con la de la Petición => " + subCategoriaDB.getCodigo());
                    }
                    imputacion.setPeticion(peticionDB);
                    imputacion.setUsuario(usuario);
                    imputacionService.insert(imputacion);

                } catch (TareasApplicationException e) {
                    Cell cellIncidencia = row.getCell(Constantes.COL_IMPUTA_INCIDENCIA);
                    if (cellIncidencia == null) {
                        cellIncidencia = row.createCell(Constantes.COL_IMPUTA_INCIDENCIA);
                    }
                    cellIncidencia.setCellValue(e.toString());
                    cantError++;
                }
            }
        }
        return cantError;
    }

    private static String getDateValue(Cell cell) {
        if (DateUtil.isCellDateFormatted(cell)) {
            System.out.print("Fecha " + cell.getDateCellValue() + "\t");
            return AppHelper.fromDateToFechaDb(cell.getDateCellValue());
        }
        return null;
    }

    private static Estado getEstado(String desEstado) {
        return Estado.builder().nombre(desEstado).id(switch (desEstado) {
            case "En Curso" ->
                2L;
            case "Retenida" ->
                3L;
            case "Anulada" ->
                4L;
            case "Finalizada" ->
                5L;
            case "Cerrada" ->
                6L;
            default ->
                1L; //Pendiente
        }).build();
    }
}
