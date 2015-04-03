package com.example.henrique.list.Cliente;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.henrique.list.R;

public class CustEditProfileFragment_11 extends Fragment {
    private View v;
    private FragmentActivity fa;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fa = super.getActivity();
        v = inflater.inflate(R.layout.fragment_cust_profile_5_11, container, false);

        return v;
    }

}
