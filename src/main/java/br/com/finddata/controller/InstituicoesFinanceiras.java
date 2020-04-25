/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.finddata.DAO.BancoSedeDAO;
import br.com.finddata.DAO.EnderecoInstituicaoFinanceiraDAO;
import br.com.finddata.files.FileAction;
import br.com.finddata.files.FileBancosSedeAction;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tiago
 */
@RestController
@RequestMapping(path = "/api/financas")
public class InstituicoesFinanceiras {
    @Autowired
    BancoSedeDAO bancoSedeDAO;
    @Autowired
    BancosSede bancosSede;
    @Autowired
    EnderecoInstituicaoFinanceiraDAO enderecoDAO;
    
    FileAction fileAction;
    FileBancosSedeAction fileBancosSedeAction;

    @RequestMapping(method = RequestMethod.POST, value = "/instituicoes_financeiras/upload/layouts")
    public String POST_UPLOAD_FILE(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "upload\\instituicoes_financeiras";
        String fileName = file.getOriginalFilename();

        fileAction = new FileAction(fileLocation, fileName).GET_FILE_LAYOUT();
        fileAction.UPLOAD_FILE(in);
        POST_INSTITUICAO_FINANCEIRA();
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/instituicoes_financeiras/upload/layouts/multiple")
    public String POST_UPLOAD_FILE(@RequestParam("file") MultipartFile[] files) throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "upload\\instituicoes_financeiras";
        for (MultipartFile file : files) {
            InputStream in = file.getInputStream();
            String fileName = file.getOriginalFilename();

            fileAction = new FileAction(fileLocation, fileName).GET_FILE_LAYOUT();
            fileAction.UPLOAD_FILE(in);
            POST_INSTITUICAO_FINANCEIRA();
        }
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/instituicoes_financeiras")
    public String POST_INSTITUICAO_FINANCEIRA() throws IOException {
        if (fileAction.getFileName().toLowerCase().contains("bancos")) {
            bancosSede.POST_BANCO_SEDE(fileAction.READ_FILE());
        }
        return "OK";
    }
}
