package com.example.henrique.list.Service;

import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import br.com.motiserver.dto.CustomerDTO;

public class CustomerSaveService  extends AsyncTask<CustomerDTO, Void, CustomerDTO> {
    CustomerDTO customerDTOresposta = new CustomerDTO();


    @Override
    protected CustomerDTO doInBackground(CustomerDTO... params) {

            try {
                customerDTOresposta = params[0];
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                customerDTOresposta = restTemplate.postForObject(URLConstants.JSON_SERVER_URL +  URLConstants.CUSTOMER_SAVE, params, CustomerDTO.class);
                System.out.println("conectou");
                return customerDTOresposta;
            } catch (Exception e) {
                System.out.println("nao conectou");
                e.printStackTrace();
            }
            return null;
        }

//        protected void onPostExecute(CustomerDTO Result) {
//            super.onPostExecute(Result);
//            // tela de carregamento
//            try {
//                if (Result != null) {
//                    Intent i = new Intent(CustProfile_5.this, CustDrawerMenu_10.class);
//                    startActivity(i);
//                } else {
//                    System.out.println("Nao conseguiu fazer post execute( mudar de tela");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

}
