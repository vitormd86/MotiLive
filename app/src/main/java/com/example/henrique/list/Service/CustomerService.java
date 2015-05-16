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

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.WrapperDTO;

public class CustomerService {
    /*********************
     *****  METHODS  *****
     *********************/
    public CustomerDTO find(Long id) throws ServiceException {
        WrapperDTO<CustomerDTO> wrapperDTO = null;
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

    public List<CustomerDTO> findAll() throws ServiceException {
        WrapperDTO<List<CustomerDTO>> wrapperDTO = null;
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

    public List<CustomerDTO> findCustomerContactsByProfessionalId(Long professionalId) throws ServiceException {
        WrapperDTO<List<CustomerDTO>> wrapperDTO = null;
        try {
            wrapperDTO = new FindCustomerContactsByProfessionalId().execute(professionalId).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    public CustomerDTO save(CustomerDTO customerDTO) throws ServiceException {
        WrapperDTO<CustomerDTO> wrapperDTO = null;
        try {
            wrapperDTO = new Save().execute(customerDTO).get();
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
    private class Find extends AsyncTask<Long, Void, WrapperDTO<CustomerDTO>> {
        @Override
        protected WrapperDTO<CustomerDTO> doInBackground(Long... id) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("id", id[0]);
            ParameterizedTypeReference<WrapperDTO<CustomerDTO>> responseType = new ParameterizedTypeReference<WrapperDTO<CustomerDTO>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<CustomerDTO>> response = (ResponseEntity<WrapperDTO<CustomerDTO>>) restTemplate
                    .exchange(URLConstants.CUSTOMER_FIND, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }

    private class FindAll extends AsyncTask<Void, Void, WrapperDTO<List<CustomerDTO>>> {
        @Override
        protected WrapperDTO<List<CustomerDTO>> doInBackground(Void... params) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ParameterizedTypeReference<WrapperDTO<List<CustomerDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<CustomerDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<CustomerDTO>>> response = (ResponseEntity<WrapperDTO<List<CustomerDTO>>>) restTemplate
                    .exchange(URLConstants.CUSTOMER_FIND_ALL, HttpMethod.GET, null, responseType);
            return response.getBody();
        }
    }

    private class FindCustomerContactsByProfessionalId extends AsyncTask<Long, Void, WrapperDTO<List<CustomerDTO>>> {
        @Override
        protected WrapperDTO<List<CustomerDTO>> doInBackground(Long... professionalId) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("professionalId", professionalId[0]);
            ParameterizedTypeReference<WrapperDTO<List<CustomerDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<CustomerDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<CustomerDTO>>> response = (ResponseEntity<WrapperDTO<List<CustomerDTO>>>) restTemplate
                    .exchange(URLConstants.CUSTOMER_FIND_CONTACTS_BY_PROFESSIONAL_ID, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }

    private class Save extends AsyncTask<CustomerDTO, Void, WrapperDTO<CustomerDTO>> {
        @Override
        protected WrapperDTO<CustomerDTO> doInBackground(CustomerDTO... customerDTO) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HttpEntity<CustomerDTO> httpEntity = new HttpEntity<CustomerDTO>(customerDTO[0]);
            ParameterizedTypeReference<WrapperDTO<CustomerDTO>> responseType = new ParameterizedTypeReference<WrapperDTO<CustomerDTO>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<CustomerDTO>> response = (ResponseEntity<WrapperDTO<CustomerDTO>>) restTemplate
                    .exchange(URLConstants.CUSTOMER_SAVE, HttpMethod.POST, httpEntity, responseType);
            return response.getBody();
        }
    }
}
