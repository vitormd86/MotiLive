package com.example.henrique.list.JSON;

import com.example.henrique.list.Mapeamento_de_Classes.Servico;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.sql.Time;
import java.util.Date;

/**
 * Definição das classes que farão a transformação de JSON
 */

//Classe feita para tratar o envio e recebimento de dados JSON
    // A classe era async,

public class JsonUtil {


// construtor

 public JsonUtil(Object object)
 {

       Gson gson = new Gson();
       gson.toJson(object);
       JSONObject jsonObject = new JSONObject();

 }



public boolean enviaServidor()
{
    //cria um http da vida POST e etc.. e envia
    // retorna true se enviado.
    return false;

}
public JSONObject recebeServidor()
{

    // recebe um http loko da vida.. e  depois  joga pra um objeto JSON
    //retorna null caso nao haja sucesso

    JSONObject jsonRetornado = new JSONObject();
    return jsonRetornado;

}


//funcao q retorna os servicos oferecidos por determinado Profissional
public Servico[] retornaServicosProfissional (int idProfissional)
{
    Servico[] servicos = new Servico[10]; // vetor para armazenar os servicos


    return servicos;
}
    //funcao q retornará somente os horarios disponiveis de um determinado profissional de um determinado dia,
    // tem q estar armazenado de 5 em 5 minutos


public Time[] retornaHorariosDisponiveisProfissional ( int idProfissional, Date Dia)
{
    Time[] time = new Time[10];
    return time;
}


}
