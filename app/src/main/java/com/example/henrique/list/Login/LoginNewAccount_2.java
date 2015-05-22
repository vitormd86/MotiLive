package com.example.henrique.list.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.Utility;

/**
 * Created by htamashiro on 3/17/15.
 *
 *
 */
public class LoginNewAccount_2 extends Activity {

    EditText nameET, passwordET, confirmPasswordET;
    CheckBox showPasswordCB;
    Button createAccountBT, cancelBT;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new_account_2);

        initViews();
        setCreateAccountButtonListener();
        setCancelButtonListener();
        setShowPasswordListener();
    }

    private void initViews(){
        nameET = (EditText) findViewById(R.id.loginNewAccountName);
        passwordET = (EditText) findViewById(R.id.loginNewAccountPassword);
        confirmPasswordET = (EditText) findViewById(R.id.loginNewAccountConfirmPassword);
        showPasswordCB = (CheckBox) findViewById(R.id.cbShowPassword);
        createAccountBT = (Button) findViewById(R.id.buttonloginNewAccount);
        cancelBT = (Button) findViewById(R.id.buttonCancel);

    }

    private void setCreateAccountButtonListener(){
        createAccountBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(executeSignUp()){
                    executeJSON();
                    Intent createAccountIntent = new Intent(LoginNewAccount_2.this, LoginProfileChoose_4.class);
                    startActivity(createAccountIntent);
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
        showPasswordCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        });
    }
    private boolean executeSignUp(){
        //este metodo verifica se os campos estao corretos e responde se o login pode ser criado ou nao
        boolean isExecutable = true;
        if (!Utility.isValidName(nameET.getText().toString())){
            nameET.setError("Campo de login contém caracteres inválidos.");
            isExecutable = false;
        }
        if (!Utility.isValid(nameET.getText().toString())){
            nameET.setError("Preencha o login do usuário.");
            isExecutable = false;
        }
        if (!Utility.isValid(passwordET.getText().toString())){
            passwordET.setError("Preeencha a senha para o seu login.");
            isExecutable = false;
        }
        if (!Utility.isValid(confirmPasswordET.getText().toString())){
            confirmPasswordET.setError("Preeencha a confirmação de senha para o seu login.");
            isExecutable = false;
        }
        if(!passwordET.getText().toString().equals(confirmPasswordET.getText().toString())){
            passwordET.setError("Os campos de senha devem ser idênticos.");
            confirmPasswordET.setError("Os campos de senha devem ser idênticos.");
            isExecutable = false;
        }
        return isExecutable;
    }

    private void executeJSON(){
        String sLogin, sPassword;

        sLogin = nameET.getText().toString();
        sPassword = passwordET.getText().toString();
        //todo verificar se o nome de usuario esta disponivel no banco
        //todo criar o novo usuario no banco

    }
}
