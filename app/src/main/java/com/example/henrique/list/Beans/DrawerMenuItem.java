package com.example.henrique.list.Beans;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;


/*Esta classe serve para sincronizar os items e seus respectivos fragments do Drawer*/
public class DrawerMenuItem {


    private Activity linkActivity;
    private Fragment linkFragment;
    private String linkTitle;

    private int resID;

    public DrawerMenuItem(Fragment f, String s, int iconResID) {
        linkActivity = null;
        linkFragment = f;
        linkTitle = s;
        resID = iconResID;
    }

    public DrawerMenuItem(Activity a, String s, int iconResID) {
        linkActivity = a;
        linkFragment = null;
        linkTitle = s;
        resID = iconResID;
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
        return linkFragment != null;
    }
    public boolean isActivity(){
        if (linkActivity != null){
            return true;
        } else {
            return false;
        }
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }
}
