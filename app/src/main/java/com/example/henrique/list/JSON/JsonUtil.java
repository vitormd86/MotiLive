package com.example.henrique.list.JSON;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Definição das classes que farão a transformação de JSON
 */

//Classe feita para tratar o envio e recebimento de dados JSON

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
}
