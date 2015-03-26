package com.example.henrique.list.UsoPosterior;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.henrique.list.Cliente.CustScheduleDateFragmentLandscape_6;
import com.example.henrique.list.Cliente.CustScheduleDateFragmentPortrait_6;
import com.example.henrique.list.Login.LoginScreen;
import com.example.henrique.list.R;
import com.facebook.Session;

//Codigo de referencia para mudança de  orientação do device

public class ActivityFragmentPortraitControl extends FragmentActivity {

    @Override
    //OnCreate, é o q a Activity faz logo que inicia, neste caso, inicia os vetores, variáveis e adaptadores
    //Inicia tb a Listener das views( botoes)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();

        Configuration configInfo = getResources().getConfiguration();
        if (configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE){

            CustScheduleDateFragmentLandscape_6 custScheduleDateFragmentLandscape6 = new CustScheduleDateFragmentLandscape_6();
            fragmentTransaction.replace(android.R.id.content, custScheduleDateFragmentLandscape6);

        }else{

            CustScheduleDateFragmentPortrait_6 custScheduleDateFragmentPortrait6 = new CustScheduleDateFragmentPortrait_6();
            fragmentTransaction.replace(android.R.id.content, custScheduleDateFragmentPortrait6);

        }
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.exit_the_app) {
            // EFETUA LOGOUT E RETORNA PARA A TELA DE LOGIN
            if (Session.getActiveSession() != null) {
                Session.getActiveSession().closeAndClearTokenInformation();
            }
            Session.setActiveSession(null);
            Intent retornarTelaLogin = new Intent(this , LoginScreen.class);
            startActivity(retornarTelaLogin);
        }

        return super.onOptionsItemSelected(item);
    }
}
