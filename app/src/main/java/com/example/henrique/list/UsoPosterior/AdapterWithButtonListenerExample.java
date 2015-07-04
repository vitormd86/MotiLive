package com.example.henrique.list.UsoPosterior;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henrique.list.Beans.ScheduleItem;
import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.PinnedSectionListView.PinnedSectionListAdapter;

import java.util.ArrayList;
import java.util.Calendar;


/*Adaptador com mais de um TIPO de view.... ITEM e SECTION. Ele gera diferentes funcionalidade parqa cada tipo de view*/
public class AdapterWithButtonListenerExample extends ArrayAdapter<ScheduleItem> implements PinnedSectionListAdapter{

    public static final int ITEM = 0;
    public static final int SECTION = 1;

    public AdapterWithButtonListenerExample(Context context, ArrayList<ScheduleItem> values) {
        super(context, R.layout.view_pinnedlist_schedules, values);
    }

    //determina quantos tipos de view a lista contem
    @Override public int getViewTypeCount() {
        return 2;
    }
    //determina o tipo de view, para view da posicao indicada
    @Override public int getItemViewType(int position) {
        if(getItem(position).isSection()){
            return SECTION;
        } else {
            return ITEM;
        }
    }
    //sempre que este metodo retornar positivo a view fica presa no topo da listview
    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == SECTION;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View view;
        Calendar cal = Calendar.getInstance();
        cal.setTime(getItem(position).getScheduleDate());

        switch (getItemViewType(position)){
            //Caso a view seja de SECAO
            case SECTION:
                view = theInflator.inflate(R.layout.view_pinnedlist_schedules_datetitle, parent, false);
                TextView textDay = (TextView) view.findViewById(R.id.textScheduleDayAndMonth);
                TextView textDayOfWeek = (TextView) view.findViewById(R.id.textScheduleDayOfWeek);
                textDay.setText(""+cal.get(Calendar.DAY_OF_MONTH));
                textDayOfWeek.setText(getDayOfWeek(cal.get(Calendar.DAY_OF_WEEK)));
                //AQUI A VIEW DEVE RECEBER O BACKGROUND para nao aparecer o item saindo.
                break;
            //Caso a view seja ITEM
            default:
                view = theInflator.inflate(R.layout.view_pinnedlist_schedules, parent, false);
                String profissionalName = getItem(position).getPersonName();
                String inicialTime = getItem(position).getScheduleInicialTime();
                String finalTime = getItem(position).getScheduleFinalTime();
                //String leftTime = getItem(position).getScheduleLeftTime();

                TextView textTitle = (TextView) view.findViewById(R.id.textScheduleName);
                TextView textSubTitle = (TextView) view.findViewById(R.id.textScheduleDuration);
                //TextView textLeftTime = (TextView) view.findViewById(R.id.textLeftTime);
//                Button moreButton = (Button) view.findViewById(R.id.button);

                textTitle.setText(inicialTime + ": " + profissionalName);
                textSubTitle.setText("Duração: ");
                //textLeftTime.setText(leftTime);

//                setButtonListener(moreButton, profissionalName);
        }


        return view;
    }
    private String getDayOfWeek(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "dom";
                break;
            case 2:
                day = "seg";
                break;
            case 3:
                day = "ter";
                break;
            case 4:
                day = "qua";
                break;
            case 5:
                day = "qui";
                break;
            case 6:
                day = "sex";
                break;
            case 7:
                day = "sab";
                break;
        }
        return day;
    }

    private void setButtonListener(final Button button, final String professionalName){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(getContext(), button);
                popupMenu.getMenuInflater().inflate(R.menu.popupmenu_schedule, popupMenu.getMenu());
                //// colocando listeners individuais para cada item do menu

                //listener do botao de submenu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    public boolean onMenuItemClick(MenuItem item){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        AlertDialog popupAlert;
                        switch (item.getItemId()){

                            case R.id.remarcar:
                                //Alimenta o Alert Dialog para confirmar remarcamento
                                builder.setTitle("Remarcar Agendamento");
                                builder.setMessage("Voce deseja mesmo remarcar o agendamento com " + professionalName + "?");
                                //define o listener dos botoes SIM / NAO do Alert Dialog
                                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface arg0, int arg1){
                                        //REMARCAR AGENDAMENTO DO BD
                                        Toast.makeText(getContext(), "Sim: " + arg1, Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setNegativeButton("Não", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface arg0, int arg1){
                                    }
                                });

                                break;

                            case R.id.desmarcar:
                                //Alimenta o Alert Dialog para confirmar cancelamento
                                builder.setTitle("Cancelar Agendamento");
                                builder.setMessage("Voce deseja mesmo cancelar o agendamento com " + professionalName + "?");
                                //define o listener dos botoes SIM / NAO do Alert Dialog
                                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface arg0, int arg1){
                                        //EXCLUIR AGENDAMENTO DO BD
                                        Toast.makeText(getContext(), "Sim: " + arg1 ,Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface arg0, int arg1) {
                                    }
                                });

                                break;
                            //switch/case ends
                        }
                        popupAlert = builder.create();
                        popupAlert.show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
}
