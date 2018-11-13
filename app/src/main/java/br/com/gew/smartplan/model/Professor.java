package br.com.gew.smartplan.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Professor implements Serializable {

    @SerializedName("id") private Long id;
    @SerializedName("nome") private String nome;
    @SerializedName("email") private String email;
    @SerializedName("username") private String username;
    @SerializedName("senha") private String senha;

    public Professor(String nome, String email, String username, String senha) {
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.senha = senha;
    }

    public Long getId() {
        return this.id;
    }

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

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return this.senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}

