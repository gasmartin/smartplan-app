package br.com.gew.smartplan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Professor implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("email")
    private String email;

    public Professor(String nome, String email) {
        super();
        this.nome = nome;
        this.email = email;
    }

    public Professor() {
        super();
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id){ this.id = id; }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

