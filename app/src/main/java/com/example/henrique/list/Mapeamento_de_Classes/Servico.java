package com.example.henrique.list.Mapeamento_de_Classes;

import java.sql.Time;

/**
 * Created by htamashiro on 2/21/15.
 */
public class Servico {
    private  int id_profissional;
    private int id;
    private String nome;
    private String descricao;
    private Time tempo;
    private Time tolerancia_atraso;
    private double valor;

    public Time getTempo() {
        return tempo;
    }

    public void setTempo(Time tempo) {
        this.tempo = tempo;
    }

    public Time getTolerancia_atraso() {
        return tolerancia_atraso;
    }

    public void setTolerancia_atraso(Time tolerancia_atraso) {
        this.tolerancia_atraso = tolerancia_atraso;
    }



    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getId_profissional() {
        return id_profissional;
    }

    public void setId_profissional(int id_profissional) {
        this.id_profissional = id_profissional;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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



}
