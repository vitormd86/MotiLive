package com.example.henrique.list;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.henrique.list.Adapters.MyAdapterDrawerOptions;
import com.example.henrique.list.Fragments.ScheduleDateFragmentP;
import com.example.henrique.list.Fragments.ProfessionalCalendarFragment;


public class ActivityDrawer extends ActionBarActivity {
    DrawerLayout mDrawerLayout;
    CharSequence mTitle;
    ActionBarDrawerToggle mDrawerToggle;
    ListView listOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        //inicia o fragment inicial dentro do frame de conteudo
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new ScheduleDateFragmentP());
        setTitle("Novo Agendamento");
        ft.commit();


        //Criacao e configuracao do menu lateral
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

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
        //configurando itens dentro do menu lateral
        setLayoutItems();
    }

    //Esta classe alimenta e configura o conteudo do drawer
    public void setLayoutItems (){

        //Configurando cabecalho
        String userName = "Leandro";
        String occupation = "Massagista";

        ImageView imagePhoto = (ImageView) findViewById(R.id.photo);
        TextView textName = (TextView) findViewById(R.id.name);
        TextView textOccupation = (TextView) findViewById(R.id.occupation);

        textName.setText(userName);
        textOccupation.setText(occupation);

        //configurando listview (menu de opcoes)
        ProfessionalCalendarFragment professionalCalendar = new ProfessionalCalendarFragment();
        ScheduleDateFragmentP scheduleDateFragmentP = new ScheduleDateFragmentP();
        DrawerMenuItem item1 = new DrawerMenuItem(scheduleDateFragmentP, "Novo Agendamento");
        DrawerMenuItem item2 = new DrawerMenuItem(professionalCalendar, "Consultar Agenda");
        final DrawerMenuItem [] menuOptions = {item1, item2};

        listOptions = (ListView) findViewById(R.id.ListView);
        //configurando adapter da listView
        ListAdapter myAdapterDrawerOptions= new MyAdapterDrawerOptions(this, menuOptions);
        listOptions.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listOptions.setAdapter(myAdapterDrawerOptions);

        //configurando listeners da listview
        listOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectMenuItem(position, menuOptions);
            }
        });
    }

    //esta classe recebe a posicao do item clicado no menu e determina qual fragment vai ser chamado no content_frame
    private void selectMenuItem(int pos, DrawerMenuItem [] menuOptions){

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, menuOptions[pos].getLinkFragment()); //aponta para o fragment correspondente ao clique no vetor de fragments
        ft.commit();

        //Brilha opcao do vetor selecionada, atualiza titulo, e fecha o drawer
        listOptions.setItemChecked(pos, true);
        setTitle(menuOptions[pos].getLinkTitle());
        mDrawerLayout.closeDrawers();
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
