package com.example.henrique.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.henrique.list.Bean.Plano;
import com.example.henrique.list.DAO.PlanoDAO;


import java.sql.SQLException;
import java.util.concurrent.ExecutionException;


public class testandoBanco extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banco_layout);

        Button button = (Button) findViewById(R.id.buttonBanco);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Plano plano = new Plano();
                plano.setId(45);
                plano.setQtde_creditos(234);
                plano.setValor(43.5);
                plano.setQtde_dias(10);
                plano.setStatus(true);

                plano.setTipo(false);

                PlanoDAO planoDAO= new PlanoDAO(testandoBanco.this) ;


                try {
                    planoDAO.execute().get();
                    Toast.makeText(testandoBanco.this, "Fez execute", Toast.LENGTH_LONG).show();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                // teste adicionar
                try {
                    Toast.makeText(testandoBanco.this, "Entrou no try do adiciona", Toast.LENGTH_LONG).show();

                    planoDAO.adiciona(plano);
                    Toast.makeText(testandoBanco.this, "fez adição", Toast.LENGTH_LONG).show();



                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        }
        );




    }
}