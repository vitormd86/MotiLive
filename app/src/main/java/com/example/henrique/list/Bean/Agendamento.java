package com.example.henrique.list.Bean;

/**
 * Created by htamashiro on 2/21/15.
 */
public class Agendamento {

    private int agenda_diaria;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getAgenda_diaria() {
        return agenda_diaria;
    }

    public void setAgenda_diaria(int agenda_diaria) {
        this.agenda_diaria = agenda_diaria;
    }

    public int getId_servico() {
        return id_servico;
    }

    public void setId_servico(int id_servico) {
        this.id_servico = id_servico;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id_servico;
    private String hora_entrada;
    private String hora_saida;
    private int id;
    private int id_cliente;
}
