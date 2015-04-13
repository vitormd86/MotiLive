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
import com.example.henrique.list.Utilidade_Publica.Utility;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

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
    CustomerDTO customerDTO = new CustomerDTO();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_1);

        //Inicia as variáveis

        errorMsg = (TextView)findViewById(R.id.login_error);
        // Find Email Edit View control by ID
        emailET = (EditText)findViewById(R.id.custLoginNome);
        // Find Password Edit View control by ID
        pwdET = (EditText)findViewById(R.id.custLoginSenha);

        CustomerDTO customerDTO1 = new CustomerDTO(); //  onde armazenaremos os dados retornados pelo json

        //inicia os listeners

        Button criarConta = (Button) findViewById(R.id.buttonCustLoginCriarConta);
        Button logar = (Button) findViewById(R.id.buttonCustLoginLogar);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (loginUser(v))
               {
                   navigatetoMainActivity(v);
               }else //avisa q usuarios nao existem, e limpa os Edit Texts
               {
                   Toast.makeText(getApplication(), "Usuario nao existente", Toast.LENGTH_SHORT).show();
                   setDefaultValues();
               }
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
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
        // When Email Edit View and Password Edit View have values other than Null
        if(Utility.isNotNull(email) && Utility.isNotNull(password)){
            // When Email entered is Valid
            if(Utility.validate(email)){
                // Put Http parameter username with value of Email Edit View control
                params.put("username", email);
                // Put Http parameter password with value of Password Edit Value control
                params.put("password", password);
                // Invoke RESTful Web Service with Http parameters
                invokeWS(params);
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
    public void invokeWS(RequestParams params){

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.169.198.138:8080/login/user",params ,new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                // Hide Progress Dialog
                prgDialog.hide();
                try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);
                    // When the JSON response has status boolean value assigned with true
                    if(obj.getBoolean("status")){
                        Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
                        // Navigate to Home screen
                        navigatetoHomeActivity();
                    }
                    // Else display error message
                    else{
                        errorMsg.setText(obj.getString("error_msg"));
                        Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }
            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                // Hide Progress Dialog
                prgDialog.hide();
                // When Http response code is '404'
                if(statusCode == 404){
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if(statusCode == 500){
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else{
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    // Utilizamos essa função para ir para a primeira tela do usuario logado, no caso.. sua lista de agendamentos.
    public void navigatetoHomeActivity(){
        Intent homeIntent = new Intent(getApplicationContext(),CustScheduleListFragment_9.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    //navega para a tela de cadastro no caso  activity_login_new_account_2
    public void navigatetoRegisterActivity(View view){
        Intent loginIntent = new Intent(Login_1.this,LoginNewAccount_2.class);
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
