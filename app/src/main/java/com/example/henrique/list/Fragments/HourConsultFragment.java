package com.example.henrique.list.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.example.henrique.list.Adapters.MyAdapterFreeTime;
import com.example.henrique.list.Adapters.MyAdapterServiceTypes;
import com.example.henrique.list._DrawerTestActivity;
import com.example.henrique.list.R;
import com.example.henrique.list.ResizeAnimation;

import java.util.ArrayList;

/**
 * Created by Cristor on 26/02/2015.
 */
public class HourConsultFragment extends Fragment {

    private View v;
    private FragmentActivity fa;

    ArrayList<Integer> freeHours = new ArrayList<>();
    ArrayList<Integer> freeMinutes = new ArrayList<>();
    ResizeAnimation resizeAnimation;
    boolean isHoursOpened;
    boolean isMinutesOpened;
    int freeHourMinutesWidth = 90;
    String selectedServicesTitle;
    String selectedHour;
    String selectedMinutes;
    String sDate;
    String professionalName;
    String totalTime;
    String totalPrice;
    //iniciando items do layout
    TextView textProfessionalName;
    TextView textProfession;
    TextView textDate;
    ListView listHours;
    ListView listMinutes;
    ListView listServices;
    ArrayAdapter myAdapterServiceTypes;
    ArrayAdapter myAdapterFreeHours;
    ArrayAdapter myAdapterFreeMinutes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fa = super.getActivity();
        v = inflater.inflate(R.layout.activity_hour_consult, container, false);

        //recebe valores da fragment anterior
        Bundle args = getArguments();
        professionalName = args.getString("selectedProfessional");
        sDate = args.getString("selectedDate");
        //deve configurar dados do profissional a partir do bd
        String occupation = "Massagista";
        String[] services = {"Serviço 1", "Serviço2", "Serviço3", "Serviço4", "Serviço5", "Serviço6", "Servico7", "Servico8", "servico8"};

        //alimentando items do layout
        textProfessionalName = (TextView) v.findViewById(R.id.professionalName);
        textProfession = (TextView) v.findViewById(R.id.profession);
        textDate = (TextView) v.findViewById(R.id.date);
        listHours = (ListView) v.findViewById(R.id.listHours);
        listMinutes = (ListView) v.findViewById(R.id.listMinutes);
        listServices = (ListView) v.findViewById(R.id.listServices);
        myAdapterServiceTypes = new MyAdapterServiceTypes(getActivity(), services);
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

                if (!isMinutesOpened) {
                    //redimensiona listView de horas]
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
                selectedHour = "" + myAdapterFreeHours.getItem(position);
                selectedMinutes = "" + myAdapterFreeMinutes.getItem(position);
                selectedServicesTitle = "";
                totalTime = "";
                totalPrice = "";
                //Alimentando variavel de titulo de servicos selecionados
                SparseBooleanArray checkedServices = listServices.getCheckedItemPositions();
                //para cada item selecionado alimenta seus respctivos valores;
                for (int i = 0; i < listServices.getAdapter().getCount(); i++) {
                    if (checkedServices.get(i)) {
                        selectedServicesTitle = selectedServicesTitle + " " + myAdapterServiceTypes.getItem(i);
                        //esse valores devem ser buscados da classe Servicos armazenados no adapterServiceTypes
                        totalTime = totalTime + " " + i;
                        totalPrice = totalPrice + " " + i;
                    }
                }
                //Gera um alerta, para confirmar o agendamento
                AlertDialog confirmAlert = getConfirmAlert();
                confirmAlert.show();
            }
        });


    }
    //este metodo gera as informacoes do alerta de confirmacao de agendamento
    public AlertDialog getConfirmAlert(){
    AlertDialog alert;
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirmar agendamento?");
        builder.setMessage("Profissional: " + professionalName +
                "\nServico(s): " + selectedServicesTitle +
                "\nDia: " + sDate +
                "\nInicio: " + selectedHour + ":" + selectedMinutes + "h" +
                "\nPeríodo: " + totalTime +
                "\nFim: " + "02:00h" +
                "\nValor: " + "R$: " + totalPrice);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //OPCAO SIM = confirma agendamento
                //DEVE ACRESCENTAR OS DADOS NO BD
                //DEVE VERIFICAR SE TODAS AS LISTAS ESTAO SELECIONADAS

                //Redireciona usuario para a tela inicial de agendamento
                Intent endOfScheduleActivity = new Intent(getActivity(), _DrawerTestActivity.class);
                startActivity(endOfScheduleActivity);

            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                //REINICIA ListMinutes/ListHours Menu
                resizeAnimation = new ResizeAnimation(listHours, 0);
                listHours.startAnimation(resizeAnimation);
                resizeAnimation = new ResizeAnimation(listMinutes, 0);
                listMinutes.startAnimation(resizeAnimation);

                isHoursOpened = false;
                isMinutesOpened = false;

                listMinutes.clearChoices();
                listHours.clearChoices();
                listServices.clearChoices();
                Toast.makeText(getActivity(), "Nao", Toast.LENGTH_SHORT).show();
            }
        });
        alert = builder.create();
    return alert;
    }

}
