package com.example.henrique.list.Service;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.motiserver.dto.CustomerDTO;

public class CustomerService {

    public CustomerDTO find(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("id", id);
        CustomerDTO customerDTO = restTemplate.getForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.CUSTOMER_FIND, CustomerDTO.class, vars);
        return customerDTO;
    }

    public List<CustomerDTO> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        List<CustomerDTO> list = restTemplate.getForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.CUSTOMER_FIND_ALL, new ArrayList<CustomerDTO>().getClass());
        return list;
    }

    public void findCustomerContactsByProfessionalId(Long professionalId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("professionalId", professionalId);
        List<CustomerDTO> list = restTemplate.getForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.CUSTOMER_FIND_CONTACTS_BY_PROFESSIONAL_ID, new ArrayList<CustomerDTO>().getClass(), vars);
    }

    public CustomerDTO save(CustomerDTO customerDTO) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        customerDTO = restTemplate.postForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.CUSTOMER_SAVE, customerDTO, CustomerDTO.class);
        return customerDTO;
    }
}
