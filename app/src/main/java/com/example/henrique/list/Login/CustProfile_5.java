package com.example.henrique.list.Login;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henrique.list.Cliente.CustDrawerMenu_10;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.CustomerService;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;
import com.example.henrique.list.Utilidade_Publica.Utility;

import java.io.Serializable;
import java.util.Calendar;

import br.com.motiserver.constants.Gender;
import br.com.motiserver.constants.Status;
import br.com.motiserver.constants.UF;
import br.com.motiserver.dto.CustomerDTO;
//TODO IsEDiting

/**
 * Created by Massaru on 03/04/2015.
 */
public class CustProfile_5 extends ActionBarActivity {

//    //Constants
    static final int DATE_DIALOG_ID = 999;
//    private static final String NOME_CTE = "NOME_CTE";
//    private static final String CELULAR_CTE = "CELULAR_CTE";
//    private static final String EMAIL_CTE = "EMAIL_CTE";
//    private static final String CEP_CTE = "CEP_CTE";
//    private static final String NUMERO_CTE = "NUMERO_CTE";
//    private static final String RUA_CTE = "RUA_CTE";
//    private static final String BAIRRO_CTE = "BAIRRO_CTE";
//    private static final String CIDADE_CTE = "CIDADE_CTE";
//    private static final String ESTADO_CTE = "ESTADO_CTE";
//
//    //TODO: fazer recuperaçao de data e Genero
//
//    private static final String GENERO_CTE = "GENERO_CTE";
//    private static final String DATA_CTE = "DATA_CTE";

    private CustomerService customerService;

    //TextViews
    TextView dataEscolhidaTV;

    // inteiros
    private int year;
    private int month;
    private int day;

    //Inicializacao dos EditTexts Obrigatorios
    Calendar chosenDate;//TODO arrumar direito
    Calendar dg = Calendar.getInstance();
    Gender opcaoEscolhidaGenero;
    RadioButton masculinoRB;
    RadioButton femininoRB;
    EditText nomeET;
    EditText celularET;
    EditText emailET;
    EditText CEPET;
    EditText numeroET;
    EditText ruaET;
    EditText bairroET;
    Spinner cidadeSP;
    Spinner estadoSP;

    //Inicializacao dos EditTexts Nao Obrigatorios
    EditText complementoET;

    //botoes
    ImageButton imageButton;

    //objetos
    CustomerDTO customerDTO;

    //boolean
    boolean executaJSON;

//    armazenadores
    String nome;
    String email;
    String celular;
    String cep;
    String numero;
    String rua;
    String bairro;
    String cidade;
    UF estado;

    Utility utility ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_profile_5);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //inflando as views

        //campos obrigatórios
        initViews();
        //Inicializa Adapters
        initSpinnerAdapters();
       // Objetos
        customerDTO = (CustomerDTO) getIntent().getSerializableExtra(SessionAttributes.CUSTOMER);
        customerService = new CustomerService();
//        Booleans
        executaJSON = true;
        addListenerOnButton();

    }

    //aqui inicializamos os botoes da action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_confirm, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // On selecting action bar icons

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.confirmButton:
                // location found
                confirmRegistration();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void confirmRegistration() {

        customerDTO = new CustomerDTO();
        executaJSON = true;

        estado = (UF) estadoSP.getSelectedItem();
        cidade = (String) cidadeSP.getSelectedItem();


        //validacoes dos campos

        nome = nomeET.getText().toString();
        if (!Utility.isValidName(nome)) {
            nomeET.setError("O nome precisa conter no Minimo 3 letras");
            executaJSON = false;

        }else{
            if(nomeET.getError() != null)
                nomeET.setError(null);
        }


        email = emailET.getText().toString();
        if (!Utility.isValidEmail(email)) {
            emailET.setError("Email invalido");
            executaJSON = false;
        }
        else{
            if(emailET.getError() != null)
                emailET.setError(null);
        }

        celular = celularET.getText().toString();
        if (!Utility.isValidCelular(celular)) {
            celularET.setError("O celular precisa conter 9 dígitos.");
            executaJSON = false;
        }
        else{
            if(celularET.getError() != null)
                celularET.setError(null);
        }

        cep = CEPET.getText().toString();
        if (!Utility.isValidCEP(cep)) {
            CEPET.setError("O CEP precisa conter 8 dígitos.");
            executaJSON = false;
        }else{
            if(CEPET.getError() != null)
                CEPET.setError(null);
        }

        numero = numeroET.getText().toString();
        if (!Utility.isValidNumero(numero)) {
            numeroET.setError("O número não");
            executaJSON = false;
        }else{
            if(numeroET.getError() != null)
                numeroET.setError(null);
        }

        rua = ruaET.getText().toString();
        if (!Utility.isValidTextWithSpace(rua)) {
            ruaET.setError("A rua não pode conter números.");
            executaJSON = false;
        }else{
            if(ruaET.getError() != null)
            ruaET.setError(null);
        }

        bairro = bairroET.getText().toString();
        if (!Utility.isValidBairro(bairro)) {
            bairroET.setError("O bairro não pode conter números.");
            executaJSON = false;
        }else{
            if(bairroET.getError() != null)
                bairroET.setError(null);
        }

        if (!Utility.isValidNascimento(chosenDate)) {
            Toast.makeText(getApplicationContext(), "Por favor,escolha sua data de nascimento ", Toast.LENGTH_SHORT).show();
            executaJSON = false;

        }
        if (!Utility.isValidSexo(opcaoEscolhidaGenero)) {
            Toast.makeText(getApplicationContext(), "Por favor,por favor escolha seu genero ", Toast.LENGTH_SHORT).show();
            executaJSON = false;

        }


        if (executaJSON) {

            //campos obrigatorios ao MVP

            customerDTO.setName(nome);
            customerDTO.setEmail(email);
            customerDTO.setBirthDate(chosenDate);
            customerDTO.setPhoneNumber(celular);
            customerDTO.setAddressStreet(rua);
            customerDTO.setAddressDistrict(bairro);
            customerDTO.setAddressCity(cidade);
            customerDTO.setAddressNumber(numero);
            customerDTO.setAddressZipCode(cep);
            customerDTO.setAddressState(estado);
            customerDTO.setGender(opcaoEscolhidaGenero);
            customerDTO.setUpdateDate(dg);

            //campos nao obrigatorios

            customerDTO.setAddressComplement(complementoET.getText().toString());

            // campos nao essenciais ao MVP
            customerDTO.setStatus(Status.TRUE);
            customerDTO.setCpfCnpj("nulo");
            customerDTO.setPhoneCode("11");
            customerDTO.setFacebookLogin("NaoEssencial");
            customerDTO.setGoogleLogin("NaoEssencial");

            // executa requisição JSON
            try {
                customerDTO = customerService.save(customerDTO);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Falha ao executar JSON");
            }
            if (customerDTO == null) {
                System.out.println("=== DEU ERRO E O CLIENTE RETORNO NULLO");
            } else {
                System.out.println("=== DEU CERTO E O CLIENTE RETORNOU COM SUCESSO " + customerDTO.getName());  //TODO verificar se o back adiciona o id no objeto de retorno
                Intent intent = new Intent(CustProfile_5.this, CustDrawerMenu_10.class);
                intent.putExtra(SessionAttributes.CUSTOMER, customerDTO);
                startActivity(intent);
            }

        } else {
            Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show();
        }
    }

    //gerencia a manipulação de Radio Buttons

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.masculinoProRB_5:
                if (checked)
                    opcaoEscolhidaGenero = Gender.MALE;
                break;
            case R.id.femininoProRB_5:
                if (checked)
                    opcaoEscolhidaGenero = Gender.FEMALE;
                break;
        }
    }

    //Metodos Relacionados ao Date Picker

    public void addListenerOnButton() {
        dataEscolhidaTV = (TextView) findViewById(R.id.dataEscolhidaProTV_5);
        dataEscolhidaTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month, day);
        }
        return null;
    }

    public DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            // coloca o resultado dentro de uma variavel do tipo date
            Calendar myCal = Calendar.getInstance();
            myCal.set(Calendar.YEAR, year);
            myCal.set(Calendar.MONTH, month);
            myCal.set(Calendar.DAY_OF_MONTH, day);
            chosenDate = myCal;

            // coloca data selecionada dentro do TextView correspondente
            dataEscolhidaTV.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));
        }
    };

    private void initSpinnerAdapters() {
        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this, R.array.cidades , android.R.layout.simple_spinner_item );
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cidadeSP.setAdapter(cityAdapter);

        ArrayAdapter<UF> stateAdapter = new  ArrayAdapter<UF>(this, android.R.layout.simple_spinner_item , UF.values() );
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoSP.setAdapter(stateAdapter);
    }

    private void initViews(){
        nomeET = (EditText) findViewById(R.id.NomeET_Pro_5);
        dataEscolhidaTV = (TextView) findViewById(R.id.dataEscolhidaProTV_5);
        masculinoRB = (RadioButton) findViewById(R.id.masculinoProRB_5);
        femininoRB = (RadioButton) findViewById(R.id.femininoProRB_5);
        celularET = (EditText) findViewById(R.id.celularProET_5);
        emailET = (EditText) findViewById(R.id.emailProET_5);
        CEPET = (EditText) findViewById(R.id.CEPProET_5);
        numeroET = (EditText) findViewById(R.id.numeroProET_5);
        ruaET = (EditText) findViewById(R.id.AddressProET_5);
        bairroET = (EditText) findViewById(R.id.bairroProET_5);
        cidadeSP = (Spinner) findViewById(R.id.cidadeProSP_5);
        estadoSP = (Spinner) findViewById(R.id.estadoProSP_5);

        // Botoes nao obrigatorios
        complementoET = (EditText) findViewById(R.id.complementoProET_5);
        imageButton = (ImageButton) findViewById(R.id.ImageButtonPro_5);
    }

        // em caso de restauração
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString(NOME_CTE, nome);
//        outState.putString(EMAIL_CTE, email);
//        outState.putString(CELULAR_CTE, celular);
//        outState.putString(CEP_CTE, cep);
//        outState.putString(NUMERO_CTE, numero);
//        outState.putString(CIDADE_CTE, cidade);
//    }

    }
