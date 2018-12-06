package br.com.gew.smartplan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Aluno {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("email")
    private String email;
    @JsonProperty("notas")
    private List<Nota> notas;

    public Aluno(String nome, String email, List<Nota> notas) {
        this.nome = nome;
        this.email = email;
        this.notas = notas;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

}
