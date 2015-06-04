package com.example.henrique.list.Service;

import android.os.AsyncTask;

import com.example.henrique.list.Utilidade_Publica.ServiceException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import br.com.motiserver.dto.SchedulingDTO;
import br.com.motiserver.dto.WrapperDTO;

public class SchedulingService extends GenericService {
    /*********************
     *****  METHODS  *****
     *********************/
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
