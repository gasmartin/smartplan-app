package br.com.gew.smartplan.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Planejamento implements Serializable {

    @SerializedName("id") private Long id;
    @SerializedName("nome") private String nome;
    @SerializedName("descricao") private String descricao;
    @SerializedName("dataInicio") private String dataInicio;
    @SerializedName("dataFinal") private String dataFinal;

    public Planejamento(String nome, String descricao, String dataInicio, String dataFinal) {
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
