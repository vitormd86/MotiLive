package com.example.henrique.list.Profissional;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.henrique.list.Adapters.MyAdapterFreeTime;
import com.example.henrique.list.Adapters.MyAdapterServiceTypes;
import com.example.henrique.list.Mapeamento_de_Classes.Servico;
import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.ResizeAnimation;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Created by htamashiro on 3/21/15.
 */
public class ProScheduleHoursFragment_12 extends Fragment {

    private View v;
    private FragmentActivity fa;

    ArrayList<Integer> freeHours = new ArrayList<>();
    ArrayList<Integer> freeMinutes = new ArrayList<>();
    ArrayList<String> selectedServicesTitles = new ArrayList<>();
    ResizeAnimation resizeAnimation;
    boolean isHoursOpened,  isMinutesOpened;
    String  sDate, clientName, fullAddress;
    String street, number, cep, complement, district, city, state;
    int freeHourMinutesWidth = 90;
    int selectedHour, selectedMinutes;
    double totalPrice;
    long totalTime;
    //iniciando variaveis que virao do banco
    Servico[] testeS = getServicos();
    //iniciando items do layout
    TextView textClientName, textAddress, textDate;
    ListView listHours, listMinutes, listServices;
    ArrayAdapter myAdapterServiceTypes, myAdapterFreeHours, myAdapterFreeMinutes;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fa = super.getActivity();
        v = inflater.inflate(R.layout.fragment_pro_schedule_hours_12, container, false);

        //recebe valores da fragment anterior
        Bundle args = getArguments();
        clientName = args.getString("selectedClient");
        sDate = args.getString("selectedDate");
        //deve configurar dados do cliente a partir do bd
        street = "Av. Leoncio de Magalhães";
        number = "1066";
        complement = " " + "ap 12";
        district = "Jardim São Paulo";
        city = "São Paulo";
        state = "SP";
        cep = "02101-000";
        fullAddress = street + ", " + number + complement + ". ";
        //String [] serviceTitles = {testeS[0].getNome()};

        //alimentando items do layout
        textClientName = (TextView) v.findViewById(R.id.professionalName);
        textAddress = (TextView) v.findViewById(R.id.address);
        textDate = (TextView) v.findViewById(R.id.date);
        listHours = (ListView) v.findViewById(R.id.listHours);
        listMinutes = (ListView) v.findViewById(R.id.listMinutes);
        listServices = (ListView) v.findViewById(R.id.listServices);
        myAdapterServiceTypes = new MyAdapterServiceTypes(getActivity(), testeS);
        myAdapterFreeHours = new MyAdapterFreeTime(getActivity(), freeHours, listHours);
        myAdapterFreeMinutes = new MyAdapterFreeTime(getActivity(), freeMinutes, listMinutes);

        //Configura as variaveis do cabecalho
        textClientName.setText(clientName);
        textAddress.setText(fullAddress);
        textDate.setText(sDate);

        //Configurando listas de servicos/horas/minutos livre
        listHours.setAdapter(myAdapterFreeHours);
        listMinutes.setAdapter(myAdapterFreeMinutes);
        listServices.setAdapter(myAdapterServiceTypes);
        listServices.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //configurando listeners das listas
        setServicesListener();
        setHoursListener();
        setMinutesListener();

        return v;
    }

    //metdodo provisorio que retorna os servicos
    public Servico [] getServicos(){
        Servico s1 = new Servico();
        s1.setId(1);
        s1.setId_profissional(1);
        s1.setNome("Servico 1");
        s1.setDescricao("Servico de teste");
        s1.setValor(20.00);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(TimeZone.getDefault());
        try {
            Time tempo = new Time(sdf.parse("00:20").getTime());
            Time atraso = new Time(sdf.parse("00:05").getTime());
            s1.setTempo(tempo);
            s1.setTolerancia_atraso(atraso);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        Servico s2 = new Servico();
        s2.setId(2);
        s2.setId_profissional(1);
        s2.setNome("Servico 2");
        s2.setDescricao("Outro servico de teste");
        s2.setValor(30.00);
        try {
            Time tempo = new Time(sdf.parse("00:35").getTime());
            Time atraso = new Time(sdf.parse("00:05").getTime());
            s2.setTempo(tempo);
            s2.setTolerancia_atraso(atraso);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        Servico s3 = new Servico();
        s3.setId(3);
        s3.setId_profissional(1);
        s3.setNome("Servico 3");
        s3.setDescricao("Outro servico de teste 3");
        s3.setValor(55.00);
        try {
            Time tempo = new Time(sdf.parse("00:15").getTime());
            Time atraso = new Time(sdf.parse("00:05:00").getTime());
            s3.setTempo(tempo);
            s3.setTolerancia_atraso(atraso);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        Servico [] s = {s1, s2, s3};
        return s;
    }

    //metodo q cria listener da lista de servicos
    public void setServicesListener(){
        listServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //adicionando horas.... deve receber do banco de dados e tratar em seguida
                freeHours.clear();
                freeHours.add(1);
                freeHours.add(2);
                freeHours.add(3);
                freeHours.add(4);
                freeHours.add(5);
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
    public void setHoursListener (){
        listHours.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //caso clique na hora a lista de minutos mudara de acordo com o horario dispnivel pra aquele servico
                freeMinutes.clear();
                freeMinutes.add(00);
                freeMinutes.add(05);
                freeMinutes.add(10);
                freeMinutes.add(15);
                freeMinutes.add(20);
                freeMinutes.add(30);
                freeMinutes.add(40);
                freeMinutes.add(50);
                myAdapterFreeMinutes.notifyDataSetChanged();

                //Armazena hora selecionada
                selectedHour = (int) myAdapterFreeHours.getItem(position);

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
    public void setMinutesListener(){
        listMinutes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Armazena servico/hora/servicos selecionados
                selectedMinutes = (int) myAdapterFreeMinutes.getItem(position);
                totalTime = 0;
                totalPrice = 0;
                //Alimentando array de servicos selecionados
                SparseBooleanArray checkedServices = listServices.getCheckedItemPositions();
                //para cada item selecionado alimenta seus respectivos valores;
                for (int i = 0; i < listServices.getAdapter().getCount(); i++) {
                    if (checkedServices.get(i)) {
                        Servico s = (Servico) myAdapterServiceTypes.getItem(i);
                        selectedServicesTitles.add(s.getNome());
                        //esse valores devem ser buscados da classe Servicos armazenados no adapterServiceTypes
                        totalTime = totalTime + s.getTempo().getTime();
                        totalPrice = totalPrice + s.getValor();
                    }
                }
                totalTime = totalTime + (TimeZone.getDefault().getOffset(totalTime)* (checkedServices.size() - 1));

                //Chama proxima tela em activity
                initConfirmActivity();

            }
        });


    }

    private void initConfirmActivity(){
        Intent intent = new Intent(getActivity(),ProScheduleConfirmActivity_13.class);

        //todo deve passar na intent o vetor dos servicos selecionados
        intent.putExtra("clientName", clientName);
        intent.putExtra("street", street);
        intent.putExtra("number", number);
        intent.putExtra("cep", cep);
        intent.putExtra("complement", complement);
        intent.putExtra("district", district);
        intent.putExtra("city", city);
        intent.putExtra("state", state);
        intent.putExtra("selectedServices", selectedServicesTitles);
        intent.putExtra("sDate", sDate);
        intent.putExtra("selectedHour", selectedHour);
        intent.putExtra("selectedMinutes", selectedMinutes);
        intent.putExtra("totalTime", totalTime);
        intent.putExtra("totalPrice", totalPrice);
        intent.putExtra("nextScreen", 10);

        startActivity(intent);
    }

    @Override
    public void onStop(){
        //esse metodo eh chamado sempre q a fragment vai para BackStack (chamando outra atividade por exemplo)
        super.onStop();
        restartFragmentValues();
    }

    public void restartFragmentValues(){
        //reinicia valores deste fragment
        isHoursOpened = false;
        isMinutesOpened = false;
        freeMinutes.clear();
        freeHours.clear();
        selectedServicesTitles.clear();
        listMinutes.clearChoices();
        listHours.clearChoices();
        listServices.clearChoices();
        listHours.getLayoutParams().width = 0;
        listMinutes.getLayoutParams().width = 0;
        listHours.requestLayout();
        listHours.requestLayout();
    }
}

