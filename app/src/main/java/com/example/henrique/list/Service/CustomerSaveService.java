package com.example.henrique.list.Service;

import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import br.com.motiserver.dto.CustomerDTO;

public class CustomerSaveService extends AsyncTask<CustomerDTO, Void, CustomerDTO> {
    @Override
    protected CustomerDTO doInBackground(CustomerDTO... params) {
        CustomerDTO customerDTOResposta = new CustomerDTO();
        try {
            RestTemplate restTemplate = new RestTemplate();
            customerDTOResposta = params[0];
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            customerDTOResposta = restTemplate.postForObject(URLConstants.JSON_SERVER_URL +
                    URLConstants.CUSTOMER_SAVE, customerDTOResposta, CustomerDTO.class);
            System.out.println("conectou");
            return customerDTOResposta;
        } catch (Exception e) {
            customerDTOResposta = null;
            System.out.println("nao conectou");
            e.printStackTrace();
        }
        return customerDTOResposta;
    }

    protected void onPostExecute(CustomerDTO Result) {
        super.onPostExecute(Result);
        // tela de carregamento
        try {
            if (Result != null) {

            } else {
                System.out.println("Nao conseguiu fazer post execute( mudar de tela");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


