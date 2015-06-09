package com.example.henrique.list.Mapeamento_de_Classes;


public class Profissional {
    private int id_pessoa;
    private int id_profissao;
    private int id;
    private String registro;
    private int id_especializacao;
    private String url_linkedin;

    public String getUrl_linkedin() {
        return url_linkedin;
    }

    public void setUrl_linkedin(String url_linkedin) {
        this.url_linkedin = url_linkedin;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public int getId_profissao() {
        return id_profissao;
    }

    public void setId_profissao(int id_profissao) {
        this.id_profissao = id_profissao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public int getId_especializacao() {
        return id_especializacao;
    }

    public void setId_especializacao(int id_especializacao) {
        this.id_especializacao = id_especializacao;
    }


}
