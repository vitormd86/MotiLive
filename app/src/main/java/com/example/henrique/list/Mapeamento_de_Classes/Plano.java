package com.example.henrique.list.Mapeamento_de_Classes;


public class Plano {
    private int id;
    private boolean tipo;
    private int qtde_creditos;
    private int qtde_dias;
    private double valor;
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public int getQtde_creditos() {
        return qtde_creditos;
    }

    public void setQtde_creditos(int qtde_creditos) {
        this.qtde_creditos = qtde_creditos;
    }

    public int getQtde_dias() {
        return qtde_dias;
    }

    public void setQtde_dias(int qtde_dias) {
        this.qtde_dias = qtde_dias;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


}
