package com.example.henrique.list.Profissional;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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

import com.example.henrique.list.R;
import com.example.henrique.list.Service.CustomerService;
import com.example.henrique.list.Utilidade_Publica.DataValidatorUtil;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.util.constants.Gender;
import br.com.motiserver.util.constants.Status;
import br.com.motiserver.util.constants.UF;


//TODO IsEDiting


public class ProScheduleFirstActivity_11 extends ActionBarActivity {

    //    //Constants
    static final int DATE_DIALOG_ID = 999;
//
//
//    //TODO: fazer recuperacao de data e Genero
//


    private CustomerService customerService;

    ArrayAdapter<String> stateAdapter;


    //Inicializacao dos EditTexts Obrigatorios


    Calendar chosenDateCal;
    Calendar onScreenCal = Calendar.getInstance();
    Calendar dg = Calendar.getInstance();
    Gender opcaoEscolhidaGenero;
    RadioButton masculinoRB, femininoRB;
    EditText nomeET, dateET, celularET, prefixET, emailET;
    EditText CEPET, numeroET, ruaET, bairroET, cidadeET;
    Spinner estadoSP, profissaoSP;
    EditText complementoET;

    //botoes
    ImageButton imageButton;

    //objetos
    ProfessionalDTO professionalDTO;
    CustomerDTO customerDTO;

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
    String profissao;
    boolean isEditing;

    //requestCodes
    private static final int SELECT_PHOTO = 100;
    private static final int CROP_PHOTO = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_schedule_first_11);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        customerDTO = new CustomerDTO();





        // Objetos

        initViews();
        setSpinnerItems();

        //variavle de teste


        addDateListenerButton();
        addPhotoListenerButton();

    }

    private void initViews() {
        //campos obrigat�rios
        nomeET = (EditText) findViewById(R.id.NomeET_Pro_11);
        dateET = (EditText) findViewById(R.id.dataEscolhidaProTV_11);
        masculinoRB = (RadioButton) findViewById(R.id.masculinoProRB_11);
        femininoRB = (RadioButton) findViewById(R.id.femininoProRB_11);
        celularET = (EditText) findViewById(R.id.celularProET_11);
        prefixET = (EditText) findViewById(R.id.prefixProET_11);
        emailET = (EditText) findViewById(R.id.emailProET_11);
        CEPET = (EditText) findViewById(R.id.CEPProET_11);
        numeroET = (EditText) findViewById(R.id.numeroProET_11);
        ruaET = (EditText) findViewById(R.id.AddressProET_11);
        bairroET = (EditText) findViewById(R.id.bairroProET_11);
        cidadeET = (EditText) findViewById(R.id.celularProET_11);
        estadoSP = (Spinner) findViewById(R.id.estadoProSP_11);

        // campos nao obrigatorios
        complementoET = (EditText) findViewById(R.id.complementoProET_11);
        imageButton = (ImageButton) findViewById(R.id.ImageButtonPro_11);
    }

    //gerencia a manipulacao de Radio Buttons
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.masculinoProRB_11:
                if (checked)
                    opcaoEscolhidaGenero = Gender.MALE;
                break;
            case R.id.femininoProRB_11:
                if (checked)
                    opcaoEscolhidaGenero = Gender.FEMALE;
                break;
        }
    }

    //configura spinners da tela
    private void setSpinnerItems() {
        ArrayList<String> stateSpinnerItens = new ArrayList<>();

        for (UF state : UF.values()) {
            stateSpinnerItens.add(state.getCode());
        }
        //adicionando hint aos spinners
        stateSpinnerItens.add("UF");

        stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stateSpinnerItens);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoSP.setAdapter(stateAdapter);
        estadoSP.setSelection(stateAdapter.getCount() - 1);
    }

    //Metodos Relacionados ao Date Picker
    public void addDateListenerButton() {
        dateET.setOnClickListener(new View.OnClickListener() {
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
            onScreenCal.set(Calendar.YEAR, selectedYear);
            onScreenCal.set(Calendar.MONTH, selectedMonth);
            onScreenCal.set(Calendar.DAY_OF_MONTH, selectedDay);
            chosenDateCal = onScreenCal;
            // coloca data selecionada dentro do TextView correspondente
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String sDate = sdf.format(onScreenCal.getTime());
            dateET.setText(sDate);
        }
    };


    //adiciona listener de selecao de foto
    private void addPhotoListenerButton() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickImageIntent = new Intent(Intent.ACTION_PICK);
                pickImageIntent.setType("image/*");
                startActivityForResult(pickImageIntent, SELECT_PHOTO);
            }
        });
    }

    @Override
    //verifica retorno de ActivityForResult
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = intent.getData();
                    if (selectedImage != null) {
                        Log.i("TAG", "Start Crop!");
                        crop(selectedImage);
                    }

                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        Bitmap cropedBmp = extras.getParcelable("data");
                        BitmapDrawable myDrawable = new BitmapDrawable(getResources(), cropedBmp);

                        //todo verificar versao de uso para poder usar setBackground()
                        imageButton.setBackgroundDrawable(myDrawable);
                    }

                }
                break;
        }
    }

    private void crop(Uri photoUri) {
        //metodo q chama funcao para recortar imagem
        //todo criar uma classe para tratemnto de imagens
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setData(photoUri);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_PHOTO);
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

        boolean isAllValidate = true;

        //validacoes dos campos
        nome = nomeET.getText().toString();
        if (!DataValidatorUtil.isValidTextWithSpace(nome)) {
            nomeET.setError("O nome precisa conter no mínimo 3 letras e conter caracteres válidos");
            isAllValidate = false;

        } else {
            if (nomeET.getError() != null)
                nomeET.setError(null);
        }


        email = emailET.getText().toString();
        if (!DataValidatorUtil.isValidEmail(email)) {
            emailET.setError("Email invalido");
            isAllValidate = false;
        } else {
            if (emailET.getError() != null)
                emailET.setError(null);
        }
        prefix = prefixET.getText().toString();
        if (!DataValidatorUtil.isValidPrefix(prefix)) {
            prefixET.setError("O prefixo precisa conter 2 digitos");
            isAllValidate = false;
        }

        celular = celularET.getText().toString();
        if (!DataValidatorUtil.isValidCelular(celular)) {
            celularET.setError("O celular precisa conter 9 d�gitos.");
            isAllValidate = false;
        } else {
            if (celularET.getError() != null)
                celularET.setError(null);
        }

        cep = CEPET.getText().toString();
        if (!DataValidatorUtil.isValidCEP(cep)) {
            CEPET.setError("O CEP precisa conter 8 d�gitos.");
            isAllValidate = false;
        } else {
            if (CEPET.getError() != null)
                CEPET.setError(null);
        }

        numero = numeroET.getText().toString();
        if (!DataValidatorUtil.isValidNumero(numero)) {
            numeroET.setError("O n�mero não é válido");
            isAllValidate = false;
        } else {
            if (numeroET.getError() != null)
                numeroET.setError(null);
        }

        rua = ruaET.getText().toString();
        if (!DataValidatorUtil.isValidTextWithSpace(rua)) {
            //todo rua pode conter digitos sim
            ruaET.setError("A rua n�o pode conter n�meros.");
            isAllValidate = false;
        } else {
            if (ruaET.getError() != null)
                ruaET.setError(null);
        }

        bairro = bairroET.getText().toString();
        if (!DataValidatorUtil.isValidTextWithSpace(bairro)) {
            //todo bairro pode conter digitos sim
            bairroET.setError("O bairro n�o pode conter n�meros.");
            isAllValidate = false;
        } else {
            if (bairroET.getError() != null)
                bairroET.setError(null);
        }

        cidade = cidadeET.getText().toString();
        if (!DataValidatorUtil.isValidTextWithSpace(cidade)) {
            cidadeET.setError("A cidade n�o pode conter n�meros.");
            isAllValidate = false;
        } else {
            if (cidadeET.getError() != null)
                cidadeET.setError(null);
        }

        if (!DataValidatorUtil.isValidNascimento(chosenDateCal)) {
            dateET.setError("Selecione a data de nascimento");
            isAllValidate = false;
        } else {
            if (dateET.getError() != null)
                dateET.setError(null);
        }

        if (!DataValidatorUtil.isValidSexo(opcaoEscolhidaGenero)) {
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
            isAllValidate = false;
        }
        return isAllValidate;
    }

    public void executeJSON() {
        //executa JSON

        CustomerService customerService= new CustomerService();
        estado = UF.getEnumFromValue((String) estadoSP.getSelectedItem());

        //campos obrigatorios ao MVP
        customerDTO.setName(nome);
        customerDTO.setEmail(email);
        customerDTO.setBirthDate(onScreenCal);
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
        customerDTO.setFacebookLogin("NaoEssencial");
        customerDTO.setGoogleLogin("NaoEssencial");

        // executa requisi��o JSON
        try {
            customerDTO = customerService.save(customerDTO);
            if (customerDTO == null) {
                System.out.println("=== DEU ERRO E O PROFISSIONAl RETORNO NULLO");
            } else {
                System.out.println("=== DEU CERTO E O PROFISSIONAl RETORNOU COM SUCESSO " + customerDTO.getName());  //TODO verificar se o back adiciona o id no objeto de retorno
                Intent intent = new Intent(ProScheduleFirstActivity_11.this, ProScheduleHoursActivity_12.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(SessionAttributes.CUSTOMER, customerDTO);
                intent.putExtras(mBundle);
                startActivity(intent);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falha ao executar JSON");
        }
    }


}
