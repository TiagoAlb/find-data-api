/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.util.layouts;

import br.com.finddata.model.BancoSede;
import br.com.finddata.model.Cooperativa;
import br.com.finddata.model.EnderecoInstituicaoFinanceira;
import br.com.finddata.util.Util;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class COOPERATIVAS_EXCEL_FILE_LAYOUT extends Util {
    private Map<Integer, String> attributes = new HashMap<Integer, String>();
    private String endereco_object_type = "endereco";
    private String instituicao_object_type = "instituicao";
    
    public COOPERATIVAS_EXCEL_FILE_LAYOUT() {
        this.attributes.put(0, "cnpj");
        this.attributes.put(1, "nome");
        this.attributes.put(2, "endereco");
        this.attributes.put(3, "complemento");
        this.attributes.put(4, "bairro");
        this.attributes.put(5, "cep");
        this.attributes.put(6, "municipio");
        this.attributes.put(7, "uf");
        this.attributes.put(8, "ddd");
        this.attributes.put(9, "telefone");
        this.attributes.put(10, "classe");
        this.attributes.put(11, "criterio_associacao");
        this.attributes.put(12, "categ_coop_sing");
        this.attributes.put(13, "filiacao");
        this.attributes.put(14, "email");
        this.attributes.put(15, "site");
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
            case "classe":
                return this.instituicao_object_type;  
            case "ddd":
                return this.instituicao_object_type;  
            case "telefone":
                return this.instituicao_object_type;  
            case "criterio_associacao":
                return this.instituicao_object_type;  
            case "categ_coop_sing":
                return this.instituicao_object_type;  
            case "filiacao":
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
    
    public Cooperativa PUT_COOPERATIVA_VALUES(Cooperativa cooperativa, String columnName, String value) {
        switch(columnName) {
            case "cnpj":
                cooperativa.setCnpj(value);
                break;
            case "nome":
                cooperativa.setNome(value);  
                break;
            case "classe":
                cooperativa.setClasse(value); 
                break;
            case "ddd":
                cooperativa.setDdd(value); 
                break;
            case "telefone":
                cooperativa.setTelefone(value); 
                break;
            case "criterio_associacao":
                cooperativa.setCriterio_associacao(value);   
                break;
            case "categ_coop_sing":
                cooperativa.setCateg_coop_sing(value);   
                break;
            case "filiacao":
                cooperativa.setFiliacao(value);   
                break;
            case "email":
                cooperativa.setEmail(value); 
                break;
            case "site":
                cooperativa.setSite(value); 
                break;
        }
        
        return cooperativa;
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
