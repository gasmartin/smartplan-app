package br.com.gew.smartplan.model;

import java.io.Serializable;

public class Planejamento implements Serializable {

    private Long id;
    private Integer cor;
    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataFinal;

    private static Planejamento instance;

    public static Planejamento getInstance() {
        if (instance == null) {
            instance = new Planejamento();
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
