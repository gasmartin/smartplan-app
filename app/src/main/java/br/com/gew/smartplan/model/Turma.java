package br.com.gew.smartplan.model;

import java.io.Serializable;

public class Turma implements Serializable {

    private Long id;
    private Integer cor;
    private Integer sala;
    private String nome;
    private String descricao;

    private static Turma instance;

    public static Turma getInstance() {
        if (instance == null) {
            instance = new Turma();
        }
        return instance;
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
