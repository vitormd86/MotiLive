package com.example.henrique.list.Profissional;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henrique.list.Adapters.MyAdapterServicesConfirmSchedule;
import com.example.henrique.list.Cliente.CustDrawerMenu_10;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.SchedulingService;
import com.example.henrique.list.Utilidade_Publica.DateUtilMoti;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.DailyScheduleDTO;
import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.dto.SchedulingDTO;
import br.com.motiserver.dto.ServiceDTO;
import br.com.motiserver.dto.ServiceSchedulingDTO;
import br.com.motiserver.util.constants.PersonType;
import br.com.motiserver.util.constants.UF;


public class ProScheduleConfirmActivity_13 extends ActionBarActivity {

    Bundle extras;

    //DTOs
    private CustomerDTO customerDTO;
    private ProfessionalDTO professionalDTO;
    private DailyScheduleDTO dailyScheduleDTO;
    private ArrayList<ServiceDTO> serviceDTOList;
    SchedulingDTO schedulingDTO;

    //Views
    ImageView imagePhoto;
    TextView textCustomerName, textContact, textDate;
    TextView textInicialHour, textFinalHour, textTotalPrice;
    ListView listServiceNames, listServicePrices;
    TextView streetET, numberET, cepET, complementET, districtET, cityET, stateET;
    //RadioGroup selectAddressRadioGroup;

    //Variveis de data e hora
    int selectedHour, selectedMinutes;
    Date totalTime, finalTime, inicialTime;

    //variaveis de preco
    BigDecimal totalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_schedule_confirm_13);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        retrieveAttributes();
        initViews();
        fillViews();

        }

    private void retrieveAttributes() {
        //recebe valores da activity anterior
        extras = getIntent().getExtras();

        if (isEditing()) {

            schedulingDTO = (SchedulingDTO) extras.getSerializable(SessionAttributes.SCHEDULING);
            customerDTO = schedulingDTO.getCustomer();
            professionalDTO = schedulingDTO.getProfessional();
            dailyScheduleDTO = schedulingDTO.getDailySchedule();

            Calendar startTime = schedulingDTO.getStartTime();
            startTime.setTimeZone(TimeZone.getDefault());
            selectedHour = startTime.get(Calendar.HOUR_OF_DAY);
            selectedMinutes = startTime.get(Calendar.MINUTE);

            //alimentando lista de servicos do agendamento selecionado
            List<ServiceSchedulingDTO> serviceSchedulingDTOArrayList = schedulingDTO.getServicesScheduling();
            ArrayList<ServiceDTO> serviceDTOLocalList = new ArrayList<>();
            for (ServiceSchedulingDTO serviceSchedulingDTO : serviceSchedulingDTOArrayList) {
                serviceDTOLocalList.add(serviceSchedulingDTO.getService());
            }
            serviceDTOList = serviceDTOLocalList;

        } else {
            //alimentando campos usados na tela
            schedulingDTO = new SchedulingDTO();
            customerDTO = (CustomerDTO) extras.getSerializable(SessionAttributes.CUSTOMER);
            professionalDTO = (ProfessionalDTO) extras.getSerializable(SessionAttributes.PROFESSIONAL);
            serviceDTOList = (ArrayList<ServiceDTO>) extras.getSerializable(SessionAttributes.SERVICE);
            dailyScheduleDTO = (DailyScheduleDTO) extras.getSerializable(SessionAttributes.DAILY_SCHEDULE);
            selectedHour = extras.getInt("selectedHour");
            selectedMinutes = extras.getInt("selectedMinutes");
        }
    }

    private void initViews(){
        //inicia objetos de layout
        imagePhoto = (ImageView) findViewById(R.id.photo);
        textCustomerName = (TextView) findViewById(R.id.clientName);
        textContact = (TextView) findViewById(R.id.contact_pro13);
        textDate = (TextView) findViewById(R.id.date);
        listServiceNames = (ListView) findViewById(R.id.listServices_pro13);
        textInicialHour = (TextView) findViewById(R.id.initialHour);
        textFinalHour = (TextView) findViewById(R.id.finalHour);
        textTotalPrice = (TextView) findViewById(R.id.totalPrice);
        streetET = (TextView) findViewById(R.id.street);
        numberET = (TextView) findViewById(R.id.number);
        cepET = (TextView) findViewById(R.id.cep);
        complementET = (TextView) findViewById(R.id.complement);
        districtET = (TextView) findViewById(R.id.district);
        cityET = (TextView) findViewById(R.id.city);
        stateET = (TextView) findViewById(R.id.state);
        //selectAddressRadioGroup = (RadioGroup) findViewById(R.id.addressRadioGroup_cust_8);
    }

    private void fillViews() {
        //precos e tempo dos servicos
        totalPrice = new BigDecimal("0.00");
        long totalTimeLong = 0;
        long finalTimeLong, inicialTimeLong;

        //calcula tempo total e final do atendimento
        for (int i = 0; serviceDTOList.size() > i; i++) {
            totalPrice = totalPrice.add(serviceDTOList.get(i).getValue());
            totalTimeLong = totalTimeLong + serviceDTOList.get(i).getTime().getTime().getTime();
        }
        //todo verificar bug de fuso horario (e tirar esta correcao)
        totalTimeLong = totalTimeLong + (TimeZone.getDefault().getOffset(totalTimeLong) * (serviceDTOList.size() - 1));


        String sSelectedTime = String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinutes);
        inicialTimeLong = DateUtilMoti.getDateFromString(sSelectedTime, new SimpleDateFormat("HH:mm")).getTime();
        finalTimeLong = inicialTimeLong + totalTimeLong + TimeZone.getDefault().getOffset(inicialTimeLong);

        inicialTime = new Date(inicialTimeLong);
        totalTime = new Date(totalTimeLong);
        finalTime = new Date(finalTimeLong);

        //alimentando views
        textInicialHour.setText(String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinutes));
        textFinalHour.setText(DateUtilMoti.getSmallHoursStringFromDate(finalTime));
        textTotalPrice.setText("R$ " + totalPrice.toString());
        textDate.setText(DateUtilMoti.getDateStringFromCalendar(dailyScheduleDTO.getDate()));

        textCustomerName.setText(customerDTO.getName());
        textContact.setText("(" + customerDTO.getPhoneCode() + ") " + customerDTO.getPhoneNumber());

        setInitialAddressItens();

        //lista de servico
        ArrayAdapter servicesAdapter = new MyAdapterServicesConfirmSchedule(this, serviceDTOList);
        listServiceNames.setAdapter(servicesAdapter);
    }

    //configura endereco inicial
    private void setInitialAddressItens() {
        if (isEditing()) {
            streetET.setText(schedulingDTO.getAddressStreet() + ",");
            numberET.setText(schedulingDTO.getAddressNumber() + " - ");
            complementET.setText(schedulingDTO.getAddressComplement());
            cepET.setText(schedulingDTO.getAddressZipCode());
            districtET.setText(schedulingDTO.getAddressDistrict());
            cityET.setText(schedulingDTO.getAddressCity() + "-");
            UF schedulingUF = schedulingDTO.getAddressState();
            stateET.setText(schedulingUF.getCode());

        } else {
            streetET.setText(customerDTO.getAddressStreet() + ",");
            numberET.setText(customerDTO.getAddressNumber() + " - ");
            complementET.setText(customerDTO.getAddressComplement());
            cepET.setText(customerDTO.getAddressZipCode());
            districtET.setText(customerDTO.getAddressDistrict());
            cityET.setText(customerDTO.getAddressCity() + "-");
            UF schedulingUF = customerDTO.getAddressState();
            stateET.setText(schedulingUF.getCode());

        }
    }

    /*
    //mude endereco caso usuario selecione outro enderecamento
    private void setAddressRadioListener() {
        if (isEditing()) {
            //em modo de edicao nao pode mudar o endereco, desativa o radiogroup
            for (int i = 0; i < selectAddressRadioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) selectAddressRadioGroup.getChildAt(i);
                radioButton.setEnabled(false);
                radioButton.setTextColor(getResources().getColor(R.color.lightGray));
            }

            selectAddressRadioGroup.clearCheck();
            selectAddressRadioGroup.setEnabled(false);
        } else {
            selectAddressRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    UF toSelectUF;
                    switch (checkedId) {
                        case R.id.proAddressRadio_cust8:
                            streetET.setText(professionalDTO.getAddressStreet() + ",");
                            numberET.setText(professionalDTO.getAddressNumber() + " - ");
                            complementET.setText(professionalDTO.getAddressComplement());
                            cepET.setText(professionalDTO.getAddressZipCode());
                            districtET.setText(professionalDTO.getAddressDistrict());
                            cityET.setText(professionalDTO.getAddressCity() + "-");
                            toSelectUF = professionalDTO.getAddressState();
                            stateET.setText(toSelectUF.getCode());

                            break;
                        case R.id.custAddressRadio_cust8:
                            streetET.setText(customerDTO.getAddressStreet() + ",");
                            numberET.setText(customerDTO.getAddressNumber() + " - ");
                            complementET.setText(customerDTO.getAddressComplement());
                            cepET.setText(customerDTO.getAddressZipCode());
                            districtET.setText(customerDTO.getAddressDistrict());
                            cityET.setText(customerDTO.getAddressCity() + "-");
                            toSelectUF = customerDTO.getAddressState();
                            stateET.setText(toSelectUF.getCode());

                            break;
                    }
                }

            });
        }
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        if (isEditing()) {
            inflater.inflate(R.menu.menu_confirm_delete, menu);
        } else {
            inflater.inflate(R.menu.menu_confirm, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Admininstra cliques da ActionBar
        switch (item.getItemId()) {
            case R.id.confirmButton:
                if(!isEditing()){
                    executeJSON();
                }
                Intent confirmIntent = new Intent(this, ProDrawerMenu_15.class);
                confirmIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                confirmIntent.putExtra(SessionAttributes.PROFESSIONAL, professionalDTO);
                startActivity(confirmIntent);
                this.finish();
                return true;

            case R.id.deleteButton:
                initCancelScheduleAlert();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void executeJSON() {
        SchedulingService schedulingService = new SchedulingService();

        List<ServiceSchedulingDTO> serviceSchedulingDTOList = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(inicialTime);
        cal2.setTime(finalTime);

        UF state = UF.getEnumFromValue((String) stateET.getText());

        for (ServiceDTO serviceDTO : serviceDTOList) {
            ServiceSchedulingDTO serviceSchedulingDTO = new ServiceSchedulingDTO();
            serviceSchedulingDTO.setService(serviceDTO);
            serviceSchedulingDTOList.add(serviceSchedulingDTO);
        }

        schedulingDTO.setProfessional(professionalDTO);
        schedulingDTO.setCustomer(customerDTO);
        schedulingDTO.setAddressCity(cityET.getText().toString());
        schedulingDTO.setAddressComplement(complementET.getText().toString());
        schedulingDTO.setAddressDistrict(districtET.getText().toString());
        schedulingDTO.setAddressNumber(numberET.getText().toString());
        schedulingDTO.setAddressState(state);
        schedulingDTO.setAddressStreet(streetET.getText().toString());
        schedulingDTO.setAddressZipCode(cepET.getText().toString());
        schedulingDTO.setAmount(totalPrice);
        schedulingDTO.setServicesScheduling(serviceSchedulingDTOList);
        schedulingDTO.setDailySchedule(dailyScheduleDTO);
        schedulingDTO.setStartTime(cal);
        schedulingDTO.setEndTime(cal2);
        schedulingDTO.setSchedulingRequester(PersonType.PROFESSIONAL);

        try {
            schedulingService.save(schedulingDTO);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao gravar agendamento");
        }
    }

    public void initCancelScheduleAlert() {
        //este metodo gera um alerta para o cancelamento
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog popupAlert;

        //Alimenta o Alert Dialog para confirmar cancelamento
        builder.setTitle("Cancelar Agendamento");
        builder.setMessage("Voce deseja mesmo cancelar o agendamento com " + customerDTO.getName() + "?");
        //define o listener dos botoes SIM / NAO do Alert Dialog
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //caso clique sim, deve voltar para atividade anterior e apagar o agendamento
                SchedulingService schedulingService = new SchedulingService();
                try{
                    schedulingDTO.setSchedulingCanceller(PersonType.PROFESSIONAL);
                    schedulingService.delete(schedulingDTO);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Erro ao apagar agendamento");
                }

                Intent cancelIntent = new Intent(getBaseContext(), ProDrawerMenu_15.class);
                cancelIntent.putExtra(SessionAttributes.PROFESSIONAL, professionalDTO);
                cancelIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(cancelIntent);
                finish();
                Toast.makeText(getBaseContext(), "Agendamento Cancelado", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //caso clique nao deve continuar na mesma atividade
            }
        });
        popupAlert = builder.create();
        popupAlert.show();
    }

    private boolean isEditing() {
        return extras.getBoolean("isEditing", true);
    }
}