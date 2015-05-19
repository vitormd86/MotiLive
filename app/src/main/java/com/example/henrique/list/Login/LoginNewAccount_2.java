package com.example.henrique.list.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.henrique.list.R;
/**
 * Created by htamashiro on 3/17/15.
 *
 *
 */
public class LoginNewAccount_2 extends Activity {

    EditText nameET, passwordET, confirmPasswordET;
    Button createAccountBT, cancelBT;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new_account_2);

        initViews();
        setCreateAccountButtonListener();
        setCancelButtonListener();
        //todo criar funcionamento do checkbox "mostrar senha"
    }

    private void initViews(){
        nameET = (EditText) findViewById(R.id.loginNewAccountName);
        passwordET = (EditText) findViewById(R.id.loginNewAccountPassword);
        confirmPasswordET = (EditText) findViewById(R.id.loginNewAccountConfirmPassword);
        createAccountBT = (Button) findViewById(R.id.buttonloginNewAccount);
        cancelBT = (Button) findViewById(R.id.buttonCancel);
    }

    private void setCreateAccountButtonListener(){
        createAccountBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPasswordEquals()){
                    //todo verificar se o nome de usuario esta disponivel no banco
                    //todo criar o novo usuario no banco
                    Intent createAccountIntent = new Intent(LoginNewAccount_2.this, LoginProfileChoose_4.class);
                    startActivity(createAccountIntent);
                } else {
                    //todo avisar usuario da divergencia dos campos de password
                    passwordET.setText("");
                    confirmPasswordET.setText("");
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
    private boolean isPasswordEquals(){
        //verifica se os campos de password estao iguais
        if(passwordET.getText().toString().equals(confirmPasswordET.getText().toString()))
            return true;
        else
            return false;
    }
}
