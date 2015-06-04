package com.example.henrique.list.Service;

import android.os.AsyncTask;

import com.example.henrique.list.Utilidade_Publica.ServiceException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.motiserver.dto.ServiceSchedulingDTO;
import br.com.motiserver.dto.WrapperDTO;

/**
 * Created by michael on 03/06/2015.
 */
public class ServiceSchedulingService extends GenericService {
    /*********************
     *****  METHODS  *****
     *********************/
    public List<ServiceSchedulingDTO> findAllByCustomerId(Long customerId) throws ServiceException {
        WrapperDTO<List<ServiceSchedulingDTO>> wrapperDTO = null;
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

    public List<ServiceSchedulingDTO> findAllByProfessionalId(Long professionalId) throws ServiceException {
        WrapperDTO<List<ServiceSchedulingDTO>> wrapperDTO = null;
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

    public List<ServiceSchedulingDTO> findUpcomingSchedulingByCustomerId(Long customerId) throws ServiceException {
        WrapperDTO<List<ServiceSchedulingDTO>> wrapperDTO = null;
        try {
            wrapperDTO = new FindUpcomingSchedulingByCustomerId().execute(customerId).get();
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
    private class FindAllByCustomerId extends AsyncTask<Long, Void, WrapperDTO<List<ServiceSchedulingDTO>>> {
        @Override
        protected WrapperDTO<List<ServiceSchedulingDTO>> doInBackground(Long... customerId) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("customerId", customerId[0]);
            ParameterizedTypeReference<WrapperDTO<List<ServiceSchedulingDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<ServiceSchedulingDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<ServiceSchedulingDTO>>> response = (ResponseEntity<WrapperDTO<List<ServiceSchedulingDTO>>>) restTemplate
                    .exchange(URLConstants.SERVICE_SCH_FIND_ALL_BY_CUSTOMER_ID, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }

    private class FindAllByProfessionalId extends AsyncTask<Long, Void, WrapperDTO<List<ServiceSchedulingDTO>>> {
        @Override
        protected WrapperDTO<List<ServiceSchedulingDTO>> doInBackground(Long... professionalId) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("professionalId", professionalId[0]);
            ParameterizedTypeReference<WrapperDTO<List<ServiceSchedulingDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<ServiceSchedulingDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<ServiceSchedulingDTO>>> response = (ResponseEntity<WrapperDTO<List<ServiceSchedulingDTO>>>) restTemplate
                    .exchange(URLConstants.SERVICE_SCH_FIND_ALL_BY_PROFESSIONAL_ID, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }

    private class FindUpcomingSchedulingByCustomerId extends AsyncTask<Long, Void, WrapperDTO<List<ServiceSchedulingDTO>>> {
        @Override
        protected WrapperDTO<List<ServiceSchedulingDTO>> doInBackground(Long... customerId) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("customerId", customerId[0]);
            ParameterizedTypeReference<WrapperDTO<List<ServiceSchedulingDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<ServiceSchedulingDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<ServiceSchedulingDTO>>> response = (ResponseEntity<WrapperDTO<List<ServiceSchedulingDTO>>>) restTemplate
                    .exchange(URLConstants.SERVICE_SCH_FIND_UPCOMING_SCHEDULING_BY_CUSTOMER_ID, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }
}
