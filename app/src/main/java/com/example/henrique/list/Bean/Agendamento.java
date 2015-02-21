package com.example.henrique.list.Bean;


import java.sql.Time;

public class Agendamento {

    private int id_agenda_diaria;
    private int id_servico;
    private Time hora_entrada;
    private Time hora_saida;
    private int id;
    private int id_cliente;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_agenda_diaria() {
        return id_agenda_diaria;
    }

    public void setId_agenda_diaria(int id_agenda_diaria) {
        this.id_agenda_diaria = id_agenda_diaria;
    }

    public int getId_servico() {
        return id_servico;
    }

    public void setId_servico(int id_servico) {
        this.id_servico = id_servico;
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




}
