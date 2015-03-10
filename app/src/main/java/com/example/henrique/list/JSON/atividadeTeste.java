package com.example.henrique.list.JSON;

import android.app.Activity;
import android.os.Bundle;

import com.example.henrique.list.Bean.Pessoa;
import com.example.henrique.list.R;

import java.sql.Date;


//Criei esta atividade com a finalidade de teste

public class atividadeTeste extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste_layout);



        Date date = new Date(1900/05/01);
        Date date2 = new Date(2000/02/24);

                Pessoa pessoa = new Pessoa();

                pessoa.setId(15);
                pessoa.setNome("frasilva");
                pessoa.setBairro("Jacana");
                pessoa.setCEP("50505050");
                pessoa.setCidade("Sao Paulo");
                pessoa.setCPF_CNPJ("345345345");
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
                pessoa.setLogin("joselitofrango");
                pessoa.setComplemento("qualquer merda");

        //Cria um objeto e transformo em JSON


              /*  JsonUtil jsonUtil = new JsonUtil();
                jsonUtil.toJSon(pessoa);

                JSONObject jObj = new JSONObject(jsonUtil);

                //escreve os objetos em jSON na tela pra ver se funcionou

                JSONObject subObj = jsonUtil.("pessoa");
                String city = subObj.getString("city");
*/
    }
}
