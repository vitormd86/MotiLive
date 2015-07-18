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

import br.com.motiserver.dto.DailyScheduleDTO;
import br.com.motiserver.dto.WrapperDTO;

public class DailyScheduleService extends GenericService {
    /*********************
     *****  METHODS  *****
     *********************/
    public List<DailyScheduleDTO> findAllByProfessionalId(Long professionalId) throws ServiceException {
        WrapperDTO<List<DailyScheduleDTO>> wrapperDTO = null;
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

    public DailyScheduleDTO findByProfessionalIdAndDate(Long professionalId, Calendar date) throws ServiceException {
        WrapperDTO<DailyScheduleDTO> wrapperDTO = null;
        try {
            wrapperDTO = new FindByProfessionalIdAndDate().execute(professionalId, date).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    public List<DailyScheduleDTO> findAllByProfessionalIdAndPeriod(Long professionalId, Calendar initialDate, Calendar finalDate) throws ServiceException {
        WrapperDTO<List<DailyScheduleDTO>> wrapperDTO = null;
        try {
            wrapperDTO = new FindAllByProfessionalIdAndPeriod().execute(professionalId, initialDate, finalDate).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    public DailyScheduleDTO save(DailyScheduleDTO dailyScheduleDTO) throws ServiceException {
        WrapperDTO<DailyScheduleDTO> wrapperDTO = null;
        try {
            wrapperDTO = new Save().execute(dailyScheduleDTO).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    public List<DailyScheduleDTO> saveAll(List<DailyScheduleDTO> list) throws ServiceException {
        WrapperDTO<List<DailyScheduleDTO>> wrapperDTO = null;
        try {
            wrapperDTO = new SaveAll().execute(list).get();
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
    private class FindAllByProfessionalId extends AsyncTask<Long, Void, WrapperDTO<List<DailyScheduleDTO>>> {
        @Override
        protected WrapperDTO<List<DailyScheduleDTO>> doInBackground(Long... professionalId) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("professionalId", professionalId[0]);
            ParameterizedTypeReference<WrapperDTO<List<DailyScheduleDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<DailyScheduleDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<DailyScheduleDTO>>> response = (ResponseEntity<WrapperDTO<List<DailyScheduleDTO>>>) restTemplate
                    .exchange(URLConstants.DAILY_SCHEDULE_FIND_ALL_BY_PROFESSIONAL_ID, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }

    private class FindByProfessionalIdAndDate extends AsyncTask<Object, Void, WrapperDTO<DailyScheduleDTO>> {
        @Override
        protected WrapperDTO<DailyScheduleDTO> doInBackground(Object... params) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("professionalId", (Long) params[0]);
            Calendar date = (Calendar) params [1];
            vars.put("date", convertCalendarToString(date));
            ParameterizedTypeReference<WrapperDTO<DailyScheduleDTO>> responseType = new ParameterizedTypeReference<WrapperDTO<DailyScheduleDTO>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<DailyScheduleDTO>> response = (ResponseEntity<WrapperDTO<DailyScheduleDTO>>) restTemplate
                    .exchange(URLConstants.DAILY_SCHEDULE_FIND_FIND_BY_PROFESSIONAL_ID_AND_DATE, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }

    private class FindAllByProfessionalIdAndPeriod extends AsyncTask<Object, Void, WrapperDTO<List<DailyScheduleDTO>>> {
        @Override
        protected WrapperDTO<List<DailyScheduleDTO>> doInBackground(Object... params) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("professionalId",  (Long) params[0]);
            Calendar initialDate = (Calendar) params [1];
            vars.put("initialDate", convertCalendarToString(initialDate));
            Calendar finalDate = (Calendar) params [2];
            vars.put("finalDate", convertCalendarToString(finalDate));
            ParameterizedTypeReference<WrapperDTO<List<DailyScheduleDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<DailyScheduleDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<DailyScheduleDTO>>> response = (ResponseEntity<WrapperDTO<List<DailyScheduleDTO>>>) restTemplate
                    .exchange(URLConstants.DAILY_SCHEDULE_FIND_ALL_BY_PROFESSIONAL_ID_AND_PERIOD, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }

    private class Save extends AsyncTask<DailyScheduleDTO, Void, WrapperDTO<DailyScheduleDTO>> {
        @Override
        protected WrapperDTO<DailyScheduleDTO> doInBackground(DailyScheduleDTO... dailyScheduleDTO) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HttpEntity<DailyScheduleDTO> httpEntity = new HttpEntity<DailyScheduleDTO>(dailyScheduleDTO[0]);
            ParameterizedTypeReference<WrapperDTO<DailyScheduleDTO>> responseType = new ParameterizedTypeReference<WrapperDTO<DailyScheduleDTO>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<DailyScheduleDTO>> response = (ResponseEntity<WrapperDTO<DailyScheduleDTO>>) restTemplate
                    .exchange(URLConstants.DAILY_SCHEDULE_SAVE, HttpMethod.POST, httpEntity, responseType);
            return response.getBody();
        }
    }

    private class SaveAll extends AsyncTask<List<DailyScheduleDTO>, Void, WrapperDTO<List<DailyScheduleDTO>>> {
        @Override
        protected WrapperDTO<List<DailyScheduleDTO>> doInBackground(List<DailyScheduleDTO>... list) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HttpEntity<List<DailyScheduleDTO>> httpEntity = new HttpEntity<List<DailyScheduleDTO>>(list[0]);
            ParameterizedTypeReference<WrapperDTO<List<DailyScheduleDTO>>> responseType = new ParameterizedTypeReference<WrapperDTO<List<DailyScheduleDTO>>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<List<DailyScheduleDTO>>> response = (ResponseEntity<WrapperDTO<List<DailyScheduleDTO>>>) restTemplate
                    .exchange(URLConstants.DAILY_SCHEDULE_SAVE_ALL, HttpMethod.POST, httpEntity, responseType);
            return response.getBody();
        }
    }
}
