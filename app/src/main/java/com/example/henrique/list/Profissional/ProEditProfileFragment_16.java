package com.example.henrique.list.Profissional;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.henrique.list.R;


public class ProEditProfileFragment_16 extends Fragment{

    EditText nomeCompletoET;
    EditText celularET;
    EditText emailET;
    EditText ruaET;
    EditText numeroET;
    EditText CEPET;
    EditText complementoET;
    EditText bairroET;
    EditText cidadeET;
    EditText estadoET;

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_pro_profile_5, parent, false);

        //TODO  Aqui to implementando a forma como fazemos o webservice
        Button buttonActionBar = (Button) v.findViewById(R.id.action_bar);

        buttonActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return v;
    }


    // vai executar a função de salvar dados.
    public void salvarDaddos(View view)
    {

        // transformando os dados em string
        //TODO: Transformar em objeto

        String nomeCompleto = nomeCompletoET.getText().toString();
        String celular = celularET.getText().toString();
        String email = emailET.getText().toString();
        String rua = ruaET.getText().toString();
        String numero = numeroET.getText().toString();
        String CEP = CEPET.getText().toString();
        String complemento = complementoET.getText().toString();
        String bairro = bairroET.getText().toString();
        String cidade = cidadeET.getText().toString();
        String estado = estadoET.getText().toString();

    }

    }
