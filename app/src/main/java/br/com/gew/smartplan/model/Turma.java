package br.com.gew.smartplan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Turma implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("sala")
    private Integer sala;
    @JsonProperty("nome")
    private String nome;

    public Turma(){ super(); }

    public Turma(Integer sala, String nome) {
        super();
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
