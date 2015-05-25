package com.example.henrique.list.Login;

import android.app.DatePickerDialog;
import android.app.Dialog;
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

import com.example.henrique.list.R;
import com.example.henrique.list.Service.ProfessionService;
import com.example.henrique.list.Service.ProfessionalService;
import com.example.henrique.list.Utilidade_Publica.ServiceException;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;
import com.example.henrique.list.Utilidade_Publica.Utility;

import java.util.Calendar;
import java.util.List;

import br.com.motiserver.constants.Gender;
import br.com.motiserver.constants.Status;
import br.com.motiserver.constants.UF;
import br.com.motiserver.dto.ProfessionDTO;
import br.com.motiserver.dto.ProfessionalDTO;


//TODO IsEDiting
/**
 * Created by Massaru on 03/04/2015.
 */
public class ProProfile_5 extends ActionBarActivity {

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
//    private static final String PROFISSIONAL_CTE = "PROFISSIONAL_CTE";
//
//
//    //TODO: fazer recuperacao de data e Genero
//
//    private static final String GENERO_CTE = "GENERO_CTE";
//    private static final String DATA_CTE = "DATA_CTE";

    private ProfessionalService professionalService;
    private ProfessionService professionService;

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
    EditText cidadeET;
    Spinner estadoSP;
    Spinner profissaoSP;

    //Inicializacao dos EditTexts Nao Obrigatorios
    EditText complementoET;

    //botoes
    ImageButton imageButton;

    //objetos
    ProfessionalDTO professionalDTO;

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
    String profissao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_profile_5);

        //Verificando se esta iniciando ou restaurando
        if ( savedInstanceState == null)
        {
            //significa que o APP esta iniciando

        }else{
            //significa que o APP esta restaurando
//            nomeET.setText(savedInstanceState.getString(NOME_CTE));
//            emailET.setText(savedInstanceState.getString(EMAIL_CTE));
//            celularET.setText(savedInstanceState.getString(CELULAR_CTE));
//            CEPET.setText(savedInstanceState.getString(CEP_CTE));
//            numeroET.setText(savedInstanceState.getString(NUMERO_CTE));
//            ruaET.setText(savedInstanceState.getString(RUA_CTE));
//            bairroET.setText(savedInstanceState.getString(BAIRRO_CTE));
//            cidadeET.setText(savedInstanceState.getString(CIDADE_CTE));
//            opcaoEscolhidaGenero = savedInstanceState.(GENERO_CTE)
//            if(opcaoEscolhidaGenero == Gender.FEMALE)
        }

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

/*
        inflando as views
*/

        //campos obrigat�rios
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
        cidadeET = (EditText) findViewById(R.id.cidadeProSP_5);
        estadoSP = (Spinner) findViewById(R.id.estadoProSP_5);
        profissaoSP = (Spinner) findViewById(R.id.profissaoProSP_5);

        List<ProfessionDTO> professions = null;
        try {
            professionService = new ProfessionService();
            professions = professionService.findAll();

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        ArrayAdapter<ProfessionDTO> professionAdapter = new  ArrayAdapter<ProfessionDTO>(this, android.R.layout.simple_spinner_item , professions );
        professionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profissaoSP.setAdapter(professionAdapter);

        ArrayAdapter<UF> stateAdapter = new  ArrayAdapter<UF>(this, android.R.layout.simple_spinner_item , UF.values() );
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoSP.setAdapter(stateAdapter);

        // campos nao obrigatorios
        complementoET = (EditText) findViewById(R.id.complementoProET_5);
        imageButton = (ImageButton) findViewById(R.id.ImageButtonPro_5);

        // Objetos
        professionalService = new ProfessionalService();
        professionalDTO = (ProfessionalDTO) getIntent().getSerializableExtra(SessionAttributes.PROFESSIONAL);

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

        professionalDTO = new ProfessionalDTO();
        executaJSON = true;

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
            celularET.setError("O celular precisa conter 9 d�gitos.");
            executaJSON = false;
        }
        else{
            if(celularET.getError() != null)
                celularET.setError(null);
        }

        cep = CEPET.getText().toString();
        if (!Utility.isValidCEP(cep)) {
            CEPET.setError("O CEP precisa conter 8 d�gitos.");
            executaJSON = false;
        }else{
            if(CEPET.getError() != null)
                CEPET.setError(null);
        }

        numero = numeroET.getText().toString();
        if (!Utility.isValidNumero(numero)) {
            numeroET.setError("O n�mero n�o");
            executaJSON = false;
        }else{
            if(numeroET.getError() != null)
                numeroET.setError(null);
        }

        rua = ruaET.getText().toString();
        if (!Utility.isValidTextWithSpace(rua)) {
            ruaET.setError("A rua n�o pode conter n�meros.");
            executaJSON = false;
        }else{
            if(ruaET.getError() != null)
                ruaET.setError(null);
        }

        bairro = bairroET.getText().toString();
        if (!Utility.isValidBairro(bairro)) {
            bairroET.setError("O bairro n�o pode conter n�meros.");
            executaJSON = false;
        }else{
            if(bairroET.getError() != null)
                bairroET.setError(null);
        }

        cidade = cidadeET.getText().toString();
        if (!Utility.isValidCidade(cidade)) {
            cidadeET.setError("A cidade n�o pode conter n�meros.");
            executaJSON = false;
        }else{
            if(cidadeET.getError() != null)
                cidadeET.setError(null);
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

            professionalDTO.setName(nome);
            professionalDTO.setEmail(email);
            professionalDTO.setBirthDate(chosenDate);
            professionalDTO.setPhoneNumber(celular);
            professionalDTO.setAddressStreet(rua);
            professionalDTO.setAddressDistrict(bairro);
            professionalDTO.setAddressCity(cidade);
            professionalDTO.setAddressNumber(numero);
            professionalDTO.setAddressZipCode(cep);
            estado = (UF) estadoSP.getSelectedItem();
            professionalDTO.setAddressState(estado);
            professionalDTO.setGender(opcaoEscolhidaGenero);
            professionalDTO.setUpdateDate(dg);
            //profession DTO
            ProfessionDTO professionDTO =(ProfessionDTO) profissaoSP.getSelectedItem();
            professionalDTO.setProfession(professionDTO);
            professionalDTO.setRegistry("1234225");  //TODO colocar um campo na tela
            //campos nao obrigatorios

            professionalDTO.setAddressComplement(complementoET.getText().toString());

            // campos nao essenciais ao MVP
            professionalDTO.setStatus(Status.TRUE);
            professionalDTO.setCpfCnpj("nulo");
            professionalDTO.setPhoneCode("11");
            professionalDTO.setLogin("definirNaTelaLogin");
            professionalDTO.setFacebookLogin("NaoEssencial");
            professionalDTO.setGoogleLogin("NaoEssencial");
            professionalDTO.setPassword("definirNaTelaLogin");

            // executa requisi��o JSON
            try {
                ProfessionalService professionalService = new ProfessionalService();
                professionalDTO =professionalService.save(professionalDTO);
                if (professionalDTO == null) {
                    System.out.println("=== DEU ERRO E O PROFISSIONAl RETORNO NULLO");
                } else {
                    System.out.println("=== DEU CERTO E O PROFISSIONAl RETORNOU COM SUCESSO " + professionalDTO.getName());  //TODO verificar se o back adiciona o id no objeto de retorno

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Falha ao executar JSON");
            }

        } else {
            Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos obrigat�rios", Toast.LENGTH_SHORT).show();
        }
    }

    //gerencia a manipulacao de Radio Buttons

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



    // em caso de restauracao
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
