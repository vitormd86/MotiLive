package com.example.henrique.list.Cliente;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.henrique.list.Adapters.ServicesNameAdapter;
import com.example.henrique.list.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Created by Cristor on 24/03/2015.
 */
//todo nao mexer

public class CustScheduleConfirmFragment_8 extends Fragment {
    private View v;
    private FragmentActivity fa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fa = super.getActivity();
        v = inflater.inflate(R.layout.fragment_cust_schedule_confirm_8, container, false);

        //recebe valores da fragment anterior
        Bundle args = getArguments();
        String professionalName = args.getString("professionalName");
        String profession = args.getString("profession");
        ArrayList<String> serviceNames = args.getStringArrayList("selectedServices");
        String sDate = args.getString("sDate");
        int selectedHour = args.getInt("selecterHour");
        int selectedMinutes = args.getInt("selectedMinutes");
        long totalTime = args.getLong("totalTime");
        double totalPrice = args.getDouble("totalPrice");

        //inicia objetos de layout
        ImageView imagePhoto = (ImageView) v.findViewById(R.id.photo);
        TextView textProfessionalName = (TextView) v.findViewById(R.id.professionalName);
        TextView textProfession = (TextView) v.findViewById(R.id.profession);
        TextView textDate = (TextView) v.findViewById(R.id.date);
        ListView listServiceNames = (ListView) v.findViewById(R.id.listServiceNames);
        TextView textInicialHour = (TextView) v.findViewById(R.id.initialHour);
        TextView textTotalTime = (TextView) v.findViewById(R.id.duration);
        TextView textFinalHour = (TextView) v.findViewById(R.id.finalHour);
        ListView listServicePrices = (ListView) v.findViewById(R.id.listServicePrices);
        TextView textTotalPrice = (TextView) v.findViewById(R.id.totalPrice);
        TextView textAddress = (TextView) v.findViewById(R.id.address);

        //comeca a formatar valores a serem apresentados
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
        //iniciando adapter de nomes de servicos
        ArrayAdapter servicesNameAdapter = new ServicesNameAdapter(v.getContext(),serviceNames);
        listServiceNames.setAdapter(servicesNameAdapter);


        //configura valores em suas views
        textProfessionalName.setText(professionalName);
        textDate.setText(sDate);
        textProfession.setText(profession);
        textTotalPrice.setText("R$: " + String.format("%.2f", totalPrice));
        textInicialHour.setText(df2.format(inicialTime));
        textTotalTime.setText(df.format(totalTime));
        textFinalHour.setText(df2.format(finalTime));
        //falta buscar endereco do usuario, ou profissional e precos isolados
        return v;
    }



}
