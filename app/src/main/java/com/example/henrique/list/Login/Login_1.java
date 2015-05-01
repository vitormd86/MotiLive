package com.example.henrique.list.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henrique.list.Cliente.CustDrawerMenu_10;
import com.example.henrique.list.Cliente.CustScheduleListFragment_9;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.LoginService;
import com.example.henrique.list.Utilidade_Publica.Utility;
import com.loopj.android.http.RequestParams;

import br.com.motiserver.dto.CustomerDTO;

/**
 * Created by htamashiro on 3/16/15.
 */
public class Login_1 extends Activity {


    ProgressDialog prgDialog;
    TextView errorMsg;
    EditText emailET;
    EditText pwdET;
    public  RequestParams params;
    public  View view;
    public LoginService loginService;
    public CustomerDTO customerDTO;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_1);

        //Inicia as variáveis

        errorMsg = (TextView)findViewById(R.id.login_error);
        // Find Email Edit View control by ID
        emailET = (EditText)findViewById(R.id.custLoginNome);
        // Find Password Edit View control by ID
        pwdET = (EditText)findViewById(R.id.custLoginSenha);


        //inicia os listeners

        Button criarConta = (Button) findViewById(R.id.buttonCustLoginCriarConta);
        Button logar = (Button) findViewById(R.id.buttonCustLoginLogar);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        /*       if (loginUser(v))
               {
                   navigatetoMainActivity(v);
               }else //avisa q usuarios nao existem, e limpa os Edit Texts
               {
                   Toast.makeText(getApplication(), "Usuario nao existente", Toast.LENGTH_SHORT).show();
                   setDefaultValues();
               }*/
            }
        });
        //define o comportamento do botao criarConta
        criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigatetoRegisterActivity(v);
            }// aqui  eh só uma transiçao de tela mesmo
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Method gets triggered when Login button is clicked
     *
     * @param view
     */

    //funcao que tenta realizar o login do usuario
    public boolean  loginUser( View view){

        // Get Email Edit View Value
        String email = emailET.getText().toString();
        // Get Password Edit View Value
        String password = pwdET.getText().toString();
        // When Email Edit View and Password Edit View have values other than Null
        if(Utility.isNotNull(email) && Utility.isNotNull(password)){
            // When Email entered is Valid
            if(Utility.validate(email)){

                //invokeWS(params);
            }
            // When Email is invalid
            else{
                Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_LONG).show();
            }
        } else{
            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
        }

        //preciso colocar algum retorno
        return true;

    }



    // Utilizamos essa função para ir para a primeira tela do usuario logado, no caso.. sua lista de agendamentos.
    public void navigatetoHomeActivity(){
        Intent homeIntent = new Intent(getApplicationContext(),CustScheduleListFragment_9.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    //navega para a tela de cadastro no caso  activity_login_new_account_2
    public void navigatetoRegisterActivity(View view){
        Intent loginIntent = new Intent(Login_1.this,CustProfile_5.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }

    public void navigatetoMainActivity(View view){
        Intent loginIntent = new Intent(Login_1.this,CustDrawerMenu_10.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }
    /**
     * Set degault values for Edit View controls
     */
    public void setDefaultValues(){
        emailET.setText("");
        pwdET.setText("");
    }


}
