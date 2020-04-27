/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.controller;

import br.com.finddata.DAO.EnderecoInstituicaoFinanceiraDAO;
import br.com.finddata.DAO.SociedadeDAO;
import br.com.finddata.files.FileSociedadesAction;
import br.com.finddata.model.Sociedade;
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
public class Sociedades extends Util {
    @Autowired
    SociedadeDAO sociedadeDAO;
    @Autowired
    EnderecoInstituicaoFinanceiraDAO enderecoDAO;

    FileSociedadesAction fileSociedadesAction;

    @RequestMapping(path = "/cooperativas", method = RequestMethod.GET)
    public Iterable<Sociedade> GET_SOCIEDADES(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int page_results) {
        PageRequest pageRequest = PageRequest.of(page, page_results, Sort.unsorted());
        return sociedadeDAO.findAll(pageRequest);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sociedades")
    public String POST_SOCIEDADE(Sociedade sociedade) {
        sociedade.setEndereco(enderecoDAO.save(sociedade.getEndereco()));
        sociedadeDAO.save(sociedade);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/multiple/sociedades")
    public String POST_SOCIEDADE(List<Sociedade> sociedades) {
        sociedades.stream().map((Sociedade sociedade) -> {
            sociedade.setEndereco(enderecoDAO.save(sociedade.getEndereco()));
            return sociedade;
        }).forEachOrdered((sociedade) -> {
            sociedadeDAO.save(sociedade);
        });
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/layouts/sociedades")
    public String POST_UPLOAD_FILE(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "upload\\instituicoes_financeiras";
        String fileName = file.getOriginalFilename();

        fileSociedadesAction = new FileSociedadesAction(fileLocation, fileName);
        fileSociedadesAction.UPLOAD_FILE(in);
        POST_SOCIEDADE(fileSociedadesAction.READ_FILE());
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/layouts/multiple/sociedades")
    public String POST_UPLOAD_FILE(@RequestParam("file") MultipartFile[] files) throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "upload\\instituicoes_financeiras";
        for (MultipartFile file : files) {
            InputStream in = file.getInputStream();
            String fileName = file.getOriginalFilename();

            fileSociedadesAction = new FileSociedadesAction(fileLocation, fileName);
            fileSociedadesAction.UPLOAD_FILE(in);
            POST_SOCIEDADE(fileSociedadesAction.READ_FILE());
        }
        return "OK";
    }
}
