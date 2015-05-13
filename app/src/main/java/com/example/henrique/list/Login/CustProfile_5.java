package com.example.henrique.list.Login;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henrique.list.Cliente.CustDrawerMenu_10;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.CustomerService;
import com.example.henrique.list.Service.URLConstants;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.motiserver.constants.Gender;
import br.com.motiserver.constants.PersonType;
import br.com.motiserver.constants.Status;
import br.com.motiserver.constants.UF;
import br.com.motiserver.dto.CustomerDTO;

/**
 * Created by Massaru on 03/04/2015.
 */
public class CustProfile_5 extends ActionBarActivity {

    //Constants
    static final int DATE_DIALOG_ID = 999;
    private static final String NOME_CTE = "NOME_CTE";
    private static final String CELULAR_CTE = "CELULAR_CTE";
    private static final String EMAIL_CTE = "EMAIL_CTE";
    private static final String CEP_CTE = "CEP_CTE";
    private static final String NUMERO_CTE = "NUMERO_CTE";
    private static final String RUA_CTE = "RUA_CTE";
    private static final String BAIRRO_CTE = "BAIRRO_CTE";
    private static final String CIDADE_CTE = "CIDADE_CTE";
    private static final String ESTADO_CTE = "ESTADO_CTE";
    private static final String GENERO_CTE = "GENERO_CTE";
    private static final String DATA_CTE = "DATA_CTE";

    //TextViews
    TextView dataEscolhidaTV;

    // inteiros
    private int year;
    private int month;
    private int day;

    //Inicializacao dos EditTexts Obrigatorios

    private Date chosenDate;
    private Gender opcaoEscolhidaGenero;
    RadioButton masculinoRB;
    RadioButton femininoRB;
    EditText nomeET;
    EditText celularET;
    EditText emailET;
    EditText CEPET;
    EditText numeroET;
    EditText ruaET;
    EditText bairroET;
    EditText cidadeET;
    EditText estadoET;

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
    String estado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cust_profile_5_11);

        //Verificando se está iniciando ou restaurando
        if ( savedInstanceState == null)
        {
            //significa que o APP está iniciando

        }else{
            //significa que o APP está restaurando
             nomeET.setText(savedInstanceState.getString(NOME_CTE));
             emailET.setText(savedInstanceState.getString(EMAIL_CTE));
             celularET.setText(savedInstanceState.getString(CELULAR_CTE));
             CEPET.setText(savedInstanceState.getString(CEP_CTE));
             numeroET.setText(savedInstanceState.getString(NUMERO_CTE));
             ruaET.setText(savedInstanceState.getString(RUA_CTE));
             bairroET.setText(savedInstanceState.getString(BAIRRO_CTE));
             cidadeET.setText(savedInstanceState.getString(CIDADE_CTE));
             estadoET.setText(savedInstanceState.getString(ESTADO_CTE));
//            opcaoEscolhidaGenero = savedInstanceState.(GENERO_CTE)
//            if(opcaoEscolhidaGenero == Gender.FEMALE)
        }

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //inflando as views

        //campos obrigatórios
        nomeET = (EditText) findViewById(R.id.NomeET_Cust_5);
        dataEscolhidaTV = (TextView) findViewById(R.id.dataEscolhidaTV_5);
        masculinoRB = (RadioButton) findViewById(R.id.masculinoCustRB_5);
        femininoRB = (RadioButton) findViewById(R.id.femininoCustRB_5);
        celularET = (EditText) findViewById(R.id.celularCustET_5);
        emailET = (EditText) findViewById(R.id.emailCustET_5);
        CEPET = (EditText) findViewById(R.id.CEPCustET_5);
        numeroET = (EditText) findViewById(R.id.numeroCustET_5);
        ruaET = (EditText) findViewById(R.id.RuaCustET_5);
        bairroET = (EditText) findViewById(R.id.bairroCustET_5);
        cidadeET = (EditText) findViewById(R.id.cidadeCustET_5);
        estadoET = (EditText) findViewById(R.id.estadoCustET_5);

        // Botoes nao obrigatorios
        complementoET = (EditText) findViewById(R.id.complementoCustET_5);
        imageButton = (ImageButton) findViewById(R.id.ImageButtonCust_5_11);

       // Objetos
        customerDTO = new CustomerDTO();

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

        //validacoes dos campos

        nome = nomeET.getText().toString();
        if (!isValidName(nome)) {
            nomeET.setError("O nome precisa conter no Minimo 3 letras");
            executaJSON = false;

        }else{
            if(nomeET.getError() != null)
                nomeET.setError(null);
        }


        email = emailET.getText().toString();
        if (!isValidEmail(email)) {
            emailET.setError("Email invalido");
            executaJSON = false;
        }
        else{
            if(emailET.getError() != null)
                emailET.setError(null);
        }

        celular = celularET.getText().toString();
        if (!isValidCelular(celular)) {
            celularET.setError("O celular precisa conter 9 dígitos.");
            executaJSON = false;
        }
        else{
            if(celularET.getError() != null)
                celularET.setError(null);
        }

        cep = CEPET.getText().toString();
        if (!isValidCEP(cep)) {
            CEPET.setError("O CEP precisa conter 8 dígitos.");
            executaJSON = false;
        }else{
            if(CEPET.getError() != null)
                CEPET.setError(null);
        }

        numero = numeroET.getText().toString();
        if (!isValidNumero(numero)) {
            numeroET.setError("O número não");
            executaJSON = false;
        }else{
            if(numeroET.getError() != null)
                numeroET.setError(null);
        }

        rua = ruaET.getText().toString();
        if (!isValidRua(rua)) {
            ruaET.setError("A rua não pode conter números.");
            executaJSON = false;
        }else{
            if(ruaET.getError() != null)
            ruaET.setError(null);
        }

        bairro = bairroET.getText().toString();
        if (!isValidBairro(bairro)) {
            bairroET.setError("O bairro não pode conter números.");
            executaJSON = false;
        }else{
            if(bairroET.getError() != null)
                bairroET.setError(null);
        }

        cidade = cidadeET.getText().toString();
        if (!isValidCidade(cidade)) {
            cidadeET.setError("A cidade não pode conter números.");
            executaJSON = false;
        }else{
            if(cidadeET.getError() != null)
                cidadeET.setError(null);
        }
        estado = estadoET.getText().toString();
        if (!isValidEstado(estado)) {
            estadoET.setError("O estado não pode conter números.");
            executaJSON = false;
        }else{
            if(estadoET.getError() != null)
                estadoET.setError(null);
        }

        if (!isValidNascimento(chosenDate)) {
            Toast.makeText(getApplicationContext(), "Por favor,escolha sua data de nascimento ", Toast.LENGTH_SHORT).show();
            executaJSON = false;

        }
        if (!isValidSexo(opcaoEscolhidaGenero)) {
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
            customerDTO.setAddressState(UF.SAO_PAULO);
            customerDTO.setGender(opcaoEscolhidaGenero);
            Calendar dg = Calendar.getInstance();
            customerDTO.setUpdateDate(dg.getTime());

            //campos nao obrigatorios

            customerDTO.setAddressComplement(complementoET.getText().toString());

            // campos nao essenciais ao MVP
            customerDTO.setStatus(Status.TRUE);
            customerDTO.setCpfCnpj("nulo");
            customerDTO.setPhoneCode("11");
            customerDTO.setLogin("definirNaTelaLogin");
            customerDTO.setFacebookLogin("NaoEssencial");
            customerDTO.setGoogleLogin("NaoEssencial");
            customerDTO.setPassword("definirNaTelaLogin");

            // executa requisição JSON
            try {
                new HttpRequestTask().execute(customerDTO);
                System.out.println("Alcançou Json Com sucesso");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Falha ao executar JSON");
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
            case R.id.masculinoCustRB_5:
                if (checked)
                    opcaoEscolhidaGenero = Gender.MALE;
                break;
            case R.id.femininoCustRB_5:
                if (checked)
                    opcaoEscolhidaGenero = Gender.FEMALE;
                break;
        }
    }

    //Metodos Relacionados ao Date Picker

    public void addListenerOnButton() {
        dataEscolhidaTV = (TextView) findViewById(R.id.dataEscolhidaTV_5);
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
            chosenDate = myCal.getTime();

            // coloca data selecionada dentro do TextView correspondente
            dataEscolhidaTV.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));
        }
    };

    //Métodos relacionados ao JSON

    private class HttpRequestTask extends AsyncTask<CustomerDTO, Void, CustomerDTO> {
        @Override
        protected CustomerDTO doInBackground(CustomerDTO... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                customerDTO = restTemplate.postForObject(URLConstants.JSON_SERVER_URL +
                        URLConstants.CUSTOMER_SAVE, customerDTO, CustomerDTO.class);
                System.out.println("conectou");
                return customerDTO;
            } catch (Exception e) {
                System.out.println("nao conectou");
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(CustomerDTO Result) {
            super.onPostExecute(Result);
            // tela de carregamento
            try {
                if (Result != null) {
                    Intent i = new Intent(CustProfile_5.this, CustDrawerMenu_10.class);
                    startActivity(i);
                } else {
                    System.out.println("Nao conseguiu fazer post execute( mudar de tela");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    Metodos de validacao de dados

    private boolean isValidName(String nome) {

        if (nome.equals("")) {

            return false;
        }
        else{
            if(nome.length() > 60){
                return false;
            }
            else{
                String NAME_PATTERN = "^[_A-Za-z\\+]{3,15}+((\\s[_A-Za-z]+)*)$";

                Pattern pattern = Pattern.compile(NAME_PATTERN);
                Matcher matcher = pattern.matcher(nome);
                return matcher.matches();
            }
        }
    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isValidCelular(String celular) {
        if (celular.equals("") || celular.length() > 9) {
            return false;
        }else  if(celular.length()==9)
        {
            return true;
        }
            return false;

    }
    private boolean isValidCEP(String cep) {
        if (cep.equals("") || cep.length() > 8 ) {
            return false;
        }
        else if(cep.length()==8){
            return true;
        }
        return false;
    }
    private boolean isValidNumero(String numero) {
        if (numero.equals("") || numero.length() > 9) {
            return false;
        }
        else{
            return true;
        }
    }
    private boolean isValidEstado(String estado) {
        if (estado.equals("")) {
            return false;
        }
        else{
            if(estado.length() > 2){
                return false;
            }
            else{
                String STATE_PATTERN = "^[a-z]{1,2}$";

                Pattern pattern = Pattern.compile(STATE_PATTERN);
                Matcher matcher = pattern.matcher(estado);
                return matcher.matches();
            }
        }
    }
    private boolean isValidCidade(String cidade) {
        if (cidade.equals("")) {
            return false;
        }
        else{
            if(cidade.length() > 40){
                return false;
            }
            else{
                String CITY_NAME = "^[_A-Za-z\\+]{3,15}+((\\s[_A-Za-z]+)*)$";

                Pattern pattern = Pattern.compile(CITY_NAME);
                Matcher matcher = pattern.matcher(cidade);
                return matcher.matches();
            }
        }
    }
    private boolean isValidBairro(String bairro) {
        if (bairro.equals("")) {
            return false;
        }
        else{
            if(bairro.length() > 60){
                return false;
            }
            else{
                String DISTRICT_NAME = "^[_A-Za-z\\+]{3,15}+((\\s[_A-Za-z]+)*)$";

                Pattern pattern = Pattern.compile(DISTRICT_NAME);
                Matcher matcher = pattern.matcher(bairro);
                return matcher.matches();            }
        }
    }
    private boolean isValidRua(String rua) {
        if (rua.equals("")) {
            return false;
        }
        else{
            if(rua.length() > 60){
                return false;
            }
            else{
                String STREET_NAME = "^[_A-Za-z\\+]{3,15}+((\\s[_A-Za-z]+)*)$";

                Pattern pattern = Pattern.compile(STREET_NAME);
                Matcher matcher = pattern.matcher(rua);
                return matcher.matches();             }
        }

    }
    private boolean isValidNascimento(Date chosenDate){
        if(chosenDate==null){
            return false;
        }
        else{
            return true;
        }
    }
    private Boolean isValidSexo(Gender opcaoEscolhidaGenero){
        if(opcaoEscolhidaGenero==null){
            return false;
        }
        else{
            return true;
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NOME_CTE, nome);
        outState.putString(EMAIL_CTE, email);
        outState.putString(CELULAR_CTE, celular);
        outState.putString(CEP_CTE, cep);
        outState.putString(NUMERO_CTE, numero);
        outState.putString(CIDADE_CTE, cidade);
        outState.putString(ESTADO_CTE, estado);
    }

    }
