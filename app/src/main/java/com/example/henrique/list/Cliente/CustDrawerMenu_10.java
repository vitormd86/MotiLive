package com.example.henrique.list.Cliente;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.henrique.list.Mapeamento_de_Classes.DrawerMenuItem;
import com.example.henrique.list.R;

/*Atividade que configura o drawer e o frame layout que recebe os fragments*/
public class CustDrawerMenu_10 extends ActionBarActivity {
    DrawerLayout mDrawerLayout;
    CharSequence mTitle;
    ActionBarDrawerToggle mDrawerToggle;
    ListView listOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        setInicialFragment();

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

    public void setInicialFragment(){
        //verifica se existe uma intent anterior. caso exista, aponto a fragment a ser aberta.
        //caso nao exista, inicia com a tela inicial.
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            int openFragment = extras.getInt("nextScreen");
            if (openFragment == 6){
                initFragment("Novo Agendamento", new CustScheduleDateFragmentPortrait_6());
            } else {
                initFragment("Meus Agendamentos", new CustScheduleListFragment_9());
            }
        }
        else {
            initFragment("Meus Agendamentos", new CustScheduleListFragment_9());
        }
    }

    public void initFragment(String title, Fragment fragment){
        //inicia um fragment dentro do frame de conteudo
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        setTitle(title);
        ft.commit();
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
        CustScheduleDateFragmentPortrait_6 custScheduleDateFragment= new CustScheduleDateFragmentPortrait_6();
        CustScheduleListFragment_9 custScheduleListFragment9 = new CustScheduleListFragment_9();
        CustProfileFragment_5 custProfileFragment5 = new CustProfileFragment_5();
        DrawerMenuItem item1 = new DrawerMenuItem(custScheduleDateFragment, "Novo Agendamento");
        DrawerMenuItem item2 = new DrawerMenuItem(custScheduleListFragment9, "Consultar Agendamentos");
        DrawerMenuItem item3 = new DrawerMenuItem(custProfileFragment5, "Editar Perfil");
        final DrawerMenuItem [] menuOptions = {item1, item2, item3};

        listOptions = (ListView) findViewById(R.id.ListView);
        //configurando adapter da listView
        ListAdapter myAdapterDrawerOptions= new MyAdapterDrawerOptions(this, menuOptions);
        listOptions.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listOptions.setAdapter(myAdapterDrawerOptions);

        //configurando listeners da listview
        listOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Brilha opcao do vetor selecionada, atualiza titulo, e fecha o drawer
                listOptions.setItemChecked(position, true);
                initFragment(menuOptions[position].getLinkTitle(), menuOptions[position].getLinkFragment());
                mDrawerLayout.closeDrawers();
            }
        });
    }

    //esta classe recebe a posicao do item clicado no menu e determina qual fragment vai ser chamado no content_frame
    private void selectMenuItem(int pos, DrawerMenuItem [] menuOptions){

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, menuOptions[pos].getLinkFragment()); //aponta para o fragment correspondente ao clique no vetor de fragments
        ft.commit();


        setTitle(menuOptions[pos].getLinkTitle());
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

}
