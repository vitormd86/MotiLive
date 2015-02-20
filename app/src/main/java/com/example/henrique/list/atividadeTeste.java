package com.example.henrique.list;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;


public class atividadeTeste extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste_layout);


        Cliente cliente;
        cliente = new Cliente(this);
        cliente.setId(100);
        cliente.setId_pessoa(120);


        ClienteDAO clienteDAO;
        clienteDAO = new ClienteDAO(this);

        try {
            clienteDAO.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        try {
            clienteDAO.adiciona(cliente);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }



    }


}
