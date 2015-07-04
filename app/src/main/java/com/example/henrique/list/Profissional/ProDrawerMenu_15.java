package com.example.henrique.list.Profissional;


import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.henrique.list.Adapters.MyAdapterDrawerOptions;
import com.example.henrique.list.Beans.DrawerMenuItem;
import com.example.henrique.list.Login.Login_1;
import com.example.henrique.list.Login.ProProfile_5;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.ProfessionalService;
import com.example.henrique.list.Service.local.LocalLoginService;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import br.com.motiserver.dto.ProfessionalDTO;

/*Atividade que configura o drawer e o frame layout que recebe os fragments*/
public class ProDrawerMenu_15 extends ActionBarActivity {


    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    CharSequence mTitle;
    ListView listOptions;
    ImageButton editProfileBT;

    Bundle extras = new Bundle();
    ProfessionalDTO professionalDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_drawer_15);

        retrieveAttributes();
        setInicialFragment();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initDrawer();
        setDrawerLayoutItems();
    }

    private void retrieveAttributes() {
        //recupera dados do da Intent e do BD
        professionalDTO = new ProfessionalDTO();

        if(getIntent().getExtras() != null){
            extras = getIntent().getExtras();
            professionalDTO = (ProfessionalDTO) getIntent().getSerializableExtra(SessionAttributes.PROFESSIONAL);
        }
    }

    public void setInicialFragment() {
        //verifica se existe uma intent anterior. caso exista, aponto a fragment a ser aberta.
        //caso nao exista, inicia com a tela inicial.

        //todo trocar este esquema por intentForResult
        if (extras != null) {
            int openFragment = extras.getInt("nextScreen");
            if (openFragment == 10) {
                initFragment("Novo Agendamento", new ProScheduleDateFragment_10());
            } else {
                initFragment("Meus Agendamentos", new ProScheduleListFragment_14());
            }
        } else {
            initFragment("Meus Agendamentos", new ProScheduleListFragment_14());
        }
    }

    private void initDrawer() {
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
    }

    public void initFragment(String title, Fragment fragment) {
        //inicia um fragment dentro do frame de conteudo

        if(!fragment.isVisible()){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            extras.putSerializable(SessionAttributes.PROFESSIONAL, professionalDTO);
            fragment.setArguments(extras);

            ft.replace(R.id.content_frame, fragment);
            setTitle(title);
            ft.commit();
        }

    }

    //Esta classe alimenta e configura o conteudo do drawer
    public void setDrawerLayoutItems() {
        //configurando itens dentro do menu lateral

        //Configurando cabecalho
        String userName = professionalDTO.getName();
        String occupation = professionalDTO.getProfession().getName();


        //todo tratar photo assim q o cadastro comecar a gravar as photos
        ImageView imagePhoto = (ImageView) findViewById(R.id.photoDrawer_pro15);
        TextView textName = (TextView) findViewById(R.id.name_pro15);
        TextView textOccupation = (TextView) findViewById(R.id.occupation_pro15);
        listOptions = (ListView) findViewById(R.id.ListView_pro15);
        editProfileBT = (ImageButton) findViewById(R.id.ic_editProfile_pro15);

        textName.setText(userName);
        textOccupation.setText(occupation);

        //configurando itens que serao incluso no drawer (menu de opcoes)
        ProScheduleDateFragment_10 proScheduleDateFragment_10 = new ProScheduleDateFragment_10();
        ProScheduleListFragment_14 proScheduleListFragment_14 = new ProScheduleListFragment_14();
        ProServiceListActivity_7 proServiceListActivity_7 = new ProServiceListActivity_7();
        ProScheduleConfig_8 proScheduleConfig_8 = new ProScheduleConfig_8();

        DrawerMenuItem item1 = new DrawerMenuItem(proScheduleListFragment_14, "Meus Agendamentos", R.drawable.ic_drawer_consult_schedule);
        DrawerMenuItem item2 = new DrawerMenuItem(proScheduleDateFragment_10, "Novo Agendamento", R.drawable.ic_drawer_new_schedule);
        DrawerMenuItem item3 = new DrawerMenuItem(proServiceListActivity_7, "Meus Serviços", R.drawable.ic_drawer_my_services);
        DrawerMenuItem item4 = new DrawerMenuItem(proScheduleConfig_8, "Configurar Expediente", R.drawable.ic_drawer_schedule_config);
        DrawerMenuItem[] menuOptions = {item1, item2, item3, item4};


        //configurando adapter e listeners da listView
        ListAdapter myAdapterDrawerOptions = new MyAdapterDrawerOptions(this, menuOptions);
        listOptions.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listOptions.setAdapter(myAdapterDrawerOptions);

        //criando listeners
        addListViewListener(menuOptions);
        addEditButtonListener();
    }

    private void addEditButtonListener(){
        //listener do botao de editprofile
        editProfileBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toEditProfileIntent = new Intent(ProDrawerMenu_15.this, ProProfile_5.class);
                toEditProfileIntent.putExtra(SessionAttributes.PROFESSIONAL, professionalDTO);
                startActivity(toEditProfileIntent);
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void addListViewListener(final DrawerMenuItem[] menuOptions){
        //configurando listeners da listview
        listOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Brilha opcao do vetor selecionada, atualiza titulo, e fecha o drawer
                listOptions.setItemChecked(position, true);

                //verifica se eh activity ou fragment
                if (menuOptions[position].isFragment()) {
                    initFragment(menuOptions[position].getLinkTitle(), menuOptions[position].getLinkFragment());
                } else if (menuOptions[position].isActivity()) {
                    Intent drawerlistIntent = new Intent(ProDrawerMenu_15.this, menuOptions[position].getLinkActivity().getClass());
                    drawerlistIntent.putExtra(SessionAttributes.PROFESSIONAL, professionalDTO);
                    startActivity(drawerlistIntent);
                    finish();
                }
                mDrawerLayout.closeDrawers();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logout, menu);
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
        } else if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.logout) {
            logout();
            return true;
        } else if (id == R.id.exit_the_app){
            exit_the_app();
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

    public  void logout(){
        LocalLoginService localLoginService = new LocalLoginService(ProDrawerMenu_15.this);
        localLoginService.logoff();
        Intent createAccountIntent = new Intent(ProDrawerMenu_15.this, Login_1.class);
        createAccountIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(createAccountIntent);
    }

    private void exit_the_app() {
        System.exit(1);
    }
}
