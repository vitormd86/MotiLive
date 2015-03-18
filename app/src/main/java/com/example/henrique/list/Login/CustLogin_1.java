package com.example.henrique.list.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.henrique.list.Drawer.DrawerMenuActivity;
import com.example.henrique.list.R;

/**
 * Created by htamashiro on 3/16/15.
 */
public class CustLogin_1 extends FragmentActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custlogin_layout);

        Button criarConta = (Button) findViewById(R.id.buttonCustLoginCriarConta);
        EditText nome = (EditText) findViewById(R.id.custLoginNome );
        EditText senha = (EditText) findViewById(R.id.custLoginSenha );
        Button logar = (Button) findViewById(R.id.buttonCustLoginLogar);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent avancarTela = new Intent(CustLogin_1.this , CustLoginNewAccount_2.class);
                startActivity(avancarTela);

            }
        });

        criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent avancarTela = new Intent(CustLogin_1.this , DrawerMenuActivity.class);
                startActivity(avancarTela);
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
