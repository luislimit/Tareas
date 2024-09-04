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
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class ExportarService {
    
    public static String exportPeticiones(
            String fileName,
            List<Peticion> peticiones,
            List<Imputacion> imputaciones
    ) throws TareasApplicationException {

        if (peticiones == null) {
            throw new TareasApplicationException("No hay peticiones a exportar");
        }

        String plantillaPeticiones = AppHelper.getConfigurationValue("plantilla.peticiones");
        String dirExport = AppHelper.getConfigurationValue("directory.export.peticiones");
        String fullFileName = dirExport + fileName + ".xlsx";
        File file = new File(plantillaPeticiones);
        if (!file.exists()){
           throw new TareasApplicationException("No existe el fichero de plantilla: " + plantillaPeticiones); 
        }
        
        try (FileInputStream fis = new FileInputStream(file);
                // Abrir plantilla (Excel)
                Workbook workbook = new XSSFWorkbook(fis)) {

            // Crear una nueva hoja en el libro
            Sheet sheetPeticion = workbook.getSheetAt(Constantes.SHEET_PETICION);
            int numRow = 1;
            XSSFCellStyle styleOk = createStyle(workbook, Color.GREEN, Color.BLACK);
            XSSFCellStyle styleError = createStyle(workbook, Color.RED, Color.WHITE);

            for (Peticion p : peticiones) {
                // Crear una nueva fila en la hoja
                Row row = sheetPeticion.createRow(numRow);
                Double horasPrevista = p.getHorasPrevista();
                Double horasReal = p.getHorasReal();
                String incidencia = null;

                setCellValue(row, Constantes.COL_CATEGORIA_COD, p.getCategoria().getCodigo());
                setCellValue(row, Constantes.COL_CATEGORIA_DES, p.getCategoria().getNombre());
                setCellValue(row, Constantes.COL_SUBCATEGORIA_COD, p.getSubCategoria().getCodigo());
                setCellValue(row, Constantes.COL_SUBCATEGORIA_DES, p.getSubCategoria().getNombre());
                setCellValue(row, Constantes.COL_PETICION_COD, p.getCodigo());
                setCellValue(row, Constantes.COL_PETICION_ASUNTO, p.getAsunto());
                setCellValue(row, Constantes.COL_HORA_PREVISTA, horasPrevista);
                setCellValue(row, Constantes.COL_HORA_REAL, horasReal);
                setCellValue(row, Constantes.COL_PORCENTAJE, p.getPorcentaje());
                setCellValue(row, Constantes.COL_ESTADO, p.getEstado().getNombre());
                setCellValue(row, Constantes.COL_DESCRIPCION, p.getDescripcion());
                setCellDate(row, Constantes.COL_FECHA_PREV_INICIO, p.getFecPrevistaInicio());
                setCellDate(row, Constantes.COL_FECHA_PREV_FIN, p.getFecPrevistaFin());
                setCellDate(row, Constantes.COL_FECHA_REAL_INICIO, p.getFecRealInicio());
                setCellDate(row, Constantes.COL_FECHA_REAL_FIN, p.getFecRealFin());
                setCellDate(row, Constantes.COL_FECHA_ALTA, p.getFecAlta());
                // Verificar criterio sobre horas
                horasReal = horasReal == null ? 0 : horasReal;
                horasPrevista = horasPrevista == null ? 0 : horasPrevista;
                //
                if (horasPrevista > horasReal) {
                    row.getCell(Constantes.COL_HORA_PREVISTA).setCellStyle(styleOk);
                    row.getCell(Constantes.COL_HORA_REAL).setCellStyle(styleOk);
                } else if (horasPrevista < horasReal) {
                    incidencia = "Horas exceden la estimación";
                    row.getCell(Constantes.COL_HORA_PREVISTA).setCellStyle(styleError);
                    row.getCell(Constantes.COL_HORA_REAL).setCellStyle(styleError);
                }
                if (incidencia != null) {
                    setCellValue(row, Constantes.COL_INCIDENCIA, incidencia);
                }
                numRow++;
            }

            Sheet sheetImputacion = workbook.getSheetAt(Constantes.SHEET_IMPUTACION);
            numRow = 1;
            for (Imputacion i : imputaciones) {
                // Crear una nueva fila en la hoja
                Row row = sheetImputacion.createRow(numRow);
                Peticion p = i.getPeticion();
                setCellValue(row, Constantes.COL_IMPUTA_CATEGORIA_COD, p.getCategoria().getCodigo());
                setCellValue(row, Constantes.COL_IMPUTA_SUBCATEGORIA_COD, p.getSubCategoria().getCodigo());
                setCellValue(row, Constantes.COL_IMPUTA_PETICION_COD, p.getCodigo());
                setCellDate(row, Constantes.COL_IMPUTA_FECHA, i.getFecha());
                setCellValue(row, Constantes.COL_IMPUTA_HORA, i.getHorasReal());
                setCellValue(row, Constantes.COL_IMPUTA_EXTRA, i.getExtra());
                setCellValue(row, Constantes.COL_IMPUTA_OBSERVACIONES, i.getDescripcion());
                setCellDate(row, Constantes.COL_IMPUTA_FECHA_ALTA, i.getFecAlta());
                numRow++;
            }

            // Escribir un dato en la celda
            // Especificar la ruta del archivo donde se guardará el Excel
            FileOutputStream fos = new FileOutputStream(fullFileName);
            // Guardar el archivo Excel
            workbook.write(fos);
            // Cerrar el libro de trabajo
            workbook.close();
            fos.close();
            fis.close();
            
            return fullFileName;
        } catch (IOException e) {
            throw new TareasApplicationException(e.getMessage());
        }
    }

    private static void setCellValue(Row row, int col, String valor) {
        Cell cell = row.createCell(col);
        if (valor != null) {
            cell.setCellValue(valor);
        }
    }

    private static void setCellValue(Row row, int col, Double valor) {
        Cell cell = row.createCell(col);
        if (valor != null) {
            cell.setCellValue(valor);
        }
    }

    private static void setCellDate(Row row, int col, Long valor) {
        Cell cell = row.createCell(col);
        if (valor != null) {
            cell.setCellValue(new Date(valor));
        }
    }

    
    private static XSSFCellStyle createStyle(Workbook workbook, Color colorFondo, Color colorLetra){
        // Crear un estilo de celda con un color de fondo y de letra
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();

        // Color de fondo
        XSSFColor backgroundColor = new XSSFColor(colorFondo, null);
        style.setFillForegroundColor(backgroundColor);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Configurar la fuente
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Arial");

        // Color de la letra
        XSSFColor fontColor = new XSSFColor(colorLetra, null); 
        font.setColor(fontColor);

        // Aplicar la fuente al estilo
        style.setFont(font);
        
        return style;
    }
}
