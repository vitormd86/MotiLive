package com.example.henrique.list.Cliente;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henrique.list.Adapters.MyAdapterFreeTime;
import com.example.henrique.list.Mapeamento_de_Classes.Servico;
import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.ResizeAnimation;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;
//todo nao mexer
/**
 * Created by Cristor on 26/02/2015.
 */
/*Tela de selecao de horas e servicos de agendamento, ao final ela gera um alerta de confirmacao*/
public class CustScheduleHourFragment_7 extends Fragment {

    private View v;
    private FragmentActivity fa;

    ArrayList<Integer> freeHours = new ArrayList<>();
    ArrayList<Integer> freeMinutes = new ArrayList<>();
    ArrayList<String> selectedServicesTitles = new ArrayList<>();
    ArrayList<Double> selectedServicesPrices = new ArrayList<>();
    ResizeAnimation resizeAnimation;
    boolean isHoursOpened,  isMinutesOpened;
    String  sDate, professionalName, occupation;
    int freeHourMinutesWidth = 90;
    int selectedHour, selectedMinutes;
    double totalPrice;
    long totalTime;
    //iniciando variaveis que virao do banco
    Servico [] testeS = getServicos();
    //iniciando items do layout
    TextView textProfessionalName, textProfession, textDate;
    ListView listHours, listMinutes, listServices;
    ArrayAdapter myAdapterServiceTypes, myAdapterFreeHours, myAdapterFreeMinutes;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fa = super.getActivity();
        v = inflater.inflate(R.layout.fragment_cust_schedule_hour_7, container, false);

        //recebe valores da fragment anterior
        Bundle args = getArguments();
        professionalName = args.getString("selectedProfessional");
        sDate = args.getString("selectedDate");
        //deve configurar dados do profissional a partir do bd
        occupation = "Massagista";
        //String [] serviceTitles = {testeS[0].getNome()};

        //alimentando items do layout
        textProfessionalName = (TextView) v.findViewById(R.id.professionalName);
        textProfession = (TextView) v.findViewById(R.id.profession);
        textDate = (TextView) v.findViewById(R.id.date);
        listHours = (ListView) v.findViewById(R.id.listHours);
        listMinutes = (ListView) v.findViewById(R.id.listMinutes);
        listServices = (ListView) v.findViewById(R.id.listServices);
        myAdapterServiceTypes = new com.example.henrique.list.Adapters.MyAdapterServiceTypes(getActivity(), testeS);
        myAdapterFreeHours = new MyAdapterFreeTime(getActivity(), freeHours, listHours);
        myAdapterFreeMinutes = new MyAdapterFreeTime(getActivity(), freeMinutes, listMinutes);

        //Configura as variaveis do cabecalho
        textProfessionalName.setText(professionalName);
        textProfession.setText(occupation);
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
                //Gera um alerta, para confirmar o agendamento
                //AlertDialog confirmAlert = getConfirmAlert();
                //confirmAlert.show();

                //Chama proximo Fragment incluindo suas informacoes nos argumentos
                //initConfirmScreen();

                //Chama proxima tela em activity
                initNextActivity();

            }
        });


    }
    //este metodo gera as informacoes do alerta de confirmacao de agendamento
    public AlertDialog getConfirmAlert(){
        //inicializando e configurando horarios
        SimpleDateFormat df = new SimpleDateFormat("HH' horas e 'mm' minutos'");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");
        df.setTimeZone(TimeZone.getDefault());
        df2.setTimeZone(TimeZone.getDefault());

        long inicialTime, finalTime;
        try{
            inicialTime = df2.parse(String.format("%02d",selectedHour) + ":" + String.format("%02d",selectedMinutes)).getTime();

        } catch (Exception e){
            inicialTime = 0;
            e.printStackTrace();
        }
        finalTime = inicialTime + totalTime + TimeZone.getDefault().getOffset(inicialTime);


        //alimentando o builder do alerta
        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirmar agendamento?");
        builder.setMessage("Profissional: " + professionalName +
                "\nServico(s): " + selectedServicesTitles +
                "\nDia: " + sDate +
                "\nInicio: " + String.format("%02d",selectedHour) + ":" + String.format("%02d",selectedMinutes) + "h" +
                "\nPeríodo: " + df.format(totalTime) +
                "\nPrevisão de término: " + df2.format(finalTime) + "h" +
                "\nValor: " + "R$: " + String.format("%.2f", totalPrice));
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //OPCAO SIM = confirma agendamento
                //DEVE ACRESCENTAR OS DADOS NO BD
                //DEVE VERIFICAR SE TODAS AS LISTAS ESTAO SELECIONADAS

                //Redireciona usuario para a tela inicial de agendamento
                Intent endOfScheduleActivity = new Intent(getActivity(), CustDrawerMenu_10.class);
                startActivity(endOfScheduleActivity);

            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                //REINICIA ListMinutes/ListHours Menu
                restartFragmentValues();
                Toast.makeText(getActivity(), "Nao", Toast.LENGTH_SHORT).show();
            }
        });
        alert = builder.create();
    return alert;
    }

    private void initConfirmScreen(){

        restartFragmentValues();

        //inicia valores que serao enviados para a proxima Fragment
        CustScheduleConfirmFragment_8 nextFragment = new CustScheduleConfirmFragment_8();
        Bundle args = new Bundle();
        args.putString("professionalName", professionalName);
        args.putString("profession", occupation);
        args.putStringArrayList("selectedServices", selectedServicesTitles);
        args.putString("sDate", sDate);
        args.putInt("selectedHour", selectedHour);
        args.putInt("selectedMinutes", selectedMinutes);
        args.putLong("totalTime", totalTime);
        args.putDouble("totalPrice", totalPrice);
        nextFragment.setArguments(args);
        //inicia a transacao de Fragments
        FragmentTransaction ft  = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, nextFragment);
        //este metodo permite q o usuario navegue de volta
        ft.addToBackStack(null);
        ft.commit();

    }

    private void initNextActivity(){
        Intent intent = new Intent(getActivity(),CustScheduleConfirmActivity_8.class);
        intent.putExtra("professionalName", professionalName);
        intent.putExtra("profession", occupation);
        intent.putExtra("selectedServices", selectedServicesTitles);
        intent.putExtra("sDate", sDate);
        intent.putExtra("selectedHour", selectedHour);
        intent.putExtra("selectedMinutes", selectedMinutes);
        intent.putExtra("totalTime", totalTime);
        intent.putExtra("totalPrice", totalPrice);

        startActivity(intent);
    }

    @Override
    public void onStop(){
        //esse metodo eh chamado sempre q a fragment vai para backStack
        super.onStop();
        restartFragmentValues();
    }
    public void restartFragmentValues(){
        //reinicia valores deste fragment
        isHoursOpened = false;
        isMinutesOpened = false;
        freeMinutes.clear();
        freeHours.clear();
        listMinutes.clearChoices();
        listHours.clearChoices();
        listServices.clearChoices();
        listHours.getLayoutParams().width = 0;
        listMinutes.getLayoutParams().width = 0;
        listHours.requestLayout();
        listHours.requestLayout();
    }
}
