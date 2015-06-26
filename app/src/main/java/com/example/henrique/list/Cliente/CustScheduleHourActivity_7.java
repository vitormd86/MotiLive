package com.example.henrique.list.Cliente;

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
import com.example.henrique.list.Adapters.MyAdapterFreeMinutes;
import com.example.henrique.list.Adapters.MyAdapterServicesSchedule;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.ServiceService;
import com.example.henrique.list.Utilidade_Publica.ResizeAnimation;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import br.com.motiserver.dto.BreakDTO;
import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.DailyScheduleDTO;
import br.com.motiserver.dto.PeriodDTO;
import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.dto.SchedulingDTO;
import br.com.motiserver.dto.ServiceDTO;
import br.com.motiserver.dto.builder.PeriodDTOBuilder;

/*Tela de selecao de horas e servicos de agendamento, ao final ela gera um alerta de confirmacao*/
public class CustScheduleHourActivity_7 extends ActionBarActivity {

    ResizeAnimation resizeAnimation;
    int freeHourMinutesWidth = 90;
    boolean isHoursOpened, isMinutesOpened;
    String sDate;

    //Iniciando DTOs
    private CustomerDTO customerDTO;
    private ProfessionalDTO professionalDTO;
    private DailyScheduleDTO dailyScheduleDTO;
    private List<ServiceDTO> serviceDTOList, selectedServicesDTOList;
    private Set<SchedulingDTO> schedulingDTOSet;
    private Set<BreakDTO> breakDTOSet;
    private List <PeriodDTO> periodDTOList;

    //iniciando items do layout
    TextView textProfessionalName, textProfession, textDate;
    ListView listHours, listMinutes, listServices;
    ArrayAdapter myAdapterServiceTypes, myAdapterFreeHours, myAdapterFreeMinutes;

    //variaveis de tempo
    int selectedHour, selectedMinutes;
    ArrayList<Integer> screenFreeHours = new ArrayList<>();
    ArrayList<Integer> screenFreeMinutes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_schedule_hour_7);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //iniciando dados da tela
        retrieveAttributes();
        initViews();

        //configurando listeners das listas
        setServicesListener();
        setHoursListener();
        setMinutesListener();
    }

    public void retrieveAttributes() {
        //recebe valores da fragment anterior
        Bundle extras = getIntent().getExtras();
        customerDTO = (CustomerDTO) extras.getSerializable(SessionAttributes.CUSTOMER);
        professionalDTO = (ProfessionalDTO) extras.getSerializable(SessionAttributes.PROFESSIONAL);
        dailyScheduleDTO = (DailyScheduleDTO) extras.getSerializable(SessionAttributes.DAILY_SCHEDULE);
        sDate = extras.getString("selectedDate");

        //atributos vindo dos servicos do Banco

        //lista de servicos
        serviceDTOList = new ArrayList<>();
        ServiceService serviceService = new ServiceService();
        try {
            serviceDTOList = serviceService.findAllByProfessionalId(professionalDTO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar servicos do profissional selecionado");
        }

        //agendamento diario


        //recuperando agendamentos
        //schedulingDTOSet = dailyScheduleDTO.getSchedules();
        //recuperando breaks
        //breakDTOSet = dailyScheduleDTO.getBreaks();
        //recuperando horas livres
        periodDTOList = PeriodDTOBuilder.buildFreeTimeListByDailyScheduling(dailyScheduleDTO);

    }

    private void initViews() {
        //alimentando items do layout
        textProfessionalName = (TextView) findViewById(R.id.professionalName);
        textProfession = (TextView) findViewById(R.id.profession);
        textDate = (TextView) findViewById(R.id.date);
        listHours = (ListView) findViewById(R.id.listHours);
        listMinutes = (ListView) findViewById(R.id.listMinutes);
        listServices = (ListView) findViewById(R.id.listServices);

        //alimentando adapters
        myAdapterServiceTypes = new MyAdapterServicesSchedule(this, serviceDTOList);
        myAdapterFreeHours = new MyAdapterFreeHours(this, screenFreeHours, listHours);
        myAdapterFreeMinutes = new MyAdapterFreeMinutes(this, screenFreeMinutes, listMinutes);

        //Configura as variaveis do cabecalho
        textProfessionalName.setText(professionalDTO.getName());
        textProfession.setText(professionalDTO.getProfession().getName());
        textDate.setText(sDate);

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
                //adicionando horas.... deve receber do banco de dados e tratar em seguida
                screenFreeHours.clear();
                screenFreeHours.addAll(findFreeHours());
                myAdapterFreeHours.notifyDataSetChanged();

                //verifica se a listview de horas ja esta aberta
                if (!isHoursOpened) {
                    //redimensiona listView de horas
                    isHoursOpened = true;
                    resizeAnimation = new ResizeAnimation(listHours, freeHourMinutesWidth);
                    resizeAnimation.setDuration(600);
                    listHours.startAnimation(resizeAnimation);
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
                screenFreeMinutes.addAll(findFreeMinutes(selectedHour));
                myAdapterFreeMinutes.notifyDataSetChanged();


                if (!isMinutesOpened) {
                    //redimensiona listView de horas
                    isMinutesOpened = true;
                    resizeAnimation = new ResizeAnimation(listMinutes, freeHourMinutesWidth);
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

    private ArrayList<Integer> findFreeHours(){
        ArrayList<Integer> freeHours = new ArrayList<>();
        System.out.println("Tamanho da periodDTOlist: " + periodDTOList.size());
        for (PeriodDTO periodDTO : periodDTOList){
            //um loop para cada periodo
            Calendar initialPeriodCal = periodDTO.getStartTime();
            Calendar finalPeriodCal = periodDTO.getEndTime();
            System.out.println("indice da lista de periodo do calendario: " + periodDTOList.lastIndexOf(periodDTO));
            System.out.println("Calendar dia inicial: " + initialPeriodCal.get(Calendar.HOUR_OF_DAY));

            for (int i = initialPeriodCal.get(Calendar.HOUR_OF_DAY); i < finalPeriodCal.get(Calendar.HOUR_OF_DAY); i++){
                //um loop para cada hora
                System.out.println("Adicionado na lista, hora numero " + i);
                freeHours.add(i);
            }
        }
        System.out.println("Tamanho da Arraylist de horas: " + freeHours.size());
        return freeHours;
    }
    private ArrayList<Integer> findFreeMinutes(int selectedHour){
        ArrayList<Integer> freeMinutes = new ArrayList<>();
        System.out.println("Tamanho da periodDTOlist: " + periodDTOList.size());
        for (PeriodDTO periodDTO : periodDTOList){
            //um loop para cada periodo
            Calendar initialPeriodCal = periodDTO.getStartTime();
            Calendar finalPeriodCal = periodDTO.getEndTime();
            System.out.println("indice da lista de periodo do calendario: " + periodDTOList.lastIndexOf(periodDTO));
            System.out.println("Calendar hora do dia inicial: " + initialPeriodCal.get(Calendar.HOUR_OF_DAY));

            for (int i = initialPeriodCal.get(Calendar.HOUR_OF_DAY); i < finalPeriodCal.get(Calendar.HOUR_OF_DAY); i++){
                //um loop para cada hora dentro do periodo
                System.out.println("Dentro da hora numero " + i);
                if(i == selectedHour) {
                    //verifica se a hora é a mesma q a selecionada
                    System.out.println("Encontrou hora igual a selecionada");
                    int initialMinutes = initialPeriodCal.get(Calendar.MINUTE);
                    int finalMinutes = 60;
                    if (i == finalPeriodCal.get(Calendar.HOUR_OF_DAY)){
                        //verifica se a hora termina na metade, caso sim, altera os minutos finais a serem apresentados
                        finalMinutes = finalPeriodCal.get(Calendar.MINUTE);
                    }
                    while (initialMinutes < finalMinutes) {
                        //um loop para cada  5 minutos
                        freeMinutes.add(initialMinutes);
                        initialMinutes = initialMinutes + 5;
                        System.out.println("Incluido na array o minuto " + initialMinutes);
                    }
                }
            }
        }
        System.out.println("Tamanho da Arraylist de minutos: " + freeMinutes.size());
        return freeMinutes;
    }

    private void initConfirmActivity() {
        Intent intent = new Intent(this, CustScheduleConfirmActivity_8.class);


        //todo deve passar na intent o vetor dos servicos selecionados
        intent.putExtra(SessionAttributes.PROFESSIONAL, professionalDTO);
        intent.putExtra(SessionAttributes.CUSTOMER, customerDTO);
        intent.putExtra(SessionAttributes.SERVICE,(ArrayList) selectedServicesDTOList);
        intent.putExtra(SessionAttributes.DAILY_SCHEDULE, dailyScheduleDTO);
        intent.putExtra("selectedHour", selectedHour);
        intent.putExtra("selectedMinutes", selectedMinutes);
        intent.putExtra("sDate", sDate);

        startActivity(intent);
    }

    private boolean isValidFields(){
        //validacao dos campos
        boolean isValid = true;
        //verifica se tem algum servico selecionado
        if(listServices.getCheckedItemCount() < 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(CustScheduleHourActivity_7.this);
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
        selectedServicesDTOList.clear();
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
