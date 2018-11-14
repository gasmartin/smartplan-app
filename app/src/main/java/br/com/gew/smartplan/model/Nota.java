package br.com.gew.smartplan.model;

import com.google.gson.annotations.SerializedName;

public class Nota {

    @SerializedName("id") private Long id;
    @SerializedName("nota") private Double nota;

    public Nota(Double nota) {
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
