/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.util.layouts;

import br.com.finddata.model.BancoSede;
import br.com.finddata.model.EnderecoInstituicaoFinanceira;
import br.com.finddata.model.Sociedade;
import br.com.finddata.util.Util;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class SOCIEDADES_EXCEL_FILE_LAYOUT extends Util {
    private Map<Integer, String> attributes = new HashMap<Integer, String>();
    private String endereco_object_type = "endereco";
    private String instituicao_object_type = "instituicao";
    
    public SOCIEDADES_EXCEL_FILE_LAYOUT() {
        this.attributes.put(0, "cnpj");
        this.attributes.put(1, "nome");
        this.attributes.put(2, "segmento");
        this.attributes.put(3, "endereco");
        this.attributes.put(4, "complemento");
        this.attributes.put(5, "bairro");
        this.attributes.put(6, "cep");
        this.attributes.put(7, "municipio");
        this.attributes.put(8, "uf");
        this.attributes.put(9, "ddd");
        this.attributes.put(10, "telefone");
        this.attributes.put(11, "email");
        this.attributes.put(12, "site");
    }
    
    public String GET_COLUMN_NAME_INDEX(int index) {
        return NULL_TO_EMPTY(attributes.get(index));
    }
    
    public String GET_OBJECT_TYPE_NAME(String columnName) {
        switch(NULL_TO_EMPTY(columnName)) {
            case "cnpj":
                return this.instituicao_object_type;
            case "nome":
                return this.instituicao_object_type;    
            case "segmento":
                return this.instituicao_object_type;  
            case "ddd":
                return this.instituicao_object_type;  
            case "telefone":
                return this.instituicao_object_type;  
            case "email":
                return this.instituicao_object_type;  
            case "site":
                return this.instituicao_object_type; 
            case "endereco":
                return this.endereco_object_type;  
            case "complemento":
                return this.endereco_object_type;  
            case "bairro":
                return this.endereco_object_type;  
            case "cep":
                return this.endereco_object_type;  
            case "municipio":
                return this.endereco_object_type;  
            case "uf":
                return this.endereco_object_type;   
            default:
                return "INVALID COLUMN NAME";    
        }
    }
    
    public Sociedade PUT_SOCIEDADE_VALUES(Sociedade sociedade, String columnName, String value) {
        switch(columnName) {
            case "cnpj":
                sociedade.setCnpj(value);
                break;
            case "nome":
                sociedade.setNome(value);  
                break;
            case "segmento":
                sociedade.setSegmento(value); 
                break;
            case "ddd":
                sociedade.setDdd(value); 
                break;
            case "telefone":
                sociedade.setTelefone(value); 
                break;
            case "email":
                sociedade.setEmail(value); 
                break;
            case "site":
                sociedade.setSite(value); 
                break;
        }
        
        return sociedade;
    }
    
    public EnderecoInstituicaoFinanceira PUT_ENDERECO_VALUES(EnderecoInstituicaoFinanceira endereco, String columnName, String value) {
        switch(columnName) {
            case "endereco":
                endereco.setEndereco(value);
                break;
            case "complemento":
                endereco.setComplemento(value); 
                break;
            case "bairro":
                endereco.setBairro(value);
                break;
            case "cep":
                endereco.setCep(value);
                break;
            case "municipio":
                endereco.setMunicipio(value);
                break;
            case "uf":
                endereco.setUf(value);
                break;
        }
        
        return endereco;
    }

    public Map<Integer, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Integer, String> attributes) {
        this.attributes = attributes;
    }

    public String getEndereco_object_type() {
        return endereco_object_type;
    }

    public void setEndereco_object_type(String endereco_object_type) {
        this.endereco_object_type = endereco_object_type;
    }

    public String getInstituicao_object_type() {
        return instituicao_object_type;
    }

    public void setInstituicao_object_type(String instituicao_object_type) {
        this.instituicao_object_type = instituicao_object_type;
    }    
}
