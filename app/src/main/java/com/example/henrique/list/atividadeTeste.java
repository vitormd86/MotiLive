package com.example.henrique.list;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.henrique.list.Bean.Agenda_Diaria;
import com.example.henrique.list.Bean.Agendamento;
import com.example.henrique.list.Bean.Cliente;
import com.example.henrique.list.Bean.Especializacao;
import com.example.henrique.list.Bean.Intervalo;
import com.example.henrique.list.DAO.Agenda_DiariaDAO;
import com.example.henrique.list.DAO.AgendamentoDAO;
import com.example.henrique.list.DAO.ClienteDAO;
import com.example.henrique.list.DAO.EspecializacaoDAO;
import com.example.henrique.list.DAO.IntervaloDAO;

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

        /*
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
        /*
        Especializacao especializacao = new Especializacao();
        especializacao.setId(41);
        especializacao.setNome("jao");
        especializacao.setId_profissao(35);
        EspecializacaoDAO  especializacaoDAO = new EspecializacaoDAO(this);
        try {
            especializacaoDAO.execute().get();
            Toast.makeText(atividadeTeste.this, "Fez execute", Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            Toast.makeText(atividadeTeste.this, "Entrou no try do adiciona", Toast.LENGTH_LONG).show();

            especializacaoDAO.adiciona(especializacao);
            Toast.makeText(atividadeTeste.this, "conteudo time", Toast.LENGTH_LONG).show();


        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        */
        Time teste = new Time(23,12,12);
        Time teste2 = new Time(21,12,12);


        Intervalo intervalo = new Intervalo();
        intervalo.setId(42);
        intervalo.setId_agenda_diaria(34);


        intervalo.setHorario_final(teste);
        intervalo.setHorario_inicial(teste2);

        IntervaloDAO intervaloDAO = new IntervaloDAO(this);

        try {
            intervaloDAO.execute().get();
            Toast.makeText(atividadeTeste.this, "Fez execute", Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            Toast.makeText(atividadeTeste.this, "Entrou no try do adiciona", Toast.LENGTH_LONG).show();

            intervaloDAO.adiciona(intervalo);


        } catch (SQLException e1) {
            e1.printStackTrace();
        }




    }


}
