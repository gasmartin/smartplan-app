package br.com.gew.smartplan.model;

public class Turma {

    private Long id;
    private Integer sala;
    private String nome;

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
