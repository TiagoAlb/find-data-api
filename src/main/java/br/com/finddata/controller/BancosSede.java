/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.controller;

import br.com.finddata.DAO.BancoSedeDAO;
import br.com.finddata.DAO.EnderecoInstituicaoFinanceiraDAO;
import br.com.finddata.files.FileBancosSedeAction;
import br.com.finddata.model.BancoSede;
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
public class BancosSede extends Util {
    @Autowired
    BancoSedeDAO bancoSedeDAO;
    @Autowired
    EnderecoInstituicaoFinanceiraDAO enderecoDAO;

    FileBancosSedeAction fileBancosSedeAction;

    @RequestMapping(path = "/bancos_sede", method = RequestMethod.GET)
    public Iterable<BancoSede> GET_BANCOS_SEDE(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int page_results) {
        PageRequest pageRequest = PageRequest.of(page, page_results, Sort.unsorted());
        return bancoSedeDAO.findAll(pageRequest);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/bancos_sede")
    public String POST_BANCO_SEDE(BancoSede bancoSede) {
        bancoSede.setEndereco(enderecoDAO.save(bancoSede.getEndereco()));
        bancoSedeDAO.save(bancoSede);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/multiple/bancos_sede")
    public String POST_BANCO_SEDE(List<BancoSede> bancosSede) {
        bancosSede.stream().map((BancoSede bancoSede) -> {
            bancoSede.setEndereco(enderecoDAO.save(bancoSede.getEndereco()));
            return bancoSede;
        }).forEachOrdered((bancoSede) -> {
            bancoSedeDAO.save(bancoSede);
        });
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/layouts/bancos_sede")
    public String POST_UPLOAD_FILE(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "upload\\instituicoes_financeiras";
        String fileName = file.getOriginalFilename();

        fileBancosSedeAction = new FileBancosSedeAction(fileLocation, fileName);
        fileBancosSedeAction.UPLOAD_FILE(in);
        POST_BANCO_SEDE(fileBancosSedeAction.READ_FILE());
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/layouts/multiple/bancos_sede")
    public String POST_UPLOAD_FILE(@RequestParam("file") MultipartFile[] files) throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "upload\\instituicoes_financeiras";
        for (MultipartFile file : files) {
            InputStream in = file.getInputStream();
            String fileName = file.getOriginalFilename();

            fileBancosSedeAction = new FileBancosSedeAction(fileLocation, fileName);
            fileBancosSedeAction.UPLOAD_FILE(in);
            POST_BANCO_SEDE(fileBancosSedeAction.READ_FILE());
        }
        return "OK";
    }
}
