package com.example.henrique.list.Profissional;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.henrique.list.R;

/**
 * Created by htamashiro on 3/21/15.
 */
public class Pro_Profile_5 extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pro_profile_5, parent, false);



        //TODO  Aqui to implementando a forma como fazemos o webservice
        Button buttonActionBar = (Button) v.findViewById(R.id.confirmarActionBar);

        buttonActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return v;

    }

    }
