package com.example.henrique.list.Login;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
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

import com.example.henrique.list.Cliente.CustDrawerMenu_10;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.CustomerService;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;
import com.example.henrique.list.Utilidade_Publica.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    ArrayAdapter<String> stateAdapter;

    //Inicializacao dos EditTexts Obrigatorios
    Calendar chosenDateCal;
    Calendar onScreenCal = Calendar.getInstance();
    Calendar dg = Calendar.getInstance();
    Gender opcaoEscolhidaGenero;
    RadioButton masculinoRB;
    RadioButton femininoRB;
    EditText nomeET;
    EditText dataET;
    EditText prefixET;
    EditText celularET;
    EditText emailET;
    EditText CEPET;
    EditText numeroET;
    EditText ruaET;
    EditText bairroET;
    EditText cidadeET;
    Spinner estadoSP;

    //Inicializacao dos EditTexts Nao Obrigatorios
    EditText complementoET;

    //botoes
    ImageButton imageButton;

    //objetos
    CustomerDTO customerDTO;

    //boolean
    boolean isAllValidate;

    //    armazenadores
    String nome;
    String email;
    String prefix;
    String celular;
    String cep;
    String numero;
    String rua;
    String bairro;
    String cidade;
    UF estado;

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
        setSpinnerItems();
        // Objetos
        customerDTO = (CustomerDTO) getIntent().getSerializableExtra(SessionAttributes.CUSTOMER);
        customerService = new CustomerService();
//        Booleans
        isAllValidate = true;
        addDateListenerButton();

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
                if (validateFields()) {
                    executeJSON();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private boolean validateFields() {
        //retorna verdadeiro se todos campos forem validos
        isAllValidate = true;
        nome = nomeET.getText().toString();
        if (!Utility.isValidName(nome)) {
            nomeET.setError("O nome precisa conter no mínimo 3 letras e conter caracteres válidos");
            isAllValidate = false;

        } else {
            if (nomeET.getError() != null)
                nomeET.setError(null);
        }


        email = emailET.getText().toString();
        if (!Utility.isValidEmail(email)) {
            emailET.setError("Email invalido");
            isAllValidate = false;
        } else {
            if (emailET.getError() != null)
                emailET.setError(null);
        }

        prefix = prefixET.getText().toString();
        if (!Utility.isValidPrefix(prefix)) {
            prefixET.setError("O prefixo precisa conter 2 digitos");
            isAllValidate = false;
        }

        celular = celularET.getText().toString();
        if (!Utility.isValidCelular(celular)) {
            celularET.setError("O celular precisa conter 9 dígitos.");
            isAllValidate = false;
        } else {
            if (celularET.getError() != null)
                celularET.setError(null);
        }

        cep = CEPET.getText().toString();
        if (!Utility.isValidCEP(cep)) {
            CEPET.setError("O CEP precisa conter 8 dígitos.");
            isAllValidate = false;
        } else {
            if (CEPET.getError() != null)
                CEPET.setError(null);
        }

        numero = numeroET.getText().toString();
        if (!Utility.isValidNumero(numero)) {
            numeroET.setError("O número não é válido");
            isAllValidate = false;
        } else {
            if (numeroET.getError() != null)
                numeroET.setError(null);
        }

        rua = ruaET.getText().toString();
        if (!Utility.isValidTextWithSpace(rua)) {
            //todo rua pode conter digitos sim
            ruaET.setError("A rua não pode conter números.");
            isAllValidate = false;
        } else {
            if (ruaET.getError() != null)
                ruaET.setError(null);
        }

        bairro = bairroET.getText().toString();
        if (!Utility.isValidBairro(bairro)) {
            //todo bairro pode conter digitos sim
            bairroET.setError("O bairro não pode conter números.");
            isAllValidate = false;
        } else {
            if (bairroET.getError() != null)
                bairroET.setError(null);
        }


        cidade = cidadeET.getText().toString();
        if (!Utility.isValidCidade(cidade)) {
            cidadeET.setError("A cidade n�o pode conter n�meros.");
            isAllValidate = false;
        } else {
            if (cidadeET.getError() != null)
                cidadeET.setError(null);
        }

        if (!Utility.isValidNascimento(chosenDateCal)) {
            dataET.setError("Selecione a data de nascimento");
            isAllValidate = false;
        } else {
            if (dataET.getError() != null)
                dataET.setError(null);
        }

        if (!Utility.isValidSexo(opcaoEscolhidaGenero)) {
            masculinoRB.setTextColor(Color.RED);
            femininoRB.setTextColor(Color.RED);
            isAllValidate = false;
        } else {
            masculinoRB.setTextColor(getResources().getColor(R.color.black));
            femininoRB.setTextColor(getResources().getColor(R.color.black));
        }
        if (estadoSP.getSelectedItemPosition() == stateAdapter.getCount() - 1) {
            TextView stateHint = (TextView) estadoSP.getSelectedView().findViewById(android.R.id.text1);
            stateHint.setTextColor(Color.RED);
        }
        return isAllValidate;
    }

    public void executeJSON() {
        //executa o JSON
        customerDTO = new CustomerDTO();
        estado = UF.getEnumFromValue((String) estadoSP.getSelectedItem());
        //campos obrigatorios ao MVP

        customerDTO.setName(nome);
        customerDTO.setEmail(email);
        customerDTO.setBirthDate(chosenDateCal);
        customerDTO.setPhoneCode(prefix);
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
    public void addDateListenerButton() {
        dataET.setOnClickListener(new View.OnClickListener() {
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
                        onScreenCal.get(Calendar.YEAR), onScreenCal.get(Calendar.MONTH), onScreenCal.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    public DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            // coloca o resultado dentro de uma variavel do tipo date
            Calendar myCal = Calendar.getInstance();
            myCal.set(Calendar.YEAR, selectedYear);
            myCal.set(Calendar.MONTH, selectedMonth);
            myCal.set(Calendar.DAY_OF_MONTH, selectedDay);
            chosenDateCal = myCal;

            // coloca data selecionada dentro do TextView correspondente
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String sDate = sdf.format(onScreenCal.getTime());
            dataET.setText(sDate);
        }
    };

    //configura spinner da tela
    private void setSpinnerItems() {

        ArrayList<String> stateSpinnerItens = new ArrayList<>();

        for (UF state : UF.values()) {
            stateSpinnerItens.add(state.getCode());
        }
        stateSpinnerItens.add("UF");
        stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stateSpinnerItens);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoSP.setAdapter(stateAdapter);
        estadoSP.setSelection(stateAdapter.getCount() - 1);
    }

    private void initViews() {
        nomeET = (EditText) findViewById(R.id.NomeET_Cust_5);
        masculinoRB = (RadioButton) findViewById(R.id.masculinoCustRB_5);
        femininoRB = (RadioButton) findViewById(R.id.femininoCustRB_5);
        prefixET = (EditText) findViewById(R.id.prefixCustET_5);
        celularET = (EditText) findViewById(R.id.celularCustET_5);
        dataET = (EditText) findViewById(R.id.dataEscolhidaCustTV_5);
        emailET = (EditText) findViewById(R.id.emailCustET_5);
        CEPET = (EditText) findViewById(R.id.CEPCustET_5);
        numeroET = (EditText) findViewById(R.id.numeroCustET_5);
        ruaET = (EditText) findViewById(R.id.AddressCustET_5);
        bairroET = (EditText) findViewById(R.id.bairroCustET_5);
        cidadeET = (EditText) findViewById(R.id.cidadeCustSP_5);
        estadoSP = (Spinner) findViewById(R.id.estadoCustSP_5);

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
