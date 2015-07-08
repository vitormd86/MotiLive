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
import com.example.henrique.list.Notification.TokenRegistrationIntentService;
import com.example.henrique.list.Profissional.ProDrawerMenu_15;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.CustomerService;
import com.example.henrique.list.Service.LoginService;
import com.example.henrique.list.Service.ProfessionalService;
import com.example.henrique.list.Service.local.LocalLoginService;
import com.example.henrique.list.Utilidade_Publica.ServiceException;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;
import com.example.henrique.list.Utilidade_Publica.DataValidatorUtil;

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.util.constants.PersonType;

public class Login_1 extends Activity {

    private TextView errorMsgTV;
    private EditText userET, pwdET;
    private Button createAccountBT, loginBT;

    private CustomerService customerService = new CustomerService();
    private LocalLoginService localLoginService = null;
    private LoginService loginService = new LoginService();
    private ProfessionalService professionalService = new ProfessionalService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_1);

        localLoginService = new LocalLoginService(getApplicationContext());
        if (!executeLocalLogin()) {
            initViews();
            setLoginButtonListener();
            setSignInButtonListener();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    private boolean executeLocalLogin() {
        CustomerDTO customerLocal = null;
        try {
            customerLocal = localLoginService.login();
            if (customerLocal != null) {
                // CALL A LOGIN SERVICE
                CustomerDTO customerDTO = loginService.login(customerLocal.getLogin(), customerLocal.getPassword());
                if (customerDTO != null) {
                    // REGISTER TOKEN CLOUD MESSAGING TO LOGGED USER
                    registerCloudMessagingToken(customerDTO);
                    // REDIRECT TO A NEXT SCREEN
                    redirectToNextScreen(customerDTO);
                    return true;
                } else {
                    localLoginService.logoff();
                }
            }
        } catch(ServiceException ex){
            Toast.makeText(getApplicationContext(), "Ocorreu um erro interno. Favor contactar o administrador!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void executeLogin() {
        String user = userET.getText().toString();
        String password = pwdET.getText().toString();
        if (isValidLogin(user, password)) {
            try {
                // CALL A LOGIN SERVICE
                CustomerDTO customerDTO = loginService.login(user, password);
                // IF SERVICE RETURNS NULL, NOTIFY THAT USER OR PASSWORD IS WRONG
                if (customerDTO == null) {
                    Toast.makeText(getApplicationContext(), "Login ou senha inválido!", Toast.LENGTH_SHORT).show();
                } else {
                    //  REGISTER LOGIN LOCALLY
                    localLoginService.registerLogin(customerDTO);
                    // REGISTER TOKEN CLOUD MESSAGING TO LOGGED USER
                    registerCloudMessagingToken(customerDTO);
                    // REDIRECT TO A NEXT SCREEN
                    redirectToNextScreen(customerDTO);
                }
            } catch (ServiceException ex) {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro interno. Favor contactar o administrador!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isValidLogin(String user, String password) {
        // VALIDATE LOGIN FIELDS
        boolean executeJson = true;
        if (!DataValidatorUtil.isValid(user)) {
            userET.setError("Preencha o login do usuário!");
            executeJson = false;
        }
        if (!DataValidatorUtil.isValid(password)) {
            pwdET.setError("Preencha o password do usuário!");
            executeJson = false;
        }
        return executeJson;
    }

    private void registerCloudMessagingToken(CustomerDTO customerDTO) {
        // CALL A INTENT TO REGISTER A TOKEN CLOUD MESSAGING
        Intent intent = new Intent(this, TokenRegistrationIntentService.class);
        intent.putExtra(SessionAttributes.CUSTOMER, customerDTO);
        startService(intent);
    }

    private void redirectToNextScreen(CustomerDTO customerDTO) throws ServiceException {
        // REDIRECT TO CUSTOMER SCREEN
        if (customerDTO.getType().equals(PersonType.CUSTOMER)) {
            Intent loginIntent = new Intent(Login_1.this, CustDrawerMenu_10.class);
            loginIntent.putExtra(SessionAttributes.CUSTOMER, customerDTO);
            startActivity(loginIntent);
            // REDIRECT TO PROFESSIONAL SCREEN
        } else {
            ProfessionalDTO professionalDTO = professionalService.find(customerDTO.getId());
            Intent loginIntent = new Intent(Login_1.this, ProDrawerMenu_15.class);
            loginIntent.putExtra(SessionAttributes.PROFESSIONAL, professionalDTO);
            startActivity(loginIntent);
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
}
