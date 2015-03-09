package com.example.henrique.list.JSON;

import com.example.henrique.list.Bean.Agenda_Diaria;
import com.example.henrique.list.Bean.Agendamento;
import com.example.henrique.list.Bean.Cliente;
import com.example.henrique.list.Bean.Pessoa;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Definição das classes que farão a transformação de JSON
 */
public class JsonUtil {

    public static String toJSon(Pessoa pessoa) {

        try {
            // Here we convert Java Object to JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("nome", pessoa.getNome()); // Set the first name/pair
            jsonObj.put("cpf_cnpj", pessoa.getCPF_CNPJ());
            jsonObj.put("endereco", pessoa.getEndereco());
            jsonObj.put("numero", pessoa.getNumero());
            jsonObj.put("numero", pessoa.getNumero());
            jsonObj.put("complemento", pessoa.getComplemento());
            jsonObj.put("bairro", pessoa.getBairro() );
            jsonObj.put("numero", pessoa.getNumero());
            jsonObj.put("bairro", pessoa.getBairro());
            jsonObj.put("cidade", pessoa.getCidade());
            jsonObj.put("cep", pessoa.getCEP());
            jsonObj.put("email", pessoa.getEmail());
            jsonObj.put("ddd", pessoa.getDDD());
            jsonObj.put("telefone", pessoa.getTelefone());
            jsonObj.put("sexo", pessoa.getSexo());
            jsonObj.put("status", pessoa.getStatus());
            jsonObj.put("login", pessoa.getLogin());
            jsonObj.put("senha", pessoa.getSenha());
            jsonObj.put("facebook_login", pessoa.getFacebook_login());
            jsonObj.put("google_login", pessoa.getGoogle_login());
            jsonObj.put("data_nascimento", pessoa.getData_nascimento());

            //trnsforma o objeto em json
            return jsonObj.toString();

        }
        catch(JSONException ex) {
            ex.printStackTrace();
            System.out.println("Não consegue tranformar em json");

        }

        return null;

    }
    public static String toJSon(Cliente cliente) {


        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id", cliente.getId()); // Set the first name/pair
            jsonObj.put("id_pessoa", cliente.getId_pessoa());


            //trnsforma o objeto em json
            return jsonObj.toString();

        }
        catch(JSONException ex) {
            ex.printStackTrace();
            System.out.println("Não consegue tranformar em json");

        }

        return null;

    }
    public static String toJSon(Agenda_Diaria agenda_diaria) {


        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id", agenda_diaria.getId()); // Set the first name/pair
            jsonObj.put("id_profissional", agenda_diaria.getId_profissional());
            jsonObj.put("data", agenda_diaria.getData());
            jsonObj.put("dia_util", agenda_diaria.getDia_util());
            jsonObj.put("hora_entrada", agenda_diaria.getHora_entrada());
            jsonObj.put("hora_saida", agenda_diaria.getHora_saida());


            //trnsforma o objeto em json

            return jsonObj.toString();

        }
        catch(JSONException ex) {
            ex.printStackTrace();
            System.out.println("Não consegue tranformar em json");

        }

        return null;

    }
    public static String toJSon(Agendamento agendamento) {


        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id_agenda_diaria", agendamento.getId_agenda_diaria()); // Set the first name/pair
            jsonObj.put("id_servico", agendamento.getId_servico());
            jsonObj.put("hora_entrada", agendamento.getHora_entrada());
            jsonObj.put("id", agendamento.getId());
            jsonObj.put("hora_saida", agendamento.getHora_saida());
            jsonObj.put("id_cliente", agendamento.getId_cliente());


            //trnsforma o objeto em json

            return jsonObj.toString();

        }
        catch(JSONException ex) {
            ex.printStackTrace();
            System.out.println("Não consegue tranformar em json");
        }

        return null;

    }
}
