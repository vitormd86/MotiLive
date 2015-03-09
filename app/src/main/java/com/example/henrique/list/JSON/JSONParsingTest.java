package com.example.henrique.list.JSON;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henrique.list.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class JSONParsingTest extends Activity {

    static String yahooStockInfo = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22MSFT%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    static String  stockSymbol =  "";
    static String  stockDaysLow =  "";
    static String  stockDaysHigh =  "";
    static String  stockChange =  "";


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_parser);

        new MyAsyncTask().execute();
    }



    private class MyAsyncTask extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... params) {

            //Cria um Http Client
            DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
            HttpPost httppost  = new HttpPost(yahooStockInfo);
            System.out.println(yahooStockInfo);

            //Avisa que eh JSON
            httppost.setHeader("Content-Type", "application/json");

            //Inicializa as Variaveis
            InputStream inputStream =null;
            String  result = null;
            System.out.println ("fez atribuições");




            try{

                //cria uma string a partir dos dados recebidos
                //guarda a resposta em response
                HttpResponse response;
                response = httpClient.execute(httppost);  // nao ta devolvendo sabe-se la pq

                //guarda numa entidade http
                HttpEntity entity  = response.getEntity();
                // joga a entidade na Stream
                inputStream = entity.getContent();
                //guarda no buffer  de  leitura
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"),8);
                //constroi  a STRING
                StringBuilder theStringBuilder = new StringBuilder();
    
                // cria a String e joga na line
                String line = null;
                while((line = reader.readLine()) != null  ){

                    theStringBuilder.append(line + "\n");
                }
                result = theStringBuilder.toString();
            }


            catch (Exception e){
                e.printStackTrace();
                System.out.println ("sem buffer no result");


            }
            finally {
                try{
                    if(inputStream !=null)inputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }


            JSONObject jsonObject;


            try{
               /* result =result.substring(7);
                result = result.substring(0,result.length()-2);*/
                //Log.v("JSONParser RESULT", result);

                //atribui a String ao JSONObject
                jsonObject = new JSONObject(result);
                JSONObject queryJSONbject = jsonObject.getJSONObject("query");
                JSONObject resultsJSONbject = queryJSONbject.getJSONObject("results");
                JSONObject quoteJSONbject = resultsJSONbject.getJSONObject("quote");


                stockSymbol =quoteJSONbject.getString("symbol");
                stockDaysHigh =quoteJSONbject.getString("DaysHigh");
                stockDaysLow =quoteJSONbject.getString("DaysLow");
                stockChange =quoteJSONbject.getString("Change");


            }catch (JSONException e)
            {
                e.printStackTrace();
                Log.i("sem JSONObject","");


            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            TextView  line1 = (TextView) findViewById(R.id.line1);
            TextView  line2 = (TextView) findViewById(R.id.line2);
            TextView  line3 = (TextView) findViewById(R.id.line3);

            line1.setText("Stock: " + stockSymbol + " : " + stockChange );
            line2.setText("Days Low: " + stockDaysLow);
            line3.setText("Days High: " + stockDaysHigh);
            Toast.makeText(JSONParsingTest.this, "Pegou os dados no webserver", Toast.LENGTH_LONG).show();


        }
    }

}


