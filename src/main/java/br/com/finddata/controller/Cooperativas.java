/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.controller;

import br.com.finddata.DAO.CooperativaDAO;
import br.com.finddata.DAO.EnderecoInstituicaoFinanceiraDAO;
import br.com.finddata.files.FileBancosSedeAction;
import br.com.finddata.files.FileCooperativasAction;
import br.com.finddata.model.Cooperativa;
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
public class Cooperativas extends Util {
    @Autowired
    CooperativaDAO cooperativaDAO;
    @Autowired
    EnderecoInstituicaoFinanceiraDAO enderecoDAO;

    FileCooperativasAction fileCooperativasAction;

    @RequestMapping(path = "/cooperativas", method = RequestMethod.GET)
    public Iterable<Cooperativa> GET_COOPERATIVAS(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int page_results) {
        PageRequest pageRequest = PageRequest.of(page, page_results, Sort.unsorted());
        return cooperativaDAO.findAll(pageRequest);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cooperativas")
    public String POST_COOPERATIVA(Cooperativa cooperativa) {
        cooperativa.setEndereco(enderecoDAO.save(cooperativa.getEndereco()));
        cooperativaDAO.save(cooperativa);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/multiple/cooperativas")
    public String POST_COOPERATIVA(List<Cooperativa> cooperativas) {
        cooperativas.stream().map((Cooperativa cooperativa) -> {
            cooperativa.setEndereco(enderecoDAO.save(cooperativa.getEndereco()));
            return cooperativa;
        }).forEachOrdered((cooperativa) -> {
            cooperativaDAO.save(cooperativa);
        });
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/layouts/cooperativas")
    public String POST_UPLOAD_FILE(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "upload\\instituicoes_financeiras";
        String fileName = file.getOriginalFilename();

        fileCooperativasAction = new FileCooperativasAction(fileLocation, fileName);
        fileCooperativasAction.UPLOAD_FILE(in);
        POST_COOPERATIVA(fileCooperativasAction.READ_FILE());
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/layouts/multiple/cooperativas")
    public String POST_UPLOAD_FILE(@RequestParam("file") MultipartFile[] files) throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "upload\\instituicoes_financeiras";
        for (MultipartFile file : files) {
            InputStream in = file.getInputStream();
            String fileName = file.getOriginalFilename();

            fileCooperativasAction = new FileCooperativasAction(fileLocation, fileName);
            fileCooperativasAction.UPLOAD_FILE(in);
            POST_COOPERATIVA(fileCooperativasAction.READ_FILE());
        }
        return "OK";
    }
}
