package com.example.henrique.list.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henrique.list.Cliente.CustDrawerMenu_10;
import com.example.henrique.list.Profissional.ProDrawerMenu_15;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.CustomerService;
import com.example.henrique.list.Service.LoginService;
import com.example.henrique.list.Service.ProfessionalService;
import com.example.henrique.list.Utilidade_Publica.ServiceException;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;
import com.example.henrique.list.Utilidade_Publica.DataValidatorUtil;

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.util.constants.PersonType;


public class Login_1 extends Activity {

    TextView errorMsgTV;
    EditText userET, pwdET;
    Button createAccountBT, loginBT;

    CustomerService customerService = new CustomerService();
    LoginService loginService = new LoginService();
    ProfessionalService professionalService = new ProfessionalService();

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
        userET = (EditText)findViewById(R.id.custLoginNome);
        pwdET = (EditText)findViewById(R.id.custLoginSenha);
        createAccountBT = (Button) findViewById(R.id.buttonCustLoginCriarConta);
        loginBT = (Button) findViewById(R.id.buttonCustLoginLogar);
    }

    private void setLoginButtonListener(){
        //listener do botao de login
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            executeLogin();
            }
        });
    }

    private void executeLogin() {
        boolean executeJson = true;

        String user = userET.getText().toString();
        if (!DataValidatorUtil.isValid(user)) {
            userET.setError("Preencha o login do usuário!");
            executeJson = false;
        }

        String password = pwdET.getText().toString();
        if (!DataValidatorUtil.isValid(password)) {
            pwdET.setError("Preencha o password do usuário!");
            executeJson = false;
        }

        if (executeJson) {
            try {
                CustomerDTO customerDTO = loginService.login(user, password);

                // IF SERVICE RETURNS NULL, NOTIFY THAT USER OR PASSWORD IS WRONG
                if (customerDTO == null) {
                    Toast.makeText(getApplicationContext(), "Login ou senha inválido!", Toast.LENGTH_SHORT).show();
                } else {
                    if (customerDTO.getType().equals(PersonType.CUSTOMER)) {
                        Intent loginIntent = new Intent(Login_1.this, CustDrawerMenu_10.class);
                        loginIntent.putExtra(SessionAttributes.CUSTOMER, customerDTO);
                        startActivity(loginIntent);
                    } else {
                        ProfessionalDTO professionalDTO = professionalService.find(customerDTO.getId());
                        Intent loginIntent = new Intent(Login_1.this, ProDrawerMenu_15.class);
                        loginIntent.putExtra(SessionAttributes.PROFESSIONAL, professionalDTO);
                        startActivity(loginIntent);
                    }
                }
            } catch (ServiceException ex) {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro interno. Favor contactar o administrador!", Toast.LENGTH_SHORT).show();
            }
        }
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
