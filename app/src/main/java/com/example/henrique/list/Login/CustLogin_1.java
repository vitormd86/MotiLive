package com.example.henrique.list.Login;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.example.henrique.list.Fragments.CustLoginFragmentLandscape;
import com.example.henrique.list.Fragments.CustLoginFragmentPortrait;
import com.example.henrique.list.R;

import static android.R.id.content;

/**
 * Created by htamashiro on 3/16/15.
 */
public class CustLogin_1 extends FragmentActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Configuration configInfo = getResources().getConfiguration();
        if (configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            CustLoginFragmentLandscape custLoginFragmentLandscape = new CustLoginFragmentLandscape();


        } else {

            CustLoginFragmentPortrait custLoginFragmentPortrait = new CustLoginFragmentPortrait();
            fragmentTransaction.replace(content, custLoginFragmentPortrait);

        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
