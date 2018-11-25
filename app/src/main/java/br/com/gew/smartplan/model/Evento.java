package br.com.gew.smartplan.model;


public class Evento {

    private Long id;
    private String nome;
    private String descricao;
    private Character tipo;
    private String dataEvento;

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
