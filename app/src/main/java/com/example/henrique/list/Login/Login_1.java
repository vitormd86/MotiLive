package com.example.henrique.list.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

import br.com.motiserver.dto.PersonDTO;

/**
 * Created by htamashiro on 3/16/15.
 */
public class Login_1 extends Activity {


    ProgressDialog prgDialog;
    TextView errorMsg;
    EditText emailET;
    EditText pwdET;
    String email;
    String password;
    public  View view;
    public LoginService loginService;
    public PersonDTO personDTO;



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
                loginUser(v);


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
        email = emailET.getText().toString();
        // Get Password Edit View Value
        password = pwdET.getText().toString();
        // When Email Edit View and Password Edit View have values other than Null


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

    private class HttpRequestTask extends AsyncTask<Void, Void, PersonDTO> {
        @Override
        protected PersonDTO doInBackground(Void... params) {
            try {

                personDTO =loginService.login(email, password);
                return personDTO;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute() {
             Toast.makeText(Login_1.this, "Login efetuado com sucesso", Toast.LENGTH_LONG).show();
        }

    }


}
