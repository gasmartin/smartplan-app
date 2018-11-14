package br.com.gew.smartplan.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Evento implements Serializable {

    @SerializedName("id") private Long id;
    @SerializedName("nome") private String nome;
    @SerializedName("descricao") private String descricao;
    @SerializedName("tipo") private Character tipo;
    @SerializedName("dataEvento") private String dataEvento;

    public Evento(String nome, String descricao, Character tipo, String dataEvento) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.dataEvento = dataEvento;
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

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }
}
