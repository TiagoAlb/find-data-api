/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.controller;

import br.com.finddata.DAO.BancoDAO;
import br.com.finddata.files.FileBancosAction;
import br.com.finddata.model.Banco;
import br.com.finddata.util.Util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@RequestMapping(path = "/api/financas/instituicoes_financeiras")
public class Bancos extends Util {
    @Autowired
    BancoDAO bancoDAO;

    FileBancosAction fileBancosAction;

    @RequestMapping(path = "/bancos", method = RequestMethod.GET)
    public Iterable<Banco> GET_BANCOS(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int page_results) {
        PageRequest pageRequest = PageRequest.of(page, page_results, Sort.unsorted());
        return bancoDAO.findAll(pageRequest);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/bancos")
    public String POST_BANCO(Banco banco) {
        bancoDAO.save(banco);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/multiple/bancos")
    public String POST_BANCO(List<Banco> bancos) {
        bancoDAO.saveAll(bancos);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/layouts/bancos")
    public String POST_UPLOAD_FILE(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "upload\\instituicoes_financeiras";
        String fileName = file.getOriginalFilename();

        fileBancosAction = new FileBancosAction(fileLocation, fileName);
        fileBancosAction.UPLOAD_FILE(in);
        POST_BANCO(fileBancosAction.READ_FILE());
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/layouts/multiple/bancos")
    public String POST_UPLOAD_FILE(@RequestParam("file") MultipartFile[] files) throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "upload\\instituicoes_financeiras";
        for (MultipartFile file : files) {
            InputStream in = file.getInputStream();
            String fileName = file.getOriginalFilename();

            fileBancosAction = new FileBancosAction(fileLocation, fileName);
            fileBancosAction.UPLOAD_FILE(in);
            POST_BANCO(fileBancosAction.READ_FILE());
        }
        return "OK";
    }
}
