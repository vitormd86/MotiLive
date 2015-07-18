package com.example.henrique.list.Login;

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
import android.widget.Toast;

import com.example.henrique.list.Profissional.ProDrawerMenu_15;
import com.example.henrique.list.Profissional.ProServiceListActivity_7;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.ProfessionService;
import com.example.henrique.list.Service.ProfessionalService;
import com.example.henrique.list.Utilidade_Publica.DataValidatorUtil;
import com.example.henrique.list.Utilidade_Publica.ServiceException;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.motiserver.dto.ProfessionDTO;
import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.util.constants.Gender;
import br.com.motiserver.util.constants.Status;
import br.com.motiserver.util.constants.UF;


//TODO IsEDiting


public class ProProfile_5 extends ActionBarActivity {

    //    //Constants
    static final int DATE_DIALOG_ID = 999;
//
//
//    //TODO: fazer recuperacao de data e Genero
//
//    private static final String GENERO_CTE = "GENERO_CTE";
//    private static final String DATA_CTE = "DATA_CTE";

    private ProfessionService professionService;

    ArrayAdapter<String> professionAdapter;
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
    List<ProfessionDTO> professions;

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
    boolean isEditing;

    //requestCodes
    private static final int SELECT_PHOTO = 100;
    private static final int CROP_PHOTO = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_profile_5);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        isEditing = isEditingService();
        Toast.makeText(this,""+isEditing , Toast.LENGTH_SHORT).show();

        professionalDTO = new ProfessionalDTO();

        // Objetos

        initViews();
        setSpinnerItems();
        if (isEditing) {
            retrieveAttributes();
            try {
                ProfessionalService professionalService;
                professionalService = new ProfessionalService();

                professionalDTO = professionalService.find(professionalDTO.getId()); //TODO depois.. recuperar id da Session.customer.
                if (professionalDTO == null) {
                    System.out.println("Professional ta vindo nulo!");
                } else {
                    System.out.println("Professional recuperado com sucesso");
                }
            } catch (ServiceException e) {
                e.printStackTrace();
                System.out.println("Erro ao executor professionalService");
            }

            Calendar resumeCal;
            resumeCal = professionalDTO.getBirthDate();

            // coloca data selecionada dentro do TextView correspondente
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String sDate = sdf.format(resumeCal.getTime());
            chosenDateCal = resumeCal;
            dateET.setText(sDate);
            nomeET.setText(professionalDTO.getName());
            prefixET.setText(professionalDTO.getPhoneCode());
            celularET.setText(professionalDTO.getPhoneNumber());
            emailET.setText(professionalDTO.getEmail());
            numeroET.setText(professionalDTO.getAddressNumber());
            ruaET.setText(professionalDTO.getAddressStreet());
            bairroET.setText(professionalDTO.getAddressDistrict());
            cidadeET.setText(professionalDTO.getAddressCity());
            CEPET.setText(professionalDTO.getAddressZipCode());
            if (professionalDTO.getGender() == Gender.FEMALE) {
                femininoRB.toggle();
                opcaoEscolhidaGenero = Gender.FEMALE;
            } else {
                masculinoRB.toggle();
                opcaoEscolhidaGenero = Gender.MALE;
            }

            estadoSP.setSelection(stateAdapter.getPosition(professionalDTO.getAddressState().toString()));
            profissaoSP.setSelection(stateAdapter.getPosition(professionalDTO.getProfession().toString()));
        } else {
            professionalDTO = new ProfessionalDTO();
            retrieveAttributes();
            Toast.makeText(this, professionalDTO.getLogin(), Toast.LENGTH_SHORT).show();
        }
        addDateListenerButton();
        addPhotoListenerButton();


    }

    private void initViews() {
        //campos obrigat�rios
        nomeET = (EditText) findViewById(R.id.NomeET_Pro_5);
        dateET = (EditText) findViewById(R.id.dataEscolhidaProTV_5);
        masculinoRB = (RadioButton) findViewById(R.id.masculinoProRB_5);
        femininoRB = (RadioButton) findViewById(R.id.femininoProRB_5);
        celularET = (EditText) findViewById(R.id.celularProET_5);
        prefixET = (EditText) findViewById(R.id.prefixProET_5);
        emailET = (EditText) findViewById(R.id.emailProET_5);
        CEPET = (EditText) findViewById(R.id.CEPProET_5);
        numeroET = (EditText) findViewById(R.id.numeroProET_5);
        ruaET = (EditText) findViewById(R.id.AddressProET_5);
        bairroET = (EditText) findViewById(R.id.bairroProET_5);
        cidadeET = (EditText) findViewById(R.id.cidadeProSP_5);
        estadoSP = (Spinner) findViewById(R.id.estadoProSP_5);
        profissaoSP = (Spinner) findViewById(R.id.profissaoProSP_5);

        // campos nao obrigatorios
        complementoET = (EditText) findViewById(R.id.complementoProET_5);
        imageButton = (ImageButton) findViewById(R.id.ImageButtonPro_5);
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

    //configura spinners da tela
    private void setSpinnerItems() {
        ArrayList<String> professionSpinnerItens = new ArrayList<>();
        ArrayList<String> stateSpinnerItens = new ArrayList<>();
        try {
            professionService = new ProfessionService();
            professions = professionService.findAll();
            //todo subistituir por um SERVICE que me traga uma lista de nomes dos DTOs (em strings)
            for (ProfessionDTO profession : professions) {
                professionSpinnerItens.add(profession.getName());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        for (UF state : UF.values()) {
            stateSpinnerItens.add(state.getCode());
        }

        //adicionando hint aos spinners
        professionSpinnerItens.add("Selecione uma profissão");
        stateSpinnerItens.add("UF");

        //configurando spinners
        professionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, professionSpinnerItens);
        professionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profissaoSP.setAdapter(professionAdapter);
        profissaoSP.setSelection(professionAdapter.getCount() - 1);


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

        if (profissaoSP.getSelectedItemPosition() == professionAdapter.getCount() - 1) {
            TextView professionHint = (TextView) profissaoSP.getSelectedView().findViewById(android.R.id.text1);
            professionHint.setTextColor(Color.RED);
            isAllValidate = false;
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
//        if(!isEditing){
//            professionalDTO = new ProfessionalDTO();
//        }
        ProfessionalService professionalService = new ProfessionalService();
        estado = UF.getEnumFromValue((String) estadoSP.getSelectedItem());

        //campos obrigatorios ao MVP
        professionalDTO.setName(nome);
        professionalDTO.setEmail(email);
        professionalDTO.setBirthDate(onScreenCal);
        professionalDTO.setPhoneCode(prefix);
        professionalDTO.setPhoneNumber(celular);
        professionalDTO.setAddressStreet(rua);
        professionalDTO.setAddressDistrict(bairro);
        professionalDTO.setAddressCity(cidade);
        professionalDTO.setAddressNumber(numero);
        professionalDTO.setAddressZipCode(cep);
        professionalDTO.setAddressState(estado);
        professionalDTO.setGender(opcaoEscolhidaGenero);
        professionalDTO.setUpdateDate(dg);

        //profession DTO
        ProfessionDTO professionDTO = professions.get(profissaoSP.getSelectedItemPosition());
        professionalDTO.setProfession(professionDTO);
        professionalDTO.setRegistry("1234225");  //TODO colocar um campo na tela

        //campos nao obrigatorios
        professionalDTO.setAddressComplement(complementoET.getText().toString());

        // campos nao essenciais ao MVP
        professionalDTO.setStatus(Status.TRUE);
        professionalDTO.setCpfCnpj("nulo");
        professionalDTO.setFacebookLogin("NaoEssencial");
        professionalDTO.setGoogleLogin("NaoEssencial");

        // executa requisi��o JSON
        try {
            professionalDTO = professionalService.save(professionalDTO);
            if (professionalDTO == null) {
                System.out.println("=== DEU ERRO E O PROFISSIONAl RETORNO NULLO");
            } else {
                System.out.println("=== DEU CERTO E O PROFISSIONAl RETORNOU COM SUCESSO " + professionalDTO.getName());  //TODO verificar se o back adiciona o id no objeto de retorno
                if(isEditing) {
                    Intent intent = new Intent(ProProfile_5.this, ProDrawerMenu_15.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(SessionAttributes.PROFESSIONAL, professionalDTO);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(ProProfile_5.this, ProServiceListActivity_7.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(SessionAttributes.PROFESSIONAL, professionalDTO);

                    intent.putExtras(mBundle);
                    intent.putExtra("isEditing", false);
                    startActivity(intent);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falha ao executar JSON");
        }
    }
    private boolean isEditingService() {
        return getIntent().getBooleanExtra("isEditing", true);

    }
    private void retrieveAttributes() {
        professionalDTO = (ProfessionalDTO) getIntent().getSerializableExtra(SessionAttributes.PROFESSIONAL);
    }

}
