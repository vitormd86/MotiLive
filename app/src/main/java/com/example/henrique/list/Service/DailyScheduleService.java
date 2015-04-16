package com.example.henrique.list.Service;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.motiserver.dto.DailyScheduleDTO;

public class DailyScheduleService {

    public List<DailyScheduleDTO> findAllByProfessionalId(Long professionalId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("professionalId", professionalId);
        List<DailyScheduleDTO> list = restTemplate.getForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.DAILY_SCHEDULE_FIND_ALL_BY_PROFESSIONAL_ID, new ArrayList<DailyScheduleDTO>().getClass(), vars);
        return list;
    }

    public DailyScheduleDTO save(DailyScheduleDTO dailyScheduleDTO) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        dailyScheduleDTO = restTemplate.postForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.DAILY_SCHEDULE_SAVE, dailyScheduleDTO, DailyScheduleDTO.class);
        return dailyScheduleDTO;
    }

    public List<DailyScheduleDTO> saveAll(List<DailyScheduleDTO> list) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        list = restTemplate.postForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.DAILY_SCHEDULE_SAVE_ALL, list, new ArrayList<DailyScheduleDTO>().getClass());
        return list;
    }
}
