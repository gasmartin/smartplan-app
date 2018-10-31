package br.com.gew.smartplan.model;

import java.io.Serializable;

public class Evento implements Serializable {

    private Long id;
    private String nome;
    private String descricao;
    private Character tipo;
    private String dataEvento;

    private static Evento instance;

    public static Evento getInstance() {
        if (instance == null) {
            instance = new Evento();
        }
        return instance;
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
