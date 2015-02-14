package com.example.henrique.list;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import static com.example.henrique.list.R.id.action_settings;


public class MainActivity extends android.app.Activity {

    @Override
    //OnCreate, é o q a Activity faz logo que inicia, neste caso, inicia os vetores, variáveis e adaptadores
    //Inicia tb a Listener das views( botoes)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //cria um fragment manager
        FragmentManager fragmentManager = getFragmentManager();
        //cria um fragment transaction
        FragmentTransaction fragmentTransaction;
        // Inicia  a escolha de qual fragmento  usar
        fragmentTransaction = fragmentManager.beginTransaction();
        //Inicia  uma variável q diz qual a orientação
        Configuration configInfo = getResources().getConfiguration();

        if (configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentLandscape fragmentLandscape = new FragmentLandscape();
            fragmentTransaction.replace(android.R.id.content, fragmentLandscape);

        } else if(configInfo.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            FragmentPortrait fragmentPortrait = new FragmentPortrait();
            fragmentTransaction.replace(android.R.id.content, fragmentPortrait);

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
        if (id == action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
