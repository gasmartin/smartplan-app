package br.com.gew.smartplan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Usuario implements Serializable {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("professor")
    private Professor professor;

    public Usuario(){}

    public Usuario(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
