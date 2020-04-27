/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.util.layouts;

import br.com.finddata.model.Banco;
import br.com.finddata.model.BancoSede;
import br.com.finddata.model.Conglomerado;
import br.com.finddata.model.EnderecoInstituicaoFinanceira;
import br.com.finddata.util.Util;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class CONGLOMERADOS_EXCEL_FILE_LAYOUT extends Util {
    private Map<Integer, String> attributes = new HashMap<Integer, String>();
    private String instituicao_object_type = "instituicao";
    
    public CONGLOMERADOS_EXCEL_FILE_LAYOUT() {
        this.attributes.put(0, "nome");
        this.attributes.put(1, "classe");
        this.attributes.put(2, "cnpj");
        this.attributes.put(3, "nome_participante");
        this.attributes.put(4, "tipo_participacao");
        this.attributes.put(5, "inicio_operacao");
        this.attributes.put(6, "uf");
    }
    
    public String GET_COLUMN_NAME_INDEX(int index) {
        return NULL_TO_EMPTY(attributes.get(index));
    }
    
    public String GET_OBJECT_TYPE_NAME(String columnName) {
        switch(NULL_TO_EMPTY(columnName)) {
            case "nome":
                return this.instituicao_object_type;
            case "classe":
                return this.instituicao_object_type;    
            case "cnpj":
                return this.instituicao_object_type;  
            case "nome_participante":
                return this.instituicao_object_type;  
            case "tipo_participacao":
                return this.instituicao_object_type;  
            case "inicio_operacao":
                return this.instituicao_object_type;  
            case "uf":
            default:
                return "INVALID COLUMN NAME";    
        }
    }
    
    public Conglomerado PUT_CONGLOMERADO_VALUES(Conglomerado conglomerado, String columnName, String value) {
        switch(columnName) {
            case "nome":
                conglomerado.setNome(value);
                break;
            case "classe":
                conglomerado.setClasse(value);  
                break;
            case "cnpj":
                conglomerado.setCnpj(value); 
                break;
            case "nome_participante":
                conglomerado.setNome_participante(value); 
                break;
            case "tipo_participacao":
                conglomerado.setTipo_participacao(value); 
                break;
            case "inicio_operacao":
                conglomerado.setInicio_operacao(new Date(NULL_TO_EMPTY(value)));   
                break;
            case "uf":
                conglomerado.setUf(value);   
                break;
        }
        
        return conglomerado;
    }
    
    public Map<Integer, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Integer, String> attributes) {
        this.attributes = attributes;
    }

    public String getInstituicao_object_type() {
        return instituicao_object_type;
    }

    public void setInstituicao_object_type(String instituicao_object_type) {
        this.instituicao_object_type = instituicao_object_type;
    }    
}
