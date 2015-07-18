package com.example.henrique.list.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.henrique.list.R;
import com.example.henrique.list.Service.LoginService;
import com.example.henrique.list.Utilidade_Publica.ServiceException;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;
import com.example.henrique.list.Utilidade_Publica.DataValidatorUtil;

/**
 * Created by htamashiro on 3/17/15.
 *
 *
 */
public class LoginNewAccount_2 extends Activity {

    private EditText nameET, passwordET, confirmPasswordET;
    // TODO private CheckBox showPasswordCB;
    private Button createAccountBT, cancelBT;

    private LoginService loginService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new_account_2);
        this.loginService = new LoginService();

        initViews();
        setCreateAccountButtonListener();
        setCancelButtonListener();
        // TODO setShowPasswordListener();
    }

    private void initViews(){
        nameET = (EditText) findViewById(R.id.loginNewAccountName);
        passwordET = (EditText) findViewById(R.id.loginNewAccountPassword);
        confirmPasswordET = (EditText) findViewById(R.id.loginNewAccountConfirmPassword);
        // TODO showPasswordCB = (CheckBox) findViewById(R.id.cbShowPassword);
        createAccountBT = (Button) findViewById(R.id.buttonloginNewAccount);
        cancelBT = (Button) findViewById(R.id.buttonCancel);
    }

    private void setCreateAccountButtonListener(){
        createAccountBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(executeSignUp()){
                    executeLoginNewAccount();
                }
            }
        });
    }
    private void setCancelButtonListener(){
        cancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccountIntent = new Intent(LoginNewAccount_2.this, Login_1.class);
                startActivity(createAccountIntent);
            }
        });
    }
    private void setShowPasswordListener(){
        /* TODO showPasswordCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    passwordET.setTransformationMethod(new HideReturnsTransformationMethod());
                    confirmPasswordET.setTransformationMethod(new HideReturnsTransformationMethod());
                }
                else{
                    passwordET.setTransformationMethod(new PasswordTransformationMethod());
                    confirmPasswordET.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });*/
    }
    private boolean executeSignUp(){
        //este metodo verifica se os campos estao corretos e responde se o login pode ser criado ou nao
        boolean isExecutable = true;
        if (!DataValidatorUtil.isValidName(nameET.getText().toString())){
            nameET.setError("Campo de login contém caracteres inválidos.");
            isExecutable = false;
        }
        if (!DataValidatorUtil.isValid(nameET.getText().toString())){
            nameET.setError("Preencha o login do usuário.");
            isExecutable = false;
        }
        if (!DataValidatorUtil.isValid(passwordET.getText().toString())){
            passwordET.setError("Preeencha a senha para o seu login.");
            isExecutable = false;
        }
        if (!DataValidatorUtil.isValid(confirmPasswordET.getText().toString())){
            confirmPasswordET.setError("Preeencha a confirmação de senha para o seu login.");
            isExecutable = false;
        }
        if(!passwordET.getText().toString().equals(confirmPasswordET.getText().toString())){
            passwordET.setError("Os campos de senha devem ser idênticos.");
            confirmPasswordET.setError("Os campos de senha devem ser idênticos.");
            isExecutable = false;
        }
        if(DataValidatorUtil.isValid(confirmPasswordET.getText().toString()) && confirmPasswordET.getText().length() < 6){
            passwordET.setError("A senha deve possuir pelo menos 6 caracteres.");
            confirmPasswordET.setError("A senha deve possuir pelo menos 6 caracteres.");
            isExecutable = false;
        }
        return isExecutable;
    }

    private void executeLoginNewAccount(){
        String sLogin  = nameET.getText().toString();
        String sPassword = passwordET.getText().toString();

        // VERIFY IF IS EXISTING USER
        Boolean isExistingUser = null;
        try {
            isExistingUser = loginService.verifyExistingUser(sLogin);
        } catch (ServiceException ex) {
            Toast.makeText(getApplicationContext(), "Ocorreu um erro interno. Favor contactar o administrador!", Toast.LENGTH_SHORT).show();
        }

        if (isExistingUser != null) {
            if (isExistingUser) {
                nameET.setError("O login informado já está cadastrado.");
            } else {
                Intent createAccountIntent = new Intent(LoginNewAccount_2.this, LoginProfileChoose_4.class);
                createAccountIntent.putExtra(SessionAttributes.LOGIN, sLogin);
                createAccountIntent.putExtra(SessionAttributes.PASSWORD, sPassword);
                startActivity(createAccountIntent);
            }
        }
    }
}
