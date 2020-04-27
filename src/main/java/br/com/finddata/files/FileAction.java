/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.files;

import br.com.finddata.model.BancoSede;
import br.com.finddata.model.EnderecoInstituicaoFinanceira;
import br.com.finddata.model.InstituicaoFinanceira;
import br.com.finddata.util.Util;
import br.com.finddata.util.layouts.BANCOS_SEDE_EXCEL_FILE_LAYOUT;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
public class FileAction extends Util {

    protected String fileLocation;
    protected String fileName;

    public FileAction(String fileLocation, String fileName) {
        this.fileLocation = fileLocation;
        this.fileName = fileName;
    }

    public String UPLOAD_FILE(InputStream in) throws IOException {
        fileLocation = fileLocation + "\\" + GET_CURRENT_DATE_STRING() + "\\";
        File dir = new File(fileLocation);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

        FileOutputStream f = new FileOutputStream(fileLocation + fileName);

        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();
        return "excel";
    }
    
    public FileAction GET_FILE_LAYOUT() throws IOException {
        FileAction action = new FileAction(fileLocation, fileName);
        
        if(fileName.toLowerCase().contains("bancos"))
            action = new FileBancosSedeAction(fileLocation, fileName);
        
        return action;
    }

    public List<InstituicaoFinanceira> READ_FILE() throws IOException {
        return new ArrayList<InstituicaoFinanceira>();
    }
    
    
    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
