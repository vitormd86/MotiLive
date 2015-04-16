package com.example.henrique.list.Service;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.motiserver.dto.SchedulingDTO;

public class SchedulingService {

    public List<SchedulingDTO> findAllByCustomerId(Long customerId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("customerId", customerId);
        List<SchedulingDTO> list = restTemplate.getForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.SCHEDULING_FIND_ALL_BY_CUSTOMER_ID, new ArrayList<SchedulingDTO>().getClass(), vars);
        return list;
    }

    public List<SchedulingDTO> findAllByProfessionalId(Long professionalId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("professionalId", professionalId);
        List<SchedulingDTO> list = restTemplate.getForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.SCHEDULING_FIND_ALL_BY_PROFESSIONAL_ID, new ArrayList<SchedulingDTO>().getClass(), vars);
        return list;
    }

    public SchedulingDTO save(SchedulingDTO schedulingDTO) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        schedulingDTO = restTemplate.postForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.SCHEDULING_SAVE, schedulingDTO, SchedulingDTO.class);
        return schedulingDTO;
    }
}
