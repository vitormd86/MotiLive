package com.example.henrique.list.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.henrique.list.Drawer.DrawerMenuActivity;
import com.example.henrique.list.R;

/**
 * Created by htamashiro on 3/17/15.
 */
public class CustProfileChoose_3 extends Activity {

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custprofilechoose_3);


        Button EscolheCliente = (Button) findViewById(R.id.clienteBTN);
        Button EscolheProfissional = (Button) findViewById(R.id.profissionalBTN);

        EscolheCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent avancarTela = new Intent(CustProfileChoose_3.this , DrawerMenuActivity.class);
                startActivity(avancarTela);
            }
        });

        EscolheProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inicia intent do Profissioinal
            }
        });


    }
}
