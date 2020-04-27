/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.files;

import br.com.finddata.model.Conglomerado;
import br.com.finddata.model.InstituicaoFinanceira;
import br.com.finddata.util.layouts.CONGLOMERADOS_EXCEL_FILE_LAYOUT;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Tiago
 */
public class FileConglomeradosAction extends FileAction {
    
    public FileConglomeradosAction(String fileLocation, String fileName) {
        super(fileLocation, fileName);
    }
    
    @Override
    public List<InstituicaoFinanceira> READ_FILE() throws IOException {
        FileInputStream file = new FileInputStream(new File(fileLocation + fileName));
        Workbook workbook = new XSSFWorkbook(file);
        CONGLOMERADOS_EXCEL_FILE_LAYOUT layout = new CONGLOMERADOS_EXCEL_FILE_LAYOUT();
        ArrayList<InstituicaoFinanceira> conglomerados = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        int i = 0;
        boolean infoStart = false;
        for (Row row : sheet) {
            Conglomerado inst = new Conglomerado();
            int cellIndex = 0;
            
            row.getCell(0).setCellType(CellType.STRING);
            String firstCell = row.getCell(0).getRichStringCellValue().getString().trim().replace(".", "").replace("-", "");
            if(IS_NUMERIC(firstCell))
                infoStart = true;
            else
                infoStart = false;
            
            for (Cell cell : row) {
                cell.setCellType(CellType.STRING);
                if(infoStart) {
                    String columnName = layout.GET_COLUMN_NAME_INDEX(cellIndex);
                    String columnObjectName = layout.GET_OBJECT_TYPE_NAME(columnName);
                    
                    if(!columnObjectName.contains("INVALID")) {
                        if(columnObjectName.equals(layout.getInstituicao_object_type()))
                            inst = layout.PUT_CONGLOMERADO_VALUES(inst, columnName, cell.getRichStringCellValue().getString());
                    }
                }
                cellIndex++;
            }
            
            if(infoStart) {
                conglomerados.add(inst);
            }
            
            i++;
        }

        return conglomerados;
    }
}
