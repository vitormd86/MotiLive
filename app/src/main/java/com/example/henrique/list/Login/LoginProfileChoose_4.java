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

    Button clientChooseBT, professionalChooseBT;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_profile_choose_4);

        initViews();
        setClientChooseListener();
        setProfessionalChooseListener();
    }

    private void initViews(){
        clientChooseBT = (Button) findViewById(R.id.clienteBTN_4);
        professionalChooseBT = (Button) findViewById(R.id.profissionalBTN_4);
    }
    
    private void setClientChooseListener(){
        clientChooseBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeJson(false);
                Intent toCustProfileIntent = new Intent(getApplicationContext(),CustProfile_5.class);
                toCustProfileIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(toCustProfileIntent);
            }
        });
    }
    
    private void setProfessionalChooseListener(){
        professionalChooseBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeJson(true);
                Intent toProProfileIntent = new Intent(getApplicationContext(),ProProfile_5.class);
                toProProfileIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(toProProfileIntent);
            }
        });
    }

    private void executeJson(boolean isProfessional){
        //0 = customer and 1 = profissional.
        if (isProfessional){
            //todo adicionar ao BD, o tipo PROFESSIONAL

        } else {
            //todo adicionar ao BD, o tipo CLIENTE
        }
    }
}
