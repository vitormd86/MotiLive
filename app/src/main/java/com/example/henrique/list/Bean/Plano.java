package com.example.henrique.list.Bean;

/**
 * Created by htamashiro on 2/21/15.
 */
public class Plano {
    private int id;
    private String tipo;
    private int qtde_creditos;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQtde_creditos() {
        return qtde_creditos;
    }

    public void setQtde_creditos(int qtde_creditos) {
        this.qtde_creditos = qtde_creditos;
    }

    public String getQtde_dias() {
        return qtde_dias;
    }

    public void setQtde_dias(String qtde_dias) {
        this.qtde_dias = qtde_dias;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    private String qtde_dias;
    private double valor;
    private String status;
}
