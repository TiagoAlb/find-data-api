/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.controller;

import br.com.finddata.DAO.ConglomeradoDAO;
import br.com.finddata.files.FileConglomeradosAction;
import br.com.finddata.model.Conglomerado;
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
public class Conglomerados extends Util {
    @Autowired
    ConglomeradoDAO conglomeradoDAO;

    FileConglomeradosAction fileConglomeradosAction;

    @RequestMapping(path = "/conglomerados", method = RequestMethod.GET)
    public Iterable<Conglomerado> GET_CONGLOMERADOS(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int page_results) {
        PageRequest pageRequest = PageRequest.of(page, page_results, Sort.unsorted());
        return conglomeradoDAO.findAll(pageRequest);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/conglomerados")
    public String POST_CONGLOMERADO(Conglomerado conglomerado) {
        conglomeradoDAO.save(conglomerado);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/multiple/conglomerados")
    public String POST_CONGLOMERADO(List<Conglomerado> conglomerados) {
        conglomeradoDAO.saveAll(conglomerados);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/layouts/conglomerados")
    public String POST_UPLOAD_FILE(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "upload\\instituicoes_financeiras";
        String fileName = file.getOriginalFilename();

        fileConglomeradosAction = new FileConglomeradosAction(fileLocation, fileName);
        fileConglomeradosAction.UPLOAD_FILE(in);
        POST_CONGLOMERADO(fileConglomeradosAction.READ_FILE());
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/layouts/multiple/conglomerados")
    public String POST_UPLOAD_FILE(@RequestParam("file") MultipartFile[] files) throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "upload\\instituicoes_financeiras";
        for (MultipartFile file : files) {
            InputStream in = file.getInputStream();
            String fileName = file.getOriginalFilename();

            fileConglomeradosAction = new FileConglomeradosAction(fileLocation, fileName);
            fileConglomeradosAction.UPLOAD_FILE(in);
            POST_CONGLOMERADO(fileConglomeradosAction.READ_FILE());
        }
        return "OK";
    }
}
