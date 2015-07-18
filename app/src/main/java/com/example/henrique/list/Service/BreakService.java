package com.example.henrique.list.Service;

import android.os.AsyncTask;

import com.example.henrique.list.Utilidade_Publica.ServiceException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.motiserver.dto.BreakDTO;
import br.com.motiserver.dto.WrapperDTO;

public class BreakService extends GenericService {
    /**
     * ******************
     * ****  METHODS  *****
     * *******************
     */
    public List<BreakDTO> findUpcomingPersonalInterval(Long professionalId, Calendar timeNow) throws ServiceException {
        WrapperDTO<List<BreakDTO>> wrapperDTO = null;
        try {
            wrapperDTO = new FindUpcomingPersonalInterval().execute(professionalId, timeNow).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    public BreakDTO save(BreakDTO breakDTO) throws ServiceException {
        WrapperDTO<BreakDTO> wrapperDTO = null;
        try {
            wrapperDTO = new Save().execute(breakDTO).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    /**
     * ************************
     * ****  INNER CLASSES  *****
     * *************************
     */
    private class FindUpcomingPersonalInterval extends AsyncTask<Object, Void, WrapperDTO<List<BreakDTO>>> {
        @Override
        protected WrapperDTO<List<BreakDTO>> doInBackground(Object... objects) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("professionalId", (Long) objects[0]);
            vars.put("timeNow", convertCalendarToString((Calendar) objects[1]));
            ParameterizedTypeReference<WrapperDTO<List<BreakDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<BreakDTO>>>() {
            };

            // EXECUTE
            ResponseEntity<WrapperDTO<List<BreakDTO>>> response = (ResponseEntity<WrapperDTO<List<BreakDTO>>>) restTemplate
                    .exchange(URLConstants.BREAK_FIND_UPCOMING_PERSONAL_INTERVAL, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }

    private class Save extends AsyncTask<BreakDTO, Void, WrapperDTO<BreakDTO>> {
        @Override
        protected WrapperDTO<BreakDTO> doInBackground(BreakDTO... breakDTO) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HttpEntity<BreakDTO> httpEntity = new HttpEntity<BreakDTO>(breakDTO[0]);
            ParameterizedTypeReference<WrapperDTO<BreakDTO>> responseType = new ParameterizedTypeReference<WrapperDTO<BreakDTO>>() {};

            // EXECUTE
            ResponseEntity<WrapperDTO<BreakDTO>> response = (ResponseEntity<WrapperDTO<BreakDTO>>) restTemplate
                    .exchange(URLConstants.BREAK_SAVE, HttpMethod.POST, httpEntity, responseType);
            return response.getBody();
        }
    }
}
