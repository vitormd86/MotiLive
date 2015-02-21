package com.example.henrique.list.Bean;

/**
 * Created by htamashiro on 2/21/15.
 */
public class Especializacao {
    private int id_profissao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId_profissao() {
        return id_profissao;
    }

    public void setId_profissao(int id_profissao) {
        this.id_profissao = id_profissao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String nome;
}
