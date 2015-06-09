package com.example.henrique.list.Mapeamento_de_Classes;

import java.sql.Date;


public class Plano_Profissional {

    private int id_plano;
    private int id;
    private int id_profissional;
    private Date data_ativacao_plano;
    private int qtde_creditos_restantes;
    private Date data_inicial;
    private Date data_final;
    private boolean status;

    public Date getData_ativacao_plano() {
        return data_ativacao_plano;
    }

    public void setData_ativacao_plano(Date data_ativacao_plano) {
        this.data_ativacao_plano = data_ativacao_plano;
    }



    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public Date getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }



    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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


    public int getQtde_creditos_restantes() {
        return qtde_creditos_restantes;
    }

    public void setQtde_creditos_restantes(int qtde_creditos_restantes) {
        this.qtde_creditos_restantes = qtde_creditos_restantes;
    }


}
