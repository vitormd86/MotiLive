package com.example.henrique.list.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Henrique on 07/02/2015.
 */

// Isso eu creiri pra faz uns testes no menu... depois vamos tirar isso.
public class myDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder theDialog = new AlertDialog.Builder(getActivity());
        theDialog.setTitle("Sample");
        theDialog.setMessage("Hello world!");
        theDialog.setPositiveButton("OK,", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"cliecked ok",Toast.LENGTH_SHORT).show();
            }
        });
        theDialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"cliecked Cancel",Toast.LENGTH_SHORT).show();
            }
        });
        return super.onCreateDialog(savedInstanceState);
    }
}
