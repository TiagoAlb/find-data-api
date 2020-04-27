/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.model;

import br.com.finddata.util.Util;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Tiago
 */
@Entity
public class EnderecoInstituicaoFinanceira extends Util implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(length = 500)
    private String endereco;
    
    @Column(length = 500)
    private String complemento;
    
    @Column(length = 2)
    private String uf;
    
    @Column(length = 100)
    private String municipio;
    
    @Column(length = 100)
    private String bairro;
    
    @Column(length = 8)
    private String cep;

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco.trim();
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento.trim();
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf.trim();
    }

    public String getMunicipio() {
        return municipio.trim();
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio.trim();
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro.trim();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = CLEAN_NUMERIC(cep);
    }
}
