/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.util.layouts;

import br.com.finddata.model.Banco;
import br.com.finddata.model.BancoSede;
import br.com.finddata.model.EnderecoInstituicaoFinanceira;
import br.com.finddata.util.Util;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class BANCOS_EXCEL_FILE_LAYOUT extends Util {
    private Map<Integer, String> attributes = new HashMap<Integer, String>();
    private String instituicao_object_type = "instituicao";
    
    public BANCOS_EXCEL_FILE_LAYOUT() {
        this.attributes.put(0, "ispb");
        this.attributes.put(1, "nome_reduzido");
        this.attributes.put(2, "codigo");
        this.attributes.put(3, "participa_compe");
        this.attributes.put(4, "acesso_principal");
        this.attributes.put(5, "nome");
        this.attributes.put(6, "inicio_operacao");
    }
    
    public String GET_COLUMN_NAME_INDEX(int index) {
        return NULL_TO_EMPTY(attributes.get(index));
    }
    
    public String GET_OBJECT_TYPE_NAME(String columnName) {
        switch(NULL_TO_EMPTY(columnName)) {
            case "ispb":
                return this.instituicao_object_type;
            case "nome_reduzido":
                return this.instituicao_object_type;    
            case "codigo":
                return this.instituicao_object_type;  
            case "participa_compe":
                return this.instituicao_object_type;  
            case "acesso_principal":
                return this.instituicao_object_type;  
            case "nome":
                return this.instituicao_object_type;  
            case "inicio_operacao":
            default:
                return "INVALID COLUMN NAME";    
        }
    }
    
    public Banco PUT_BANCO_VALUES(Banco banco, String columnName, String value) {
        switch(columnName) {
            case "ispb":
                banco.setIspb(Long.getLong(NULL_TO_ZERO(value)));
                break;
            case "nome_reduzido":
                banco.setNome_reduzido(value);  
                break;
            case "codigo":
                banco.setCodigo(Long.getLong(NULL_TO_ZERO(value))); 
                break;
            case "participa_compe":
                banco.setParticipa_compe(STR_TO_BOOLEAN(value)); 
                break;
            case "acesso_principal":
                banco.setAcesso_principal(value); 
                break;
            case "nome":
                banco.setNome(value);   
                break;
            case "inicio_operacao":
                banco.setInicio_operacao(new Date(NULL_TO_EMPTY(value)));   
                break;
        }
        
        return banco;
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
