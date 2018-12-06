package br.com.gew.smartplan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Turma implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("sala")
    private Integer sala;
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("alunos")
    private List<Aluno> alunos;

    public Turma(){ super(); }

    public Turma(Integer sala, String nome, List<Aluno> alunos) {
        super();
        this.sala = sala;
        this.nome = nome;
        this.alunos = alunos;
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

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
