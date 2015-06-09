package com.example.henrique.list.Profissional;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.Utility;

import br.com.motiserver.constants.Status;
import br.com.motiserver.dto.ProfessionalDTO;
//TODO IsEDiting


public class ProScheduleFirstActivity_11 extends ActionBarActivity {

    int nextScreen;


    private static final String NOME_CTE = "NOME_CTE";
    private static final String CEP_CTE = "CEP_CTE";
    private static final String NUMERO_CTE = "NUMERO_CTE";
    private static final String RUA_CTE = "RUA_CTE";
    private static final String BAIRRO_CTE = "BAIRRO_CTE";
    private static final String CIDADE_CTE = "CIDADE_CTE";
    private static final String ESTADO_CTE = "ESTADO_CTE";

    //Dados Obrigat�rios

    EditText nomeET;
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
    ProfessionalDTO professionalDTO;

    //boolean
    boolean executaJSON;

    //    armazenadores
    String nome;
    String cep;
    String numero;
    String rua;
    String bairro;
    String cidade;
    String estado;


    @Override

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_schedule_first_11);

        //Verificando se est� iniciando ou restaurando
        if ( savedInstanceState == null)
        {
            //significa que o APP est� iniciando

        }else{
            //significa que o APP est� restaurando
            nomeET.setText(savedInstanceState.getString(NOME_CTE));
            CEPET.setText(savedInstanceState.getString(CEP_CTE));
            numeroET.setText(savedInstanceState.getString(NUMERO_CTE));
            ruaET.setText(savedInstanceState.getString(RUA_CTE));
            bairroET.setText(savedInstanceState.getString(BAIRRO_CTE));
            cidadeET.setText(savedInstanceState.getString(CIDADE_CTE));
            estadoET.setText(savedInstanceState.getString(ESTADO_CTE));

            /*
        inflando as views
*/
            //campos obrigat�rios
            nomeET = (EditText) findViewById(R.id.NomeET_Pro_11);
            CEPET = (EditText) findViewById(R.id.CEPProET_11);
            numeroET = (EditText) findViewById(R.id.numeroProET_11);
            ruaET = (EditText) findViewById(R.id.RuaProET_11);
            bairroET = (EditText) findViewById(R.id.bairroProET_11);
            cidadeET = (EditText) findViewById(R.id.cidadeProET_11);
            estadoET = (EditText) findViewById(R.id.estadoProET_11);
            //campos nao obrigatorios
            complementoET = (EditText) findViewById(R.id.cidadeProET_11);
            imageButton = (ImageButton) findViewById(R.id.ImageButtonPro_11);
            //booleans
            executaJSON = true;

        }

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //recebendo int que avisa o drawer qual fragment abrir
        Bundle extras = getIntent().getExtras();
        nextScreen = extras.getInt("nextScreen");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_confirm, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Admininstra cliques da ActionBar
        switch (item.getItemId()) {
            case R.id.confirmButton:
                //inicia argumentos para a tela proScheduleHoursFragment12 atraves da drawerActivity
                Bundle extras = new Bundle();
                extras.putString("selectedClient", nomeET.getText().toString());
                extras.putString("selectedDate", "XX/XX/XX");

                //inicia intent
                Intent confirmIntent = new Intent(this, ProDrawerMenu_15.class);
                confirmIntent.putExtra("nextScreen", nextScreen);
                confirmIntent.putExtra("extras", extras);
                confirmIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                confirmRegistration();// executa json TODO ver o q sao as outras coisas com vitinho

                Toast.makeText(this, "Cliente adicionado a sua lista.", Toast.LENGTH_SHORT).show();
                startActivity(confirmIntent);
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
        estado = estadoET.getText().toString();
        if (!Utility.isValidEstado(estado)) {
            estadoET.setError("O estado n�o pode conter n�meros.");
            executaJSON = false;
        }else{
            if(estadoET.getError() != null)
                estadoET.setError(null);
        }


        if (executaJSON) {

            //campos obrigatorios ao MVP

            professionalDTO.setName(nome);
            professionalDTO.setAddressStreet(rua);
            professionalDTO.setAddressDistrict(bairro);
            professionalDTO.setAddressCity(cidade);
            professionalDTO.setAddressNumber(numero);
            professionalDTO.setAddressZipCode(cep);
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

            // executa requisi��o JSON //TODO modificar
            try {
             //   professionalDTO = new ProfessionalSaveService().execute(professionalDTO).get();

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
    // em caso de restauração
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NOME_CTE, nome);
        outState.putString(CEP_CTE, cep);
        outState.putString(NUMERO_CTE, numero);
        outState.putString(CIDADE_CTE, cidade);
        outState.putString(ESTADO_CTE, estado);
    }


}
