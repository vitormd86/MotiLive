package com.example.henrique.list.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.ProfessionalDTO;


public class LoginProfileChoose_4 extends Activity {

    private Button clientChooseBT, professionalChooseBT;
    private String login, password;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_profile_choose_4);

        retrieveAttributes();
        initViews();
        setClientChooseListener();
        setProfessionalChooseListener();
    }

    private void retrieveAttributes() {
        login    = getIntent().getStringExtra(SessionAttributes.LOGIN);
        password = getIntent().getStringExtra(SessionAttributes.PASSWORD);
    }

    private void initViews(){
        clientChooseBT = (Button) findViewById(R.id.clienteBTN_4);
        professionalChooseBT = (Button) findViewById(R.id.profissionalBTN_4);
    }
    
    private void setClientChooseListener(){
        clientChooseBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CustProfile_5.class);
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setLogin(login);
                customerDTO.setPassword(password);

                intent.putExtra("isEditing", false);//todo tirar teste
                intent.putExtra(SessionAttributes.CUSTOMER, customerDTO);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });
    }
    
    private void setProfessionalChooseListener(){
        professionalChooseBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent professionalIntent = new Intent(getApplicationContext(),ProProfile_5.class);
                ProfessionalDTO professionalDTO = new ProfessionalDTO();
                professionalDTO.setLogin(login);
                professionalDTO.setPassword(password);

                professionalIntent.putExtra(SessionAttributes.PROFESSIONAL, professionalDTO);
                professionalIntent.putExtra("isEditing", false);
                professionalIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(professionalIntent);
            }
        });
    }
}
