package com.example.henrique.list.Beans;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by Massaru on 28/02/2015.
 */
/*Esta classe serve para sincronizar os items e seus respectivos fragments do Drawer*/
public class DrawerMenuItem {


    private Activity linkActivity;
    private Fragment linkFragment;
    private String linkTitle;

    public DrawerMenuItem(Fragment f, String s) {
        linkActivity = null;
        linkFragment = f;
        linkTitle = s;
    }

    public DrawerMenuItem(Activity a, String s) {
        linkActivity = a;
        linkFragment = null;
        linkTitle = s;
    }

    public Fragment getLinkFragment() {return linkFragment;}

    public void setLinkFragment(Fragment linkFragment) { this.linkFragment = linkFragment; }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public Activity getLinkActivity() { return linkActivity;}

    public void setLinkActivity(Activity linkActivity) { this.linkActivity = linkActivity; }
    public boolean isFragment(){
        if (linkFragment != null){
            return true;
        } else {
            return false;
        }
    }
    public boolean isActivity(){
        if (linkActivity != null){
            return true;
        } else {
            return false;
        }
    }
}
