package com.example.henrique.list.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.henrique.list.R;

/**
 * Created by htamashiro on 3/16/15.
 */
public class CustLoginFragmentLandscape extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_cust_login_1_landscape, parent, false);

        Button buttonCriarConta = (Button)   v.findViewById(R.id.buttonCustLoginCriarConta);
        EditText ETnome = (EditText) v.findViewById(R.id.custLoginNome);
        EditText ETsenha = (EditText) v.findViewById(R.id.custLoginSenha);
        Button buttonLogar = (Button) v.findViewById(R.id.buttonCustLoginLogar);




           return v;
    }

}
