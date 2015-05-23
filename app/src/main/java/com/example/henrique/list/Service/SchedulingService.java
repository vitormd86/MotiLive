package com.example.henrique.list.Service;

import android.os.AsyncTask;

import com.example.henrique.list.Utilidade_Publica.ServiceException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.motiserver.dto.SchedulingDTO;
import br.com.motiserver.dto.WrapperDTO;

public class SchedulingService extends GenericService {
    /*********************
     *****  METHODS  *****
     *********************/
    public List<SchedulingDTO> findAllByCustomerId(Long customerId) throws ServiceException {
        WrapperDTO<List<SchedulingDTO>> wrapperDTO = null;
        try {
            wrapperDTO = new FindAllByCustomerId().execute(customerId).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    public List<SchedulingDTO> findAllByProfessionalId(Long professionalId) throws ServiceException {
        WrapperDTO<List<SchedulingDTO>> wrapperDTO = null;
        try {
            wrapperDTO = new FindAllByProfessionalId().execute(professionalId).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    public SchedulingDTO save(SchedulingDTO schedulingDTO) throws ServiceException {
        WrapperDTO<SchedulingDTO> wrapperDTO = null;
        try {
            wrapperDTO = new Save().execute(schedulingDTO).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    /***************************
     *****  INNER CLASSES  *****
     ***************************/
    private class FindAllByCustomerId extends AsyncTask<Long, Void, WrapperDTO<List<SchedulingDTO>>> {
        @Override
        protected WrapperDTO<List<SchedulingDTO>> doInBackground(Long... customerId) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("customerId", customerId[0]);
            ParameterizedTypeReference<WrapperDTO<List<SchedulingDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<SchedulingDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<SchedulingDTO>>> response = (ResponseEntity<WrapperDTO<List<SchedulingDTO>>>) restTemplate
                    .exchange(URLConstants.SCHEDULING_FIND_ALL_BY_CUSTOMER_ID, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }

    private class FindAllByProfessionalId extends AsyncTask<Long, Void, WrapperDTO<List<SchedulingDTO>>> {
        @Override
        protected WrapperDTO<List<SchedulingDTO>> doInBackground(Long... professionalId) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("professionalId", professionalId[0]);
            ParameterizedTypeReference<WrapperDTO<List<SchedulingDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<SchedulingDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<SchedulingDTO>>> response = (ResponseEntity<WrapperDTO<List<SchedulingDTO>>>) restTemplate
                    .exchange(URLConstants.SCHEDULING_FIND_ALL_BY_PROFESSIONAL_ID, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }

    private class Save extends AsyncTask<SchedulingDTO, Void, WrapperDTO<SchedulingDTO>> {
        @Override
        protected WrapperDTO<SchedulingDTO> doInBackground(SchedulingDTO... schedulingDTO) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HttpEntity<SchedulingDTO> httpEntity = new HttpEntity<SchedulingDTO>(schedulingDTO[0]);
            ParameterizedTypeReference<WrapperDTO<SchedulingDTO>> responseType = new ParameterizedTypeReference<WrapperDTO<SchedulingDTO>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<SchedulingDTO>> response = (ResponseEntity<WrapperDTO<SchedulingDTO>>) restTemplate
                    .exchange(URLConstants.SCHEDULING_SAVE, HttpMethod.POST, httpEntity, responseType);
            return response.getBody();
        }
    }
}
