package com.example.henrique.list.Fragments;

import android.support.v4.app.Fragment;

/**
 * Created by Massaru on 28/02/2015.
 */
/*Esta classe serve para sincronizar os items e seus respectivos fragments do Drawer*/
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
