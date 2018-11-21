package br.com.gew.smartplan.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Turma implements Serializable {

    @SerializedName("id") private Long id;
    @SerializedName("sala") private Integer sala;
    @SerializedName("nome") private String nome;

    public Turma(Integer sala, String nome) {
        this.sala = sala;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
