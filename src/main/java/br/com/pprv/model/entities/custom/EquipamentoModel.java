/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.entities.custom;

import br.com.pprv.model.entities.Tbinspecao;
import br.com.pprv.model.entities.Tbtecnica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JorgeFonseca
 */
public class EquipamentoModel implements Serializable{
    
    private String nmEquipamento;
    private Integer criticidade;
    private String descEquipamento;
    private Tbinspecao idInspecao;
    private Tbtecnica idTecnica;
    private Integer idCondicao;
    private List<SubConjuntoModel> listSubConjuntoModels;
    
    public EquipamentoModel (){
        listSubConjuntoModels = new ArrayList<>();
    }

    /**
     * @return the nmEquipamento
     */
    public String getNmEquipamento() {
        return nmEquipamento;
    }

    /**
     * @param nmEquipamento the nmEquipamento to set
     */
    public void setNmEquipamento(String nmEquipamento) {
        this.nmEquipamento = nmEquipamento;
    }

    /**
     * @return the criticidade
     */
    public Integer getCriticidade() {
        return criticidade;
    }

    /**
     * @param criticidade the criticidade to set
     */
    public void setCriticidade(Integer criticidade) {
        this.criticidade = criticidade;
    }

    /**
     * @return the descEquipamento
     */
    public String getDescEquipamento() {
        return descEquipamento;
    }

    /**
     * @param descEquipamento the descEquipamento to set
     */
    public void setDescEquipamento(String descEquipamento) {
        this.descEquipamento = descEquipamento;
    }
  
    /**
     * @return the idCondicao
     */
    public Integer getIdCondicao() {
        return idCondicao;
    }

    /**
     * @param idCondicao the idCondicao to set
     */
    public void setIdCondicao(Integer idCondicao) {
        this.idCondicao = idCondicao;
    }

    /**
     * @return the listSubConjuntoModels
     */
    public List<SubConjuntoModel> getListSubConjuntoModels() {
        return listSubConjuntoModels;
    }

    /**
     * @param listSubConjuntoModels the listSubConjuntoModels to set
     */
    public void setListSubConjuntoModels(List<SubConjuntoModel> listSubConjuntoModels) {
        this.listSubConjuntoModels = listSubConjuntoModels;
    }

    /**
     * @return the idTecnica
     */
    public Tbtecnica getIdTecnica() {
        return idTecnica;
    }

    /**
     * @param idTecnica the idTecnica to set
     */
    public void setIdTecnica(Tbtecnica idTecnica) {
        this.idTecnica = idTecnica;
    }

    /**
     * @return the idInspecao
     */
    public Tbinspecao getIdInspecao() {
        return idInspecao;
    }

    /**
     * @param idInspecao the idInspecao to set
     */
    public void setIdInspecao(Tbinspecao idInspecao) {
        this.idInspecao = idInspecao;
    }
    
    
}
