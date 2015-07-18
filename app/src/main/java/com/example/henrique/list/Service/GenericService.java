package com.example.henrique.list.Service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

abstract class GenericService {

    protected Context context;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

    protected String convertCalendarToString(Calendar calendar) {
        String dateString = null;
        if (calendar != null) {
            try {
                dateString = sdf.format(calendar.getTime());
            } catch(Exception e) {}
        }
        return dateString;
    }

    protected abstract class GenericAsyncTask<Params, Progress, Result>
            extends AsyncTask<Params, Progress, Result> {

        private ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            ProgressDialog progress = new ProgressDialog(context);
            progress.setTitle("Processando...");
            progress.setMessage("Por favor aguarde.");
            progress.setCancelable(false);
            progress.setIndeterminate(true);
            progress.show();
        }

        @Override
        protected void onPostExecute(Result result) {
            if(progress!= null)
                progress.dismiss();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            if(progress!= null)
                progress.dismiss();
        }

        @Override
        protected void onCancelled(Result result) {
            super.onCancelled(result);
            if(progress!= null)
                progress.dismiss();
        }
    }
}
