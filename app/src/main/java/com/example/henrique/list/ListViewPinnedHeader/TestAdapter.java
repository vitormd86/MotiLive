package com.example.henrique.list.ListViewPinnedHeader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.henrique.list.ListViewPinnedHeader.PinnedSectionListView.PinnedSectionListAdapter;
import com.example.henrique.list.R;

/**
 * Created by Henrique on 06/02/2015.
 */
//Adaptador ele joga as  a imagem e um EditText dentro de uma lista
public class TestAdapter extends ArrayAdapter<String> implements PinnedSectionListAdapter{


    public TestAdapter(Context context, String[] values) {
        super(context, R.layout.row_layout2, values);
    }

    @Override public int getViewTypeCount() {
        return 2;
    }
    @Override public int getItemViewType(int position) {
        if(position == 1){
            return 1;
        } else {
            return 0;
        }
    }
    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == 1;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.row_layout2, parent, false);


        String profissionais = getItem(position);


        TextView textView = (TextView) theView.findViewById(R.id.textView1);

        textView.setText(profissionais);
        ImageView theImageView =  (ImageView) theView.findViewById(R.id.imageView1);
        theImageView.setImageResource(R.drawable.hide);

        return theView;

    }

}
