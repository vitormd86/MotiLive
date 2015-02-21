package com.example.henrique.list;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.henrique.list.Bean.Agenda_Diaria;
import com.example.henrique.list.Bean.Agendamento;
import com.example.henrique.list.Bean.Cliente;
import com.example.henrique.list.DAO.Agenda_DiariaDAO;
import com.example.henrique.list.DAO.AgendamentoDAO;
import com.example.henrique.list.DAO.ClienteDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;


public class atividadeTeste extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste_layout);


        Time teste =  new Time(3,25,25);



        Agendamento agendamento;
        agendamento = new Agendamento();
        agendamento.setId(35);
        agendamento.setId_agenda_diaria(70);
        agendamento.setId_cliente(40);
        agendamento.setId_servico(65);
        agendamento.setHora_entrada(teste);
        agendamento.setHora_saida(teste);
        Toast.makeText(atividadeTeste.this, "terminou as atribuicoes", Toast.LENGTH_LONG).show();


        //daqui pra baixo nao temos problemas.

        AgendamentoDAO agendamentoDAO = new AgendamentoDAO(this);

        try {
            agendamentoDAO.execute().get();
            Toast.makeText(atividadeTeste.this, "Fez execute", Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(agendamentoDAO.isConected())
        {
            Toast.makeText(atividadeTeste.this, "Fez as conexoes", Toast.LENGTH_LONG).show();

        }

        // teste adicionar
        try {
            Toast.makeText(atividadeTeste.this, "Entrou no try do adiciona", Toast.LENGTH_LONG).show();

            agendamentoDAO.adiciona(agendamento);
            Toast.makeText(atividadeTeste.this, "conteudo time"+ teste.getTime(), Toast.LENGTH_LONG).show();


        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        //teste remover
        /*try {
            clienteDAO.remove(cliente);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }*/
        //teste update
      /*  try {
            clienteDAO.update(cliente);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        */



    }


}
