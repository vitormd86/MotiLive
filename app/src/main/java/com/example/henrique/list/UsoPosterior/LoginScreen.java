package com.example.henrique.list.UsoPosterior;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.henrique.list.Login.LoginSignUpFacebook_3;
import com.example.henrique.list.R;

public class LoginScreen extends FragmentActivity {

    private LoginSignUpFacebook_3 custLoginSignUpFacebook3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null)
        {
            // Add the fragment on initial activity setup
            custLoginSignUpFacebook3 = new LoginSignUpFacebook_3();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, custLoginSignUpFacebook3)
                    .commit();
            Log.i("LoginScreen", "Passou em cima...");
        } else {
            // Or set the fragment from restored state info
            custLoginSignUpFacebook3 = (LoginSignUpFacebook_3) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
            Log.i("LoginScreen", "Passou em baixo...");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_screen, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("LoginScreen", "Passa no resumeeeeeeeeeeeeeeeeeeeee");
    }
}