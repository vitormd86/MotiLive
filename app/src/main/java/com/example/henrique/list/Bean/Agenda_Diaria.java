package com.example.henrique.list.Bean;

import java.util.Date;

/**
 * Created by htamashiro on 2/21/15.
 */
public class Agenda_Diaria {

    private int id;

    public int getId_profissiona() {
        return id_profissiona;
    }

    public void setId_profissiona(int id_profissiona) {
        this.id_profissiona = id_profissiona;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDia_util() {
        return dia_util;
    }

    public void setDia_util(String dia_util) {
        this.dia_util = dia_util;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_saida() {
        return hora_saida;
    }

    public void setHora_saida(String hora_saida) {
        this.hora_saida = hora_saida;
    }

    private int id_profissiona;
    private Date data;
    private String dia_util;
    private String hora_entrada;
    private String hora_saida;
}
