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

import br.com.motiserver.dto.ServiceDTO;
import br.com.motiserver.dto.WrapperDTO;

public class ServiceService {
    /*********************
     *****  METHODS  *****
     *********************/
    public List<ServiceDTO> findAllByProfessionalId(Long professionalId) throws ServiceException {
        WrapperDTO<List<ServiceDTO>> wrapperDTO = null;
        try {
            wrapperDTO = new FindAllByProfessionalId().execute(professionalId).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getException() != null) {
            throw new ServiceException(wrapperDTO.getException());
        } else {
            return wrapperDTO.getObject();
        }
    }

    public ServiceDTO save(ServiceDTO serviceDTO) throws ServiceException {
        WrapperDTO<ServiceDTO> wrapperDTO = null;
        try {
            wrapperDTO = new Save().execute(serviceDTO).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getException() != null) {
            throw new ServiceException(wrapperDTO.getException());
        } else {
            return wrapperDTO.getObject();
        }
    }

    /***************************
     *****  INNER CLASSES  *****
     ***************************/
    private class FindAllByProfessionalId extends AsyncTask<Long, Void, WrapperDTO<List<ServiceDTO>>> {
        @Override
        protected WrapperDTO<List<ServiceDTO>> doInBackground(Long... professionalId) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("professionalId", professionalId[0]);
            ParameterizedTypeReference<WrapperDTO<List<ServiceDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<ServiceDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<ServiceDTO>>> response = (ResponseEntity<WrapperDTO<List<ServiceDTO>>>) restTemplate
                    .exchange(URLConstants.SERVICE_FIND_ALL_BY_PROFESSIONAL_ID, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }

    private class Save extends AsyncTask<ServiceDTO, Void, WrapperDTO<ServiceDTO>> {
        @Override
        protected WrapperDTO<ServiceDTO> doInBackground(ServiceDTO... serviceDTO) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HttpEntity<ServiceDTO> httpEntity = new HttpEntity<ServiceDTO>(serviceDTO[0]);
            ParameterizedTypeReference<WrapperDTO<ServiceDTO>> responseType = new ParameterizedTypeReference<WrapperDTO<ServiceDTO>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<ServiceDTO>> response = (ResponseEntity<WrapperDTO<ServiceDTO>>) restTemplate
                    .exchange(URLConstants.SERVICE_SAVE, HttpMethod.POST, httpEntity, responseType);
            return response.getBody();
        }
    }
}
