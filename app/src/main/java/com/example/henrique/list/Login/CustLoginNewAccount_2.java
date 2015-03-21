package com.example.henrique.list.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.henrique.list.R;

/**
 * Created by htamashiro on 3/17/15.
 */
public class CustLoginNewAccount_2 extends Activity {


    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    // Name Edit View Object
    EditText nameET;
    // Email Edit View Object
    EditText emailET;
    // Passwprd Edit View Object
    EditText pwdET;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custloginnewaccount_2);

        EditText login = (EditText) findViewById(R.id.loginNewAccountName);
        EditText senha = (EditText) findViewById(R.id.loginNewAccountSenha);
        Button logar = (Button) findViewById(R.id.buttonloginNewAccount);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent avancarTela = new Intent(CustLoginNewAccount_2.this , CustProfileChoose_4.class);
                startActivity(avancarTela);

                //TODO Mais tarde teremos que colocar AsyncTask para fazer o login e a sessao

            }
        });

    }
}
