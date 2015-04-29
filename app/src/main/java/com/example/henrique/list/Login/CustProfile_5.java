package com.example.henrique.list.Login;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

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


import com.example.henrique.list.Cliente.CustScheduleDateFragmentPortrait_6;
import com.example.henrique.list.R;

import java.util.Calendar;

import br.com.motiserver.constants.Gender;
import br.com.motiserver.dto.CustomerDTO;



/**
 * Created by Massaru on 03/04/2015.
 */
public class CustProfile_5 extends FragmentActivity {

    ImageButton imageButton;
    EditText nomeET;


    TextView dataEscolhidaTV;


    Button chamaDatePickerBTN;
    private TextView tvDisplayDate;
    private DatePicker dpResult;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cust_profile_5_11);

        //Habilitando BackNavigation button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        complementoET = (EditText) findViewById(R.id.ComplementProET_5);
        bairroET = (EditText) findViewById(R.id.bairroCustET_5);
        cidadeET = (EditText) findViewById(R.id.cidadeCustET_5);
        estadoET = (EditText) findViewById(R.id.estadoCustET_5);


        chamaDatePickerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Inicializando RadioButtons
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

    private void cancelRegistration(){
        Intent i = new Intent(CustProfile_5.this, LoginNewAccount_2.class);
        startActivity(i);
    }
    private void confirmRegistration(){
        customerDTO.setName(nomeET.getText().toString());
        customerDTO.setEmail(emailET.getText().toString());



        // end definir
        customerDTO.setAddressStreet(ruaET.getText().toString());
        customerDTO.setAddressDistrict(bairroET.getText().toString());
        customerDTO.setAddressCity(cidadeET.getText().toString());
        customerDTO.setAddressComplement(complementoET.getText().toString());
        customerDTO.setAddressNumber(numeroET.getText().toString());
        customerDTO.setAddressZipCode(CEPET.getText().toString());

        String celular = celularET.getText().toString();
        String email = emailET.getText().toString();


        // Get Password Edit View Value


        Intent i = new Intent(CustProfile_5.this, CustScheduleDateFragmentPortrait_6.class);
        startActivity(i);

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
                    break;
            case R.id.femininoCustRB_5:
                if (checked)
                    customerDTO.setGender(Gender.FEMALE);
                    break;
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        //newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
