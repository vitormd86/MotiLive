package com.example.henrique.list.Mapeamento_de_Classes;


import android.content.Context;

public class Cliente {


    private int id;
    private int id_pessoa;
    private Context context;



    public Cliente(Context context)
    {
        this.context = context;
    }


    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
