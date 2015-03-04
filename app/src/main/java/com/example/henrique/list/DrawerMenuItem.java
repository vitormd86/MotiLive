package com.example.henrique.list;

import android.support.v4.app.Fragment;

import com.example.henrique.list.Fragments.FragmentPortrait;

/**
 * Created by Massaru on 28/02/2015.
 */
public class DrawerMenuItem {
    private Fragment linkFragment;
    private String linkTitle;

    public DrawerMenuItem(Fragment f, String s){
        linkFragment = f;
        linkTitle = s;
    }

    public Fragment getLinkFragment() {
        return linkFragment;
    }

    public void setLinkFragment(Fragment linkFragment) {
        this.linkFragment = linkFragment;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }
}
