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
public class Sociedade extends InstituicaoFinanceira {
    @Column(length = 100)
    private String segmento;

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = NULL_TO_EMPTY(segmento);
    }
}
