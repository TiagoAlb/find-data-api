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
public class Banco extends InstituicaoFinanceira {
    @Column(nullable = true)
    private long ispb;
    
    @Column(length = 500)
    private String nome_reduzido;
    
    @Column(nullable = true)
    private long codigo;
    
    private boolean participa_compe;
    
    @Column(length = 50)
    private String acesso_principal;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date inicio_operacao;

    public long getIspb() {
        return ispb;
    }

    public void setIspb(long ispb) {
        this.ispb = ispb;
    }

    public String getNome_reduzido() {
        return nome_reduzido;
    }

    public void setNome_reduzido(String nome_reduzido) {
        this.nome_reduzido = NULL_TO_EMPTY(nome_reduzido);
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public boolean isParticipa_compe() {
        return participa_compe;
    }

    public void setParticipa_compe(boolean participa_compe) {
        this.participa_compe = participa_compe;
    }

    public String getAcesso_principal() {
        return acesso_principal;
    }

    public void setAcesso_principal(String acesso_principal) {
        this.acesso_principal = NULL_TO_EMPTY(acesso_principal);
    }

    public Date getInicio_operacao() {
        return inicio_operacao;
    }

    public void setInicio_operacao(Date inicio_operacao) {
        this.inicio_operacao = inicio_operacao;
    }
}
