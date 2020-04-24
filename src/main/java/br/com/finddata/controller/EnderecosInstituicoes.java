/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.controller;

import br.com.finddata.model.Banco;
import br.com.finddata.model.EnderecoInstituicaoFinanceira;
import br.com.finddata.model.InstituicaoFinanceira;
import br.com.finddata.util.Util;
import br.com.finddata.util.layouts.BANCOS_EXCEL_FILE_LAYOUT;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tiago
 */
@RestController
@RequestMapping(path = "/api")
public class EnderecosInstituicoes {
    Util util = new Util();
    
    private String fileLocation;
    private String fileName;
    
    @RequestMapping(method = RequestMethod.POST, value = "/uploadExcelFile")
    public String POST_UPLOAD_FILE(@RequestParam("file") MultipartFile[] file) throws IOException {
        InputStream in = file[0].getInputStream();
        
        for (MultipartFile file1 : file) {
            System.out.println(file1.getName());
        }
        
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        fileLocation = path.substring(0, path.length() - 1) + "upload\\instituicoes_financeiras\\" + util.GET_CURRENT_DATE_STRING() + "\\";
        fileName = file[0].getOriginalFilename();
        File dir = new File(fileLocation);
        if(!dir.isDirectory())
            dir.mkdirs();
        
        FileOutputStream f = new FileOutputStream(fileLocation + fileName);
        
        int ch = 0;
        while ((ch = in.read()) != -1) {
            System.out.println(ch);
            f.write(ch);
        }
        f.flush();
        f.close();
        
        READ_FILE();
        return "excel";
    }
    
    public String READ_FILE() throws IOException {
        if(fileName.contains("BANCOS"))
            return READ_FILE_BANCOS();
       
        return "ERRO";
    }
    
    public String READ_FILE_BANCOS() throws IOException {
        FileInputStream file = new FileInputStream(new File(fileLocation + fileName));
        Workbook workbook = new XSSFWorkbook(file);
        BANCOS_EXCEL_FILE_LAYOUT layout = new BANCOS_EXCEL_FILE_LAYOUT();
        
        Sheet sheet = workbook.getSheetAt(0);
 
        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            Banco inst = new Banco();
            EnderecoInstituicaoFinanceira endereco = new EnderecoInstituicaoFinanceira();
            data.put(i, new ArrayList<String>());
            
            int cellIndex = 0;
            for (Cell cell : row) {
                String columnName = layout.GET_COLUMN_NAME_INDEX(cellIndex);
                if(columnName.equals(layout.getEndereco_object_type()))
                    endereco = layout.PUT_ENDERECO_VALUES(endereco, columnName, cell.toString());
                else if(columnName.equals(layout.getInstituicao_object_type()))
                    inst = layout.PUT_BANCO_VALUES(inst, columnName, cell.toString());
                cellIndex++;
            }
            i++;
        }

        return "excel";
    }
    
    public String ADD_INSTITUICAO() throws IOException {
        if(fileName.contains("BANCOS"))
            return READ_FILE_BANCOS();
       
        return "ERRO";
    }
}
