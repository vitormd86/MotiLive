package com.example.henrique.list.Mapeamento_de_Classes;

import java.sql.Date;
import java.sql.Time;


public class Agenda_Diaria {
    // FIXME: Arrumar o Date, a forma como eh feita a marcação



    private int id;
    private int id_profissional;



    private Date data;
    private char dia_util;
    private Time hora_entrada;
    private Time hora_saida;

    public int getId_profissional() {
        return id_profissional;
    }

    public void setId_profissional(int id_profissional) {
        this.id_profissional = id_profissional;
    }


    public char getDia_util() {
        return dia_util;
    }

    public void setDia_util(char dia_util) {
        this.dia_util = dia_util;
    }



    public Time getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(Time hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public Time getHora_saida() {
        return hora_saida;
    }

    public void setHora_saida(Time hora_saida) {
        this.hora_saida = hora_saida;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Date getData() {

        return this.data;
    }
    public void setData(Date data) {
            this.data = data;
    }


}