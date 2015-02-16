package com.example.henrique.list;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


public class _DrawerTestActivity extends ActionBarActivity {
    DrawerLayout mDrawerLayout;
    CharSequence mTitle;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        mTitle = "test";

       // mPlanetTitles = new String[]{"one", "two", "three"};
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
       // mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
       // mDrawerList.setAdapter(new ArrayAdapter<String>(this,
        //        R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        //mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


            mDrawerToggle = new ActionBarDrawerToggle(
                    this,                  /* host Activity */
                    mDrawerLayout,         /* DrawerLayout object */
                      /* nav drawer icon to replace 'Up' caret */
                    R.string.drawer_open,  /* "open drawer" description */
                    R.string.drawer_close  /* "close drawer" description */
            ) {

                /**
                 * Called when a drawer has settled in a completely closed state.
                 */
                public void onDrawerClosed(View view) {
                }

                /**
                 * Called when a drawer has settled in a completely open state.
                 */
                public void onDrawerOpened(View drawerView) {
                }
            };


        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

   @Override
    public void onConfigurationChanged(Configuration newConfig) {
       super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
       else
       if (id == R.id.action_settings) {
           return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    /**
     * Swaps fragments in the main content view
     */
//    private void selectItem(int position) {
        //Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();

        // Highlight the selected item, update the title, and close the drawer
        // mDrawerList.setItemChecked(position, true);
        //setTitle(mPlanetTitles[position]);
        //mDrawerLayout.closeDrawer(mDrawerList);
//    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

//    private class DrawerItemClickListener implements ListView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView parent, View view, int position, long id) {
//            selectItem(position);
//}
//    }
}
