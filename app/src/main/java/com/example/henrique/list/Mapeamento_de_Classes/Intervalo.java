package com.example.henrique.list.Mapeamento_de_Classes;

import java.sql.Time;

/**
 * Created by htamashiro on 2/21/15.
 */
public class Intervalo {
    private Time horario_inicial;
    private Time horario_final;
    private int id;
    private int id_agenda_diaria;

    public int getId_agenda_diaria() {
        return id_agenda_diaria;
    }

    public void setId_agenda_diaria(int id_agenda_diaria) {
        this.id_agenda_diaria = id_agenda_diaria;
    }

    public Time getHorario_inicial() {
        return horario_inicial;
    }

    public void setHorario_inicial(Time horario_inicial) {
        this.horario_inicial = horario_inicial;
    }

    public Time getHorario_final() {
        return horario_final;
    }

    public void setHorario_final(Time horario_final) {
        this.horario_final = horario_final;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
