package com.example.henrique.list.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.henrique.list.Cliente.CustDrawerMenu_10;
import com.example.henrique.list.Profissional.ProDrawerMenu_15;
import com.example.henrique.list.R;

/**
 * Created by htamashiro on 3/17/15.
 */
public class LoginProfileChoose_4 extends Activity {

    Button EscolheCliente;
    Button EscolheProfissional;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_profile_choose_4);


        EscolheCliente = (Button) findViewById(R.id.clienteBTN_1);
        EscolheProfissional = (Button) findViewById(R.id.profissionalBTN_1);
        EscolheCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToClient(v);
            }
        });



        EscolheProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { navigateToProfissional(v);            }
        });


    }

    //metodo de navegacao paraprimeira tela profissional
    public void navigateToProfissional(View view){
        Intent loginIntent = new Intent(getApplicationContext(),ProDrawerMenu_15.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }

    //metodo de navegacao para primeira tela cliente
    public void navigateToClient(View view){
        Intent escolheuProfissional = new Intent(getApplicationContext(),CustDrawerMenu_10.class);
        escolheuProfissional.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(escolheuProfissional);
    }
}
