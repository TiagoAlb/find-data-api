/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Tiago
 */
@Entity
public class Conglomerado extends InstituicaoFinanceira {
    @Column(length = 100)
    private String classe;
    
    @Column(length = 100)
    private String nome_participante;
    
    @Column(length = 50)
    private String tipo_participacao;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date inicio_operacao;
    
    @Column(length = 2)
    private String uf;

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = NULL_TO_EMPTY(classe);
    }

    public String getNome_participante() {
        return nome_participante;
    }

    public void setNome_participante(String nome_participante) {
        this.nome_participante = nome_participante;
    }

    
    
    public String getTipo_participacao() {
        return tipo_participacao;
    }

    public void setTipo_participacao(String tipo_participacao) {
        this.tipo_participacao = NULL_TO_EMPTY(tipo_participacao);
    }

    public Date getInicio_operacao() {
        return inicio_operacao;
    }

    public void setInicio_operacao(Date inicio_operacao) {
        this.inicio_operacao = inicio_operacao;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
