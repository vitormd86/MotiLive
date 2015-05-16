package com.example.henrique.list.Service;

import android.os.AsyncTask;

import com.example.henrique.list.Utilidade_Publica.ServiceException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import br.com.motiserver.dto.ProfessionDTO;
import br.com.motiserver.dto.WrapperDTO;

/**
 * Created by michael on 16/05/2015.
 */
public class ProfessionService {
    /*********************
     *****  METHODS  *****
     *********************/
    public List<ProfessionDTO> findAll() throws ServiceException {
        WrapperDTO<List<ProfessionDTO>> wrapperDTO = null;
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

    /***************************
     *****  INNER CLASSES  *****
     ***************************/
    private class FindAll extends AsyncTask<Void, Void, WrapperDTO<List<ProfessionDTO>>> {
        @Override
        protected WrapperDTO<List<ProfessionDTO>> doInBackground(Void... params) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ParameterizedTypeReference<WrapperDTO<List<ProfessionDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<ProfessionDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<ProfessionDTO>>> response = (ResponseEntity<WrapperDTO<List<ProfessionDTO>>>) restTemplate
                    .exchange(URLConstants.PROFESSION_FIND_ALL, HttpMethod.GET, null, responseType);
            return response.getBody();
        }
    }
}
