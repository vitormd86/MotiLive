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
public class CustProfileChoose_4 extends Activity {

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custprofilechoose_4);


        Button EscolheCliente = (Button) findViewById(R.id.clienteBTN);
        Button EscolheProfissional = (Button) findViewById(R.id.profissionalBTN);

        EscolheCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent avancarTela = new Intent(CustProfileChoose_4.this , CustDrawerMenu_10.class);
                startActivity(avancarTela);
            }
        });

        EscolheProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent avancarTela = new Intent(CustProfileChoose_4.this , ProDrawerMenu_15.class);
                startActivity(avancarTela);            }
        });


    }
}
