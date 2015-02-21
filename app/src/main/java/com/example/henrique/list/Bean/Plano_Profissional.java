package com.example.henrique.list.Bean;

/**
 * Created by htamashiro on 2/21/15.
 */
public class Plano_Profissional {

    private int id_plano;
    private int id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_plano() {
        return id_plano;
    }

    public void setId_plano(int id_plano) {
        this.id_plano = id_plano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_profissional() {
        return id_profissional;
    }

    public void setId_profissional(int id_profissional) {
        this.id_profissional = id_profissional;
    }

    public String getData_ativacao_plano() {
        return data_ativacao_plano;
    }

    public void setData_ativacao_plano(String data_ativacao_plano) {
        this.data_ativacao_plano = data_ativacao_plano;
    }

    public double getQtde_creditos_restantes() {
        return qtde_creditos_restantes;
    }

    public void setQtde_creditos_restantes(double qtde_creditos_restantes) {
        this.qtde_creditos_restantes = qtde_creditos_restantes;
    }

    public String getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(String data_inicial) {
        this.data_inicial = data_inicial;
    }

    public String getData_final() {
        return data_final;
    }

    public void setData_final(String data_final) {
        this.data_final = data_final;
    }

    private int id_profissional;
    private String data_ativacao_plano;
    private double qtde_creditos_restantes;
    private String data_inicial;
    private String data_final;
    private String status;
}
