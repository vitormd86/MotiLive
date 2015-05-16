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

import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.dto.WrapperDTO;

public class ProfessionalService {
    /*********************
     *****  METHODS  *****
     *********************/
    public ProfessionalDTO find(Long id) throws ServiceException {
        WrapperDTO<ProfessionalDTO> wrapperDTO = null;
        try {
            wrapperDTO = new Find().execute(id).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    public List<ProfessionalDTO> findAll() throws ServiceException {
        WrapperDTO<List<ProfessionalDTO>> wrapperDTO = null;
        try {
            wrapperDTO = new FindAll().execute().get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    public List<ProfessionalDTO> findProfessionalContactsByCustomerId(Long customerId) throws ServiceException {
        WrapperDTO<List<ProfessionalDTO>> wrapperDTO = null;
        try {
            wrapperDTO = new FindProfessionalContactsByCustomerId().execute(customerId).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    public ProfessionalDTO save(ProfessionalDTO professionalDTO) throws ServiceException {
        WrapperDTO<ProfessionalDTO> wrapperDTO = null;
        try {
            wrapperDTO = new Save().execute(professionalDTO).get();
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
    private class Find extends AsyncTask<Long, Void, WrapperDTO<ProfessionalDTO>> {
        @Override
        protected WrapperDTO<ProfessionalDTO> doInBackground(Long... id) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("id", id[0]);
            ParameterizedTypeReference<WrapperDTO<ProfessionalDTO>> responseType = new ParameterizedTypeReference<WrapperDTO<ProfessionalDTO>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<ProfessionalDTO>> response = (ResponseEntity<WrapperDTO<ProfessionalDTO>>) restTemplate
                    .exchange(URLConstants.PROFESSIONAL_FIND, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }

    private class FindAll extends AsyncTask<Void, Void, WrapperDTO<List<ProfessionalDTO>>> {
        @Override
        protected WrapperDTO<List<ProfessionalDTO>> doInBackground(Void... params) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ParameterizedTypeReference<WrapperDTO<List<ProfessionalDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<ProfessionalDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<ProfessionalDTO>>> response = (ResponseEntity<WrapperDTO<List<ProfessionalDTO>>>) restTemplate
                    .exchange(URLConstants.PROFESSIONAL_FIND_ALL, HttpMethod.GET, null, responseType);
            return response.getBody();
        }
    }

    private class FindProfessionalContactsByCustomerId extends AsyncTask<Long, Void, WrapperDTO<List<ProfessionalDTO>>> {
        @Override
        protected WrapperDTO<List<ProfessionalDTO>> doInBackground(Long... customerId) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("customerId", customerId[0]);
            ParameterizedTypeReference<WrapperDTO<List<ProfessionalDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<ProfessionalDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<ProfessionalDTO>>> response = (ResponseEntity<WrapperDTO<List<ProfessionalDTO>>>) restTemplate
                    .exchange(URLConstants.PROFESSIONAL_FIND_CONTACTS_BY_CUSTOMER_ID, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }

    private class Save extends AsyncTask<ProfessionalDTO, Void, WrapperDTO<ProfessionalDTO>> {
        @Override
        protected WrapperDTO<ProfessionalDTO> doInBackground(ProfessionalDTO... professionalDTO) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HttpEntity<ProfessionalDTO> httpEntity = new HttpEntity<ProfessionalDTO>(professionalDTO[0]);
            ParameterizedTypeReference<WrapperDTO<ProfessionalDTO>> responseType = new ParameterizedTypeReference<WrapperDTO<ProfessionalDTO>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<ProfessionalDTO>> response = (ResponseEntity<WrapperDTO<ProfessionalDTO>>) restTemplate
                    .exchange(URLConstants.PROFESSIONAL_SAVE, HttpMethod.POST, httpEntity, responseType);
            return response.getBody();
        }
    }
}
