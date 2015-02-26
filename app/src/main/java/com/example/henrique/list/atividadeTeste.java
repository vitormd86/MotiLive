package com.example.henrique.list;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.henrique.list.Bean.Pessoa;
import com.example.henrique.list.Bean.Plano;
import com.example.henrique.list.Bean.Plano_Profissional;
import com.example.henrique.list.Bean.Profissao;
import com.example.henrique.list.Bean.Profissional;
import com.example.henrique.list.DAO.PessoaDAO;
import com.example.henrique.list.DAO.PlanoDAO;
import com.example.henrique.list.DAO.Plano_ProfissionalDAO;
import com.example.henrique.list.DAO.ProfissaoDAO;
import com.example.henrique.list.DAO.ProfissionalDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
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
        /*
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

    */
 /*       Date date = new Date(1900/05/01);
        Date date2 = new Date(2000/02/24);


        Pessoa pessoa = new Pessoa();

        pessoa.setId(13);
        pessoa.setNome("frasilva");
        pessoa.setBairro("Jacana");
        pessoa.setCEP("02263090");
        pessoa.setCidade("Sao Paulo");
        pessoa.setCPF_CNPJ("12333333");
        pessoa.setData_nascimento(date);
        pessoa.setDDD("11");
        pessoa.setEndereco("Rua Baia sdf Passaros");
        pessoa.setDt_atualizacao(date2);
        pessoa.setEmail("jao@gmail.com");
        pessoa.setTelefone("1111111");
        pessoa.setEstado("SP");
        pessoa.setFacebook_login("testa");
        pessoa.setSexo("h");
        pessoa.setGoogle_login("tetetetetete");
        pessoa.setStatus(true);
        pessoa.setSenha("tetetete");
        pessoa.setNumero("194");
        pessoa.setLogin("testnado");
        pessoa.setComplemento("qualquer merda");


        PessoaDAO pessoaDAO = new PessoaDAO(this);

        try {
            pessoaDAO.execute().get();
            Toast.makeText(atividadeTeste.this, "Fez execute", Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(pessoaDAO.isConected())
        {
            Toast.makeText(atividadeTeste.this, "Fez as conexoes", Toast.LENGTH_LONG).show();

        }

        // teste adicionar
        try {
            Toast.makeText(atividadeTeste.this, "Entrou no try do adiciona", Toast.LENGTH_LONG).show();

            pessoaDAO.adiciona(pessoa);


        } catch (SQLException e1) {
            e1.printStackTrace();
        }
*/
/*

        Pessoa pessoinha =  new Pessoa();
        PessoaDAO pessoaDAO = new PessoaDAO(this);

        try {
            pessoaDAO.execute().get();
            Toast.makeText(atividadeTeste.this, "Fez execute", Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        // teste Select
        try {
            Toast.makeText(atividadeTeste.this, "Entrou no try do getPessoa", Toast.LENGTH_LONG).show();

            pessoinha = pessoaDAO.getPessoa(2);



        } catch (SQLException e1) {
            e1.printStackTrace();
        }
*/

     /*   Plano plano = new Plano();
        plano.setId(50);
        plano.setQtde_creditos(23.4);
        plano.setValor(43.5);
        plano.setQtde_dias(10);
        plano.setStatus(true);

        plano.setTipo(false);

        PlanoDAO planoDAO= new PlanoDAO(this) ;


        try {
            planoDAO.execute().get();
            Toast.makeText(atividadeTeste.this, "Fez execute", Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(planoDAO.isConected())
        {
            Toast.makeText(atividadeTeste.this, "Fez as conexoes", Toast.LENGTH_LONG).show();

        }

        // teste adicionar
        try {
            Toast.makeText(atividadeTeste.this, "Entrou no try do adiciona", Toast.LENGTH_LONG).show();

            planoDAO.adiciona(plano);


        } catch (SQLException e1) {
            e1.printStackTrace();
        }*/


     /*   Date date = new Date(2014-05-12);

        Date date2 = new Date(2004-05-12);

        Date date3 = new Date(2015-05-12);



        Plano_Profissional plano_profissional = new Plano_Profissional();
        plano_profissional.setId(20);
        plano_profissional.setId_plano(34);
        plano_profissional.setId_profissional(53);
        plano_profissional.setStatus(true);
        plano_profissional.setData_ativacao_plano(date);
        plano_profissional.setData_final(date2);
        plano_profissional.setData_inicial(date3);
        plano_profissional.setQtde_creditos_restantes(35);

        Plano_ProfissionalDAO plano_profissionalDAO = new Plano_ProfissionalDAO(this);

        try {
            plano_profissionalDAO.execute().get();
            Toast.makeText(atividadeTeste.this, "Fez execute", Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(plano_profissionalDAO.isConected())
        {
            Toast.makeText(atividadeTeste.this, "Fez as conexoes", Toast.LENGTH_LONG).show();

        }

        // teste adicionar
        try {
            Toast.makeText(atividadeTeste.this, "Entrou no try do adiciona", Toast.LENGTH_LONG).show();

            plano_profissionalDAO.adiciona(plano_profissional);


        } catch (SQLException e1) {
            e1.printStackTrace();
        }*/

/*        Profissao profissao = new Profissao();
        profissao.setNome("jao");
        profissao.setId(42);

        ProfissaoDAO profissaoDAO = new ProfissaoDAO(this);

        try {
            profissaoDAO.execute().get();
            Toast.makeText(atividadeTeste.this, "Fez execute", Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(profissaoDAO.isConected())
        {
            Toast.makeText(atividadeTeste.this, "Fez as conexoes", Toast.LENGTH_LONG).show();

        }

        // teste adicionar
        try {
            Toast.makeText(atividadeTeste.this, "Entrou no try do adiciona", Toast.LENGTH_LONG).show();

            profissaoDAO.adiciona(profissao);


        } catch (SQLException e1) {
            e1.printStackTrace();
        }*/

        Profissional profissional = new Profissional();
        profissional.setId(32);
        profissional.setId_profissao(42);
        profissional.setId_especializacao(41);
        profissional.setId_pessoa(43);
        profissional.setRegistro("blabla");
        profissional.setUrl_linkedin("testando");

        ProfissionalDAO profissionalDAO = new ProfissionalDAO(this);

        try {
            profissionalDAO.execute().get();
            Toast.makeText(atividadeTeste.this, "Fez execute", Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(profissionalDAO.isConected())
        {
            Toast.makeText(atividadeTeste.this, "Fez as conexoes", Toast.LENGTH_LONG).show();

        }

        // teste adicionar
        try {
            Toast.makeText(atividadeTeste.this, "Entrou no try do adiciona", Toast.LENGTH_LONG).show();

            profissionalDAO.adiciona(profissional);


        } catch (SQLException e1) {
            e1.printStackTrace();
        }




    }


}
