package com.example.henrique.list.Login;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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

import br.com.motiserver.constants.Gender;
import br.com.motiserver.constants.PersonType;
import br.com.motiserver.constants.Status;
import br.com.motiserver.constants.UF;
import br.com.motiserver.dto.CustomerDTO;



/**
 * Created by Massaru on 03/04/2015.
 */
public class CustProfile_5 extends ActionBarActivity {

    ImageButton imageButton;
    EditText nomeET;
    TextView dataEscolhidaTV;
    private Button chamaDatePickerBTN;
    private int year;
    private int month;
    private int day;
    private Date chosenDate;
    public CustomerService customerService;

    RadioButton masculinoRB;
    RadioButton femininoRB;

    EditText celularET;
    EditText emailET;

    EditText CEPET;
    EditText numeroET;
    EditText ruaET;
    EditText complementoET;
    EditText bairroET;
    EditText cidadeET;
    EditText estadoET;

    CustomerDTO customerDTO;

    static final int DATE_DIALOG_ID = 999;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cust_profile_5_11);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Aqui  inicializaremos as variáveis

        customerDTO = new CustomerDTO();

        imageButton = (ImageButton) findViewById(R.id.ImageButtonCust_5_11);
        nomeET = (EditText)  findViewById(R.id.NomeET_Cust_5);

        dataEscolhidaTV = (TextView) findViewById(R.id.dataEscolhidaTV_5);

        chamaDatePickerBTN = (Button) findViewById(R.id.escolheDataCustBTN_5);

        masculinoRB = (RadioButton) findViewById(R.id.masculinoCustRB_5);
        femininoRB = (RadioButton) findViewById(R.id.femininoCustRB_5);

        celularET = (EditText) findViewById(R.id.celularCustET_5);
        emailET = (EditText) findViewById(R.id.emailCustET_5);

        CEPET = (EditText) findViewById(R.id.CEPCustET_5);
        numeroET = (EditText) findViewById(R.id.numeroCustET_5);
        ruaET = (EditText) findViewById(R.id.RuaCustET_5);
        complementoET = (EditText) findViewById(R.id.complementoCustET_5);
        bairroET = (EditText) findViewById(R.id.bairroCustET_5);
        cidadeET = (EditText) findViewById(R.id.cidadeCustET_5);
        estadoET = (EditText) findViewById(R.id.estadoCustET_5);


        addListenerOnButton();

    }

    //aqui inicializamos os botoes da action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_confirm, menu);

        return super.onCreateOptionsMenu(menu);
    }
    /**
     * On selecting action bar icons
     * */
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


    private void confirmRegistration(){
        customerDTO = new CustomerDTO();
        customerDTO.setName(nomeET.getText().toString());
        customerDTO.setEmail(emailET.getText().toString());
        customerDTO.setBirthDate(chosenDate);

        customerDTO.setPhoneNumber(celularET.getText().toString());
        customerDTO.setAddressStreet(ruaET.getText().toString());
        customerDTO.setAddressDistrict(bairroET.getText().toString());
        customerDTO.setAddressCity(cidadeET.getText().toString());
        customerDTO.setAddressNumber(numeroET.getText().toString());
        customerDTO.setAddressZipCode(CEPET.getText().toString());
        customerDTO.setAddressState(UF.SAO_PAULO);
        customerDTO.setGender(Gender.MALE); //TODO arrumar o  sexo do programa
        //Toast.makeText(getApplicationContext(), customerDTO.getAddressState().toString(), Toast.LENGTH_LONG).show();

        customerDTO.setAddressComplement(complementoET.getText().toString());
        customerDTO.setCpfCnpj("36652914883");
        Calendar dg = Calendar.getInstance();
        customerDTO.setUpdateDate(dg.getTime());
        customerDTO.setStatus(Status.TRUE);
        customerDTO.setType(PersonType.CUSTOMER);
        customerDTO.setPhoneCode("11");
        customerDTO.setLogin("jaodasilva");
        customerDTO.setFacebookLogin("flangoflito");
        customerDTO.setGoogleLogin("jao canabrava");
        customerDTO.setPassword("pancreas");


        // executa requisição JSON
         new HttpRequestTask().execute(customerDTO);
    }
//TODO preencher corretamente

    //gerencia a manipulação de Radio Buttons
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.masculinoCustRB_5:
                if (checked)
                    customerDTO.setGender(Gender.MALE);
                Toast.makeText(getApplicationContext(), customerDTO.getGender().toString(), Toast.LENGTH_LONG).show();

                break;
            case R.id.femininoCustRB_5:
                if (checked)
                    customerDTO.setGender(Gender.FEMALE);
                Toast.makeText(getApplicationContext(), customerDTO.getGender().toString(), Toast.LENGTH_LONG).show();

                break;
        }
    }

    public void addListenerOnButton() {

        chamaDatePickerBTN = (Button) findViewById(R.id.escolheDataCustBTN_5);

        chamaDatePickerBTN.setOnClickListener(new View.OnClickListener() {

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
                        year, month,day);
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
            if (Result != null) {
                Intent i = new Intent(CustProfile_5.this, CustDrawerMenu_10.class);
                startActivity(i);
            }else{
                System.out.println("alguma coisa deu errado");
            }
        }



    }

}
