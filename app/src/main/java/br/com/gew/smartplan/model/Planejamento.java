package br.com.gew.smartplan.model;

import java.io.Serializable;
import java.util.Date;

public class Planejamento implements Serializable {

    private Long id;
    private Integer cor;
    private String nome;
    private String descricao;
    private Date dataInicio;
    private Date dataFinal;

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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
}
