package com.example.henrique.list.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.henrique.list.Cliente.CustDrawerMenu_10;
import com.example.henrique.list.R;

/**
 * Created by htamashiro on 3/16/15.
 */
public class Login_1 extends Activity {

    TextView errorMsgTV;
    EditText emailET, pwdET;
    Button createAccountBT, loginBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_1);

        initViews();
        setLoginButtonListener();
        setSignInButtonListener();
    }

    private void initViews(){
        //inicia as views
        errorMsgTV = (TextView)findViewById(R.id.login_error);
        emailET = (EditText)findViewById(R.id.custLoginNome);
        pwdET = (EditText)findViewById(R.id.custLoginSenha);
        createAccountBT = (Button) findViewById(R.id.buttonCustLoginCriarConta);
        loginBT = (Button) findViewById(R.id.buttonCustLoginLogar);
    }

    private void setLoginButtonListener(){
        //listener do botao de login
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo validação de campos
                //todo verificar se é profissional ou cliente e encaminhar para a atividade equivalente
                Intent loginIntent = new Intent(Login_1.this, CustDrawerMenu_10.class);
                startActivity(loginIntent);
            }
        });
    }

    private void setSignInButtonListener(){
        //listener do botao de login
        createAccountBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Login_1.this, LoginNewAccount_2.class);
                startActivity(loginIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
