package com.example.henrique.list.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henrique.list.Fragments.ClientViewFragment;
import com.example.henrique.list.R;

import java.util.ArrayList;

/**
 * Created by Vitor on 14/02/2015.
**/
//PRECISO PASSAR MAIS DE UMA ARRAY COMO ARGUMENTO NO CONSTRUTOR... (HORARIO E STATUS)
public class MyAdapterClients extends ArrayAdapter<String> {
    Fragment fragment;
    public MyAdapterClients(Context context, ArrayList clients, Fragment f) {
        super(context, R.layout.view_schedule_client, clients);
        fragment = f;
    }


    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.view_schedule_client, parent, false);

        final String clients = getItem(position);
        TextView name = (TextView) theView.findViewById(R.id.textView1);
        TextView status = (TextView) theView.findViewById(R.id.textView2);
        TextView hour = (TextView) theView.findViewById(R.id.textView3);
        final Button menuButton1 = (Button) theView.findViewById(R.id.button1);
        ImageView clientPhoto =  (ImageView) theView.findViewById(R.id.imageView1);


        //Alimentando os dados de view_schedule_client
        name.setText(clients);
        status.setText("STATUS");
        hour.setText("HORARIO");
        clientPhoto.setImageResource(R.drawable.img_photo_default);
        // colocando listener no botao do menu
        menuButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(getContext(), menuButton1);
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
                                builder.setMessage("Voce deseja mesmo remarcar o agendamento com " + clients + "?");
                                //define o listener dos botoes SIM / NAO do Alert Dialog
                                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface arg0, int arg1){
                                        //REMARCAR AGENDAMENTO DO BD
                                        Toast.makeText(getContext(), "Sim: " + arg1 ,Toast.LENGTH_SHORT).show();
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
                                builder.setMessage("Voce deseja mesmo cancelar o agendamento com " + clients + "?");
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
        //Listener da foto para abrir perfil
        clientPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent nextCalendarActivity = new Intent(getContext(),ClientView.class);

                //Envia dados para proxima Activity (ProfessionalHours)
                //nextCalendarActivity.putExtra("nameClient", clients);

                //getContext().startActivity(nextCalendarActivity);
                ClientViewFragment nextFragment = new ClientViewFragment();

                //inicia valores que serao enviados para a proxima Fragment
                Bundle args = new Bundle();
                args.putString("client", clients);
                nextFragment.setArguments(args);

                //inicia a transacao de Fragments
                FragmentTransaction ft =  fragment.getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, nextFragment);

                //este metodo permite q o usuario navegue de volta
                ft.addToBackStack(null);

                ft.commit();
            }
        });

        return theView;

    }
}
