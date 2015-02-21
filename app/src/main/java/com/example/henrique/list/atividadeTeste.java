package com.example.henrique.list;

import android.app.Activity;
import android.os.Bundle;

import com.example.henrique.list.Bean.Cliente;
import com.example.henrique.list.DAO.ClienteDAO;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;


public class atividadeTeste extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste_layout);


        Cliente cliente;
        cliente = new Cliente(this);
        cliente.setId(40);
        cliente.setId_pessoa(69);


        ClienteDAO clienteDAO;
        clienteDAO = new ClienteDAO(this);

        try {
            clienteDAO.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // teste adicionar
        /*try {
            clienteDAO.adiciona(cliente);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }*/
        //teste remover
        /*try {
            clienteDAO.remove(cliente);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }*/
        //teste update
        try {
            clienteDAO.update(cliente);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }



    }


}
