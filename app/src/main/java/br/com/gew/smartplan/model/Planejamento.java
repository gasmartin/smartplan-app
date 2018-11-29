package br.com.gew.smartplan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Planejamento implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("descricao")
    private String descricao;
    @JsonProperty("data_inicio")
    private String dataInicio;
    @JsonProperty("data_final")
    private String dataFinal;

    public Planejamento(){ super(); }

    public Planejamento(String nome, String descricao, String dataInicio, String dataFinal) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }
}
