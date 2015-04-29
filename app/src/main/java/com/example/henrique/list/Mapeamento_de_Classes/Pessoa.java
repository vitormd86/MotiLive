package com.example.henrique.list.Mapeamento_de_Classes;

import java.sql.Date;

/**
 * Created by htamashiro on 2/21/15.
 */
public class Pessoa {
    private int id;
    private String nome;
    private String cpf_cnpj;
    private String endereco;
    private String numero;
    private String complemento;
    private String Bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String email;
    private String ddd;
    private String telefone;
    private String sexo;
    private boolean status;
    private String login;
    private String senha;
    private String facebook_login;
    private String google_login;
    private Date dt_atualizacao;
    private Date data_nascimento;






    public String getGoogle_login() {
        return google_login;
    }

    public void setGoogle_login(String google_login) {
        this.google_login = google_login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF_CNPJ() {
        return cpf_cnpj;
    }

    public void setCPF_CNPJ(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCEP() {
        return cep;
    }

    public void setCEP(String cep) {
        this.cep = cep;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDDD() {
        return ddd;
    }

    public void setDDD(String DDD) {
        this.ddd = DDD;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }



    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFacebook_login() {
        return facebook_login;
    }

    public void setFacebook_login(String facebook_login) {
        this.facebook_login = facebook_login;
    }

    public Date getDt_atualizacao() {
        return dt_atualizacao;
    }

    public void setDt_atualizacao(Date dt_atualizacao) {
        this.dt_atualizacao = dt_atualizacao;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }


}
