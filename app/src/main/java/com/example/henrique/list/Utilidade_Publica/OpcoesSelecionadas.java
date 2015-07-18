package com.example.henrique.list.Utilidade_Publica;

import java.io.Serializable;

// CLasse criada para passar os dados entre objetos, activity


public class OpcoesSelecionadas implements Serializable {

    private String profissionalEscolhido;
    private String servicoEscolhido;
    private String dataEscolhida;
    private String diaEscolhido;
    private String HoraEscolhida;

    public String getDiaEscolhido() {
        return diaEscolhido;
    }

    public void setDiaEscolhido(String diaEscolhido) {
        this.diaEscolhido = diaEscolhido;
    }

    public String getHoraEscolhida() {
        return HoraEscolhida;
    }

    public void setHoraEscolhida(String horaEscolhida) {
        HoraEscolhida = horaEscolhida;
    }

    public String getProfissionalEscolhido() {
        return profissionalEscolhido;
    }

    public void setProfissionalEscolhido(String profissionalEscolhido) {
        this.profissionalEscolhido = profissionalEscolhido;
    }

    public String getServicoEscolhido() {
        return servicoEscolhido;
    }

    public void setServicoEscolhido(String servicoEscolhido) {
        this.servicoEscolhido = servicoEscolhido;
    }

    public String getDataEscolhida() {
        return dataEscolhida;
    }

    public void setDataEscolhida(String dataEscolhida) {
        this.dataEscolhida = dataEscolhida;
    }

    public OpcoesSelecionadas(String profissionalEscolhido, String servicoEscolhido, String dataEscolhida) {
        this.profissionalEscolhido = profissionalEscolhido;
        this.servicoEscolhido = servicoEscolhido;
        this.dataEscolhida = dataEscolhida;

    }
}
