package com.example.henrique.list.Service;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.motiserver.dto.ProfessionalDTO;

public class ProfessionalService {

    public ProfessionalDTO find(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("id", id);
        ProfessionalDTO professionalDTO = restTemplate.getForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.PROFESSIONAL_FIND, ProfessionalDTO.class, vars);
        return professionalDTO;
    }

    public List<ProfessionalDTO> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        List<ProfessionalDTO> list = restTemplate.getForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.PROFESSIONAL_FIND_ALL, new ArrayList<ProfessionalDTO>().getClass());
        return list;
    }

    public void findProfessionalContactsByCustomerId(Long customerId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("customerId", customerId);
        List<ProfessionalDTO> list = restTemplate.getForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.PROFESSIONAL_FIND_CONTACTS_BY_CUSTOMER_ID, new ArrayList<ProfessionalDTO>().getClass(), vars);
    }

    public ProfessionalDTO save(ProfessionalDTO professionalDTO) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        professionalDTO = restTemplate.postForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.PROFESSIONAL_SAVE, professionalDTO, ProfessionalDTO.class);
        return professionalDTO;
    }
}
