package com.example.henrique.list.JSON;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by htamashiro on 3/14/15.
 */
public class JSONPut extends Activity{


    //aqui colocamos o endereço JSON pra recuperar os dados desejados
    static String enderecoJSON = "";
    Activity activityatual ;

    public JSONPut(Activity activity){
        activityatual = activity;

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.json_parser);

        //Teremos que incluir um desses em cada tela
        new HttpRequestTask().execute();
    }



    private class HttpRequestTask extends AsyncTask<Void, Void, Object> {
        @Override
        protected Object doInBackground(Void... params) {
            try {
                final String url = "endereco pego no JSONCTES";

                //cria um objeto do tipo RestTemplate
                RestTemplate restTemplate = new RestTemplate();

                // adiciona o  jackson converter ao converter do restTemplate
                restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

                //transforma o objeto q recebemos em jSON para o objetio greeting
                Object object = restTemplate.getForObject(url, Object.class);// atribui as classes a uma determinada activity
                return object;
            } catch (Exception e) {
                Log.e("activity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object object) {
            //atualiza a interface se precisar

        }

    }




}
