package br.com.gew.smartplan.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Turma implements Serializable {

    @SerializedName("id") private Long id;
    @SerializedName("cor") private Integer cor;
    @SerializedName("sala") private Integer sala;
    @SerializedName("nome") private String nome;
    @SerializedName("descricao") private String descricao;

    public Turma(Integer cor, Integer sala, String nome, String descricao) {
        this.cor = cor;
        this.sala = sala;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCor() {
        return cor;
    }

    public void setCor(Integer cor) {
        this.cor = cor;
    }

    public Integer getSala() {
        return sala;
    }

    public void setSala(Integer sala) {
        this.sala = sala;
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
}
