/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Tiago
 */
@Entity
public class Cooperativa extends InstituicaoFinanceira {
    @Column(length = 50)
    private String classe;
    
    @Column(length = 100)
    private String criterio_associacao;
    
    @Column(length = 50)
    private String categ_coop_sing;
    
    @Column(length = 100)
    private String filiacao;

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = NULL_TO_EMPTY(classe);
    }

    public String getCriterio_associacao() {
        return criterio_associacao;
    }

    public void setCriterio_associacao(String criterio_associacao) {
        this.criterio_associacao = NULL_TO_EMPTY(criterio_associacao);
    }

    public String getCateg_coop_sing() {
        return categ_coop_sing;
    }

    public void setCateg_coop_sing(String categ_coop_sing) {
        this.categ_coop_sing = NULL_TO_EMPTY(categ_coop_sing);
    }

    public String getFiliacao() {
        return filiacao;
    }

    public void setFiliacao(String filiacao) {
        this.filiacao = NULL_TO_EMPTY(filiacao);
    }
}
