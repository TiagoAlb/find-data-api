/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 *
 * @author Tiago
 */
@MappedSuperclass
public class InstituicaoFinanceira implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, length = 500)
    private String nome;
    
    @Column(length = 14)
    private String cnpj;
    
    @Column(length = 9)
    private String telefone;
    
    @Column(length = 3)
    private String ddd;
    
    @Column(length = 500)
    private String site;
    
    @Column(length = 500)
    private String email;
   
    @OneToOne
    private EnderecoInstituicaoFinanceira endereco;

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnderecoInstituicaoFinanceira getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoInstituicaoFinanceira endereco) {
        this.endereco = endereco;
    }
}
