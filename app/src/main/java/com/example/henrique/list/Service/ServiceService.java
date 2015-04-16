package com.example.henrique.list.Service;

import android.app.Service;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.motiserver.dto.ServiceDTO;

public class ServiceService {

    public List<ServiceDTO> findAllByProfessionalId(Long professionalId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("professionalId", professionalId);
        List<ServiceDTO> list = restTemplate.getForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.SERVICE_FIND_ALL_BY_PROFESSIONAL_ID, new ArrayList<ServiceDTO>().getClass(), vars);
        return list;
    }

    public ServiceDTO save(ServiceDTO serviceDTO) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        serviceDTO = restTemplate.postForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.SERVICE_SAVE, serviceDTO, ServiceDTO.class);
        return serviceDTO;
    }
}
