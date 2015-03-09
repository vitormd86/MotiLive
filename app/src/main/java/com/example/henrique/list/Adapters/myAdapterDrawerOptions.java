package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.henrique.list.DrawerMenuItem;
import com.example.henrique.list.R;

/**
 * Created by Cristor on 26/02/2015.
 */
public class myAdapterDrawerOptions extends ArrayAdapter<DrawerMenuItem> {
    public myAdapterDrawerOptions(Context context, DrawerMenuItem[] clients) {
        super(context, R.layout.view_drawer_options, clients);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.view_drawer_options, parent, false);

        String option = getItem(position).getLinkTitle();
        TextView textOption = (TextView) theView.findViewById(R.id.option);

        textOption.setText(option);
        return theView;

    }
}
