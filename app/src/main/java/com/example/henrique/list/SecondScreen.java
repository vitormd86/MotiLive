package com.example.henrique.list;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for the layout we created
        setContentView(R.layout.second_layout);

        // Get the Intent that called for this Activity to open

        Intent activityThatCalled = getIntent();

        // Get the data that was sent




    }

}
