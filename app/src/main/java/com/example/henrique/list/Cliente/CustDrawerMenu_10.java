package com.example.henrique.list.Cliente;

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
import com.example.henrique.list.Login.CustProfile_5;
import com.example.henrique.list.Login.Login_1;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.CustomerService;
import com.example.henrique.list.Service.local.LocalLoginService;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import br.com.motiserver.dto.CustomerDTO;

/*Atividade que configura o drawer e o frame layout que recebe os fragments*/
public class CustDrawerMenu_10 extends ActionBarActivity {

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;
    private DrawerLayout mDrawerLayout;
    private ListView listOptions;
    private ImageButton editProfileBT;

    Bundle extras = new Bundle();
    private CustomerDTO customerDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_drawer_10);

        retrieveAttributes();
        setInicialFragment();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initDrawer();
        setDrawerLayoutItems();
    }

    private void retrieveAttributes() {
        //recupera dados do da Intent e do BD

        customerDTO = new CustomerDTO();

        if (getIntent().getExtras() != null) {
            extras = getIntent().getExtras();
            customerDTO = (CustomerDTO) getIntent().getSerializableExtra(SessionAttributes.CUSTOMER);
        }

        //todo-vitor apagar assim q tiver o logout
        CustomerService custService = new CustomerService();
        Long idProfessional = Long.valueOf(2);
        try {
            customerDTO = custService.find(idProfessional);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error findind customer with id " + idProfessional.toString());
        }
        //todo-end
    }

    public void setInicialFragment() {
        //verifica se existe uma intent anterior. caso exista, aponto a fragment a ser aberta.
        //caso nao exista, inicia com a tela inicial.

        if (getIntent().getExtras() != null) {
            extras = getIntent().getExtras();
            int openFragment = extras.getInt("nextScreen");
            if (openFragment == 6) {
                initFragment("Novo Agendamento", new CustScheduleDateFragmentPortrait_6());
            } else {
                initFragment("Meus Agendamentos", new CustScheduleListFragment_9());
            }
        } else {
            initFragment("Meus Agendamentos", new CustScheduleListFragment_9());
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

        if (!fragment.isVisible()) {
            //verifica se é diferente do fragment atual
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            extras.putSerializable(SessionAttributes.CUSTOMER, customerDTO);
            fragment.setArguments(extras);

            ft.replace(R.id.content_frame, fragment);
            setTitle(title);
            ft.commit();
        }
    }

    //Esta classe alimenta e configura o conteudo do drawer
    public void setDrawerLayoutItems() {
        //configurando itens de dentro do drawer

        //Configurando cabecalho
        String userName = customerDTO.getName();
        String userEmail = customerDTO.getEmail();

        //todo tratar photo assim q o cadastro comecar a gravar as photos
        ImageView imagePhoto = (ImageView) findViewById(R.id.photoDrawer_cust10);
        TextView nameTV = (TextView) findViewById(R.id.name_cust10);
        TextView emailTV = (TextView) findViewById(R.id.email_cust10);
        listOptions = (ListView) findViewById(R.id.listView_cust10);
        editProfileBT = (ImageButton) findViewById(R.id.ic_editProfile_cust10);

        nameTV.setText(userName);
        emailTV.setText(userEmail);

        //configurando listview (menu de opcoes)
        CustScheduleDateFragmentPortrait_6 custScheduleDateFragment = new CustScheduleDateFragmentPortrait_6();
        CustScheduleListFragment_9 custScheduleListFragment9 = new CustScheduleListFragment_9();

        DrawerMenuItem item1 = new DrawerMenuItem(custScheduleListFragment9, "Consultar Agendamentos", R.drawable.ic_drawer_consult_schedule);
        DrawerMenuItem item2 = new DrawerMenuItem(custScheduleDateFragment, "Novo Agendamento", R.drawable.ic_drawer_new_schedule);
        DrawerMenuItem[] menuOptions = {item1, item2};

        //configurando adapter da listView
        ListAdapter myAdapterDrawerOptions = new MyAdapterDrawerOptions(this, menuOptions);
        listOptions.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listOptions.setAdapter(myAdapterDrawerOptions);

        //criando listeners
        addListViewListener(menuOptions);
        addEditButtonListener();
    }

    private void addEditButtonListener() {
        //listener do botao de editprofile
        editProfileBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toEditProfileIntent = new Intent(CustDrawerMenu_10.this, CustProfile_5.class);
                startActivity(toEditProfileIntent);
            }
        });
    }

    private void addListViewListener(final DrawerMenuItem[] menuOptions) {
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
                    Intent i = new Intent(CustDrawerMenu_10.this, menuOptions[position].getLinkActivity().getClass());
                    i.putExtra(SessionAttributes.CUSTOMER, customerDTO);
                    startActivity(i);
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
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return true;
            case R.id.exit_the_app:
                exit_the_app();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
    // olhar depois c tem alguma soluçao mais elegantes
    public  void logout(){
        LocalLoginService localLoginService = new LocalLoginService(CustDrawerMenu_10.this);
        localLoginService.logoff();
        Intent createAccountIntent = new Intent(CustDrawerMenu_10.this, Login_1.class);
        startActivity(createAccountIntent);
    }

    private void exit_the_app() {
        System.exit(0);
    }

}
