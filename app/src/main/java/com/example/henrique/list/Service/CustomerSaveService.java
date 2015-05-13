package com.example.henrique.list.Service;

import android.os.AsyncTask;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.WrapperDTO;

public class CustomerSaveService  extends AsyncTask<CustomerDTO, Void, CustomerDTO> {

    @Override
    protected CustomerDTO doInBackground(CustomerDTO... customerDTOs) {
        // PREPARE
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("id", 1);
        ParameterizedTypeReference<WrapperDTO<CustomerDTO>> responseType = new ParameterizedTypeReference<WrapperDTO<CustomerDTO>>(){};

        // EXECUTE
        CustomerDTO customerDTO = null;
        ResponseEntity<WrapperDTO<CustomerDTO>> response = (ResponseEntity<WrapperDTO<CustomerDTO>>) restTemplate
                .exchange(URLConstants.JSON_SERVER_URL + URLConstants.CUSTOMER_SAVE, HttpMethod.GET, null, responseType, vars);
        if (response.getStatusCode() == HttpStatus.OK) {
            customerDTO = response.getBody().getObject();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        } else {
            response.getBody().getException().printStackTrace();
        }
        return customerDTO;
    }
}
