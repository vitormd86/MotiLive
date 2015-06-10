package com.example.henrique.list.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.henrique.list.Beans.DrawerMenuItem;
import com.example.henrique.list.R;


public class MyAdapterDrawerOptions extends ArrayAdapter<DrawerMenuItem> {
    public MyAdapterDrawerOptions(Context context, DrawerMenuItem[] drawerMenuItems) {
        super(context, R.layout.view_drawer_options, drawerMenuItems);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.view_drawer_options, parent, false);

        String option = getItem(position).getLinkTitle();
        int iconResID = getItem(position).getResID();

        TextView textOption = (TextView) theView.findViewById(R.id.option);

        textOption.setText(option);
        textOption.setCompoundDrawablesWithIntrinsicBounds(iconResID, 0, 0, 0);
        return theView;

    }
}
