package com.example.henrique.list;

import java.io.Serializable;

/**
 * Created by Henrique on 11/02/2015.
 */
public class opcoesSelecionadas implements Serializable {

    private String profissionalEscolhido, servicoEscolhido, dataEscolhida;

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

    public opcoesSelecionadas(String profissionalEscolhido, String servicoEscolhido, String dataEscolhida) {
        this.profissionalEscolhido = profissionalEscolhido;
        this.servicoEscolhido = servicoEscolhido;
        this.dataEscolhida = dataEscolhida;

    }
}
