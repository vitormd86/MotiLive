package com.example.henrique.list.Profissional;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.henrique.list.Adapters.MyAdapterFreeHours;
import com.example.henrique.list.Adapters.MyAdapterServicesSchedule;
import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.DateUtilMoti;
import com.example.henrique.list.Utilidade_Publica.ResizeAnimation;
import com.example.henrique.list.Utilidade_Publica.SchedulingCalculator.FreeTimeCalculator;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.DailyScheduleDTO;
import br.com.motiserver.dto.PeriodDTO;
import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.dto.ServiceDTO;


public class ProScheduleHoursActivity_12 extends ActionBarActivity {

    ResizeAnimation resizeAnimation;
    int freeHourMinutesListWidth = 90;
    boolean isHoursOpened, isMinutesOpened;

    //Iniciando DTOs
    private CustomerDTO customerDTO;
    private ProfessionalDTO professionalDTO;
    private DailyScheduleDTO dailyScheduleDTO;
    private List<ServiceDTO> serviceDTOList, selectedServicesDTOList;
    private List<PeriodDTO> periodDTOList;

    //itens de layout
    TextView textClientName, textContact, textDate;
    ListView listHours, listMinutes, listServices;
    ArrayAdapter myAdapterServiceTypes, myAdapterFreeHours, myAdapterFreeMinutes;

    //variaveis de tempo
    int selectedHour, selectedMinutes;
    ArrayList<Integer> screenFreeHours = new ArrayList<>();
    ArrayList<Integer> screenFreeMinutes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_schedule_hours_12);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //configurando itens da tela
        retrieveAttributes();
        initViews();


        //configurando listeners das listas
        setServicesListener();
        setHoursListener();
        setMinutesListener();
    }

    private void retrieveAttributes() {
        //recebe valores da fragment anterior
        Bundle extras = getIntent().getExtras();
        customerDTO = (CustomerDTO) extras.getSerializable(SessionAttributes.CUSTOMER);
        professionalDTO = (ProfessionalDTO) extras.getSerializable(SessionAttributes.PROFESSIONAL);
        dailyScheduleDTO = (DailyScheduleDTO) extras.getSerializable(SessionAttributes.DAILY_SCHEDULE);
        periodDTOList = (ArrayList<PeriodDTO>) extras.getSerializable(SessionAttributes.PERIOD_LIST);
        serviceDTOList = (ArrayList<ServiceDTO>) extras.getSerializable(SessionAttributes.SERVICE);

        System.out.println("Array Servicos chegando com size " + serviceDTOList.size());
    }

    private void initViews() {
        //alimentando items do layout
        textClientName = (TextView) findViewById(R.id.professionalName);
        textContact = (TextView) findViewById(R.id.contact_pro12);
        textDate = (TextView) findViewById(R.id.date);
        listHours = (ListView) findViewById(R.id.listHours);
        listMinutes = (ListView) findViewById(R.id.listMinutes);
        listServices = (ListView) findViewById(R.id.listServices);

        myAdapterServiceTypes = new MyAdapterServicesSchedule(this, serviceDTOList);
        myAdapterFreeHours = new MyAdapterFreeHours(this, screenFreeHours, listHours);
        myAdapterFreeMinutes = new MyAdapterFreeHours(this, screenFreeMinutes, listMinutes);

        //Configura as variaveis do cabecalho
        textClientName.setText(customerDTO.getName());
        textContact.setText("(" + customerDTO.getPhoneCode() + ") " + customerDTO.getPhoneNumber());
        textDate.setText(DateUtilMoti.getDateStringFromCalendar(dailyScheduleDTO.getDate()));

        //Configurando listas de servicos/horas/minutos livre
        listHours.setAdapter(myAdapterFreeHours);
        listMinutes.setAdapter(myAdapterFreeMinutes);
        listServices.setAdapter(myAdapterServiceTypes);
        listServices.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    //metodo q cria listener da lista de servicos
    public void setServicesListener() {
        listServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //caso nenhum servico selecionado, fechar listview de horas
                if (listServices.getCheckedItemCount() == 0 & isHoursOpened) {
                    isHoursOpened = false;
                    resizeAnimation = new ResizeAnimation(listHours, 0);
                    resizeAnimation.setDuration(400);
                    listHours.startAnimation(resizeAnimation);
                } else {
                    //adicionando horas na lista
                    screenFreeHours.clear();
                    screenFreeHours.addAll(FreeTimeCalculator.findFreeHoursByServicesTime(periodDTOList, dailyScheduleDTO, findSelectedServices()));
                    myAdapterFreeHours.notifyDataSetChanged();

                    //verifica se a lista de minutos jah esta aberta, para fecha-la
                    if (isMinutesOpened) {
                        isMinutesOpened = false;
                        resizeAnimation = new ResizeAnimation(listMinutes, 0);
                        resizeAnimation.setDuration(400);
                        listMinutes.startAnimation(resizeAnimation);
                        listHours.clearChoices();
                    }

                    //verifica se a listview de horas esta fechada, para abri-la
                    if (!isHoursOpened) {
                        isHoursOpened = true;
                        resizeAnimation = new ResizeAnimation(listHours, freeHourMinutesListWidth);
                        resizeAnimation.setDuration(400);
                        listHours.startAnimation(resizeAnimation);
                    }
                }

            }
        });

    }

    //metodo q cria listener da lista de horas
    public void setHoursListener() {
        listHours.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Armazena hora selecionada
                selectedHour = (int) myAdapterFreeHours.getItem(position);

                //caso clique na hora a lista de minutos mudara de acordo com o horario dispnivel pra aquele servico
                screenFreeMinutes.clear();
                screenFreeMinutes.addAll(FreeTimeCalculator.findFreeMinutesByServicesTime(selectedHour, periodDTOList, findSelectedServices()));
                myAdapterFreeMinutes.notifyDataSetChanged();


                if (!isMinutesOpened) {
                    //redimensiona listView de horas
                    isMinutesOpened = true;
                    resizeAnimation = new ResizeAnimation(listMinutes, freeHourMinutesListWidth);
                    resizeAnimation.setDuration(600);
                    listMinutes.startAnimation(resizeAnimation);
                }
            }
        });
    }

    //metodo q cria listener da lista de minutos
    public void setMinutesListener() {
        listMinutes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Armazena servico/hora/servicos selecionados
                selectedServicesDTOList = new ArrayList<>();
                selectedMinutes = (int) myAdapterFreeMinutes.getItem(position);

                //Alimentando array de servicos selecionados
                SparseBooleanArray checkedServices = listServices.getCheckedItemPositions();
                //para cada item selecionado alimenta seus respectivos valores;
                for (int i = 0; i < listServices.getAdapter().getCount(); i++) {
                    if (checkedServices.get(i)) {
                        ServiceDTO s = (ServiceDTO) myAdapterServiceTypes.getItem(i);
                        selectedServicesDTOList.add(s);
                    }
                }

                //Chama proxima tela em activity
                if(isValidFields()){
                    initConfirmActivity();
                }
            }
        });
    }

    private List<ServiceDTO> findSelectedServices(){
        //Alimentando array de servicos selecionados
        SparseBooleanArray checkedServices = listServices.getCheckedItemPositions();
        List<ServiceDTO> selectedServices = new ArrayList<>();
        //para cada item selecionado alimenta seus respectivos valores;
        for (int i = 0; i < listServices.getAdapter().getCount(); i++) {
            if (checkedServices.get(i)) {
                ServiceDTO s = (ServiceDTO) myAdapterServiceTypes.getItem(i);
                selectedServices.add(s);
            }
        }
        return selectedServices;
    }

    private void initConfirmActivity() {
        Intent intent = new Intent(this, ProScheduleConfirmActivity_13.class);
        intent.putExtra(SessionAttributes.PROFESSIONAL, professionalDTO);
        intent.putExtra(SessionAttributes.CUSTOMER, customerDTO);
        intent.putExtra(SessionAttributes.SERVICE,(ArrayList) selectedServicesDTOList);
        intent.putExtra(SessionAttributes.DAILY_SCHEDULE, dailyScheduleDTO);
        intent.putExtra("selectedHour", selectedHour);
        intent.putExtra("selectedMinutes", selectedMinutes);
        intent.putExtra("isEditing", false);

        startActivity(intent);
    }

    private boolean isValidFields(){
        //validacao dos campos
        boolean isValid = true;
        //verifica se tem algum servico selecionado
        if(listServices.getCheckedItemCount() < 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(ProScheduleHoursActivity_12.this);
            AlertDialog popupAlert;
            //Alimenta o Alert Dialog para erro de validação para usuario

            builder.setMessage("Selecione pelo menos um serviço.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
            popupAlert = builder.create();
            popupAlert.show();

            isValid = false;
        }
        return isValid;
    }

    @Override
    public void onStop() {
        //esse metodo eh chamado sempre q a fragment vai para BackStack (chamando outra atividade por exemplo)
        super.onStop();
        restartValues();
    }

    public void restartValues() {
        //reinicia valores deste fragment
        if(selectedServicesDTOList != null){
            selectedServicesDTOList.clear();
        }
        isHoursOpened = false;
        isMinutesOpened = false;
        screenFreeMinutes.clear();
        screenFreeHours.clear();
        listMinutes.clearChoices();
        listHours.clearChoices();
        listServices.clearChoices();
        listHours.getLayoutParams().width = 0;
        listMinutes.getLayoutParams().width = 0;
        listHours.requestLayout();
        listHours.requestLayout();
    }
}

