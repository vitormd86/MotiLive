package com.example.henrique.list.Service;

import android.os.AsyncTask;

import com.example.henrique.list.Utilidade_Publica.ServiceException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import br.com.motiserver.dto.PersonDTO;
import br.com.motiserver.dto.WrapperDTO;

public class LoginService {
    /*********************
     *****  METHODS  *****
     *********************/
    public PersonDTO login(String login, String password) throws ServiceException {
        WrapperDTO<PersonDTO> wrapperDTO = null;
        try {
            wrapperDTO = new Login().execute(login, password).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    public PersonDTO loginWithFacebook(String facebookLogin) throws ServiceException {
        WrapperDTO<PersonDTO> wrapperDTO = null;
        try {
            wrapperDTO = new LoginWithFacebook().execute(facebookLogin).get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (wrapperDTO.getErrorMessage() != null) {
            throw new ServiceException(wrapperDTO.getErrorMessage());
        } else {
            return wrapperDTO.getObject();
        }
    }

    public Boolean verifyExistingUser(String login) throws ServiceException {
        WrapperDTO<Boolean> wrapperDTO = null;
        try {
            wrapperDTO = new VerifyExistingUser().execute(login).get();
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
    private class Login extends AsyncTask<String, Void, WrapperDTO<PersonDTO>> {
        @Override
        protected WrapperDTO<PersonDTO> doInBackground(String... userAndPassword) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("login", userAndPassword[0]);
            vars.put("password", userAndPassword[1]);
            ParameterizedTypeReference<WrapperDTO<PersonDTO>> responseType = new ParameterizedTypeReference<WrapperDTO<PersonDTO>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<PersonDTO>> response = (ResponseEntity<WrapperDTO<PersonDTO>>) restTemplate
                    .exchange(URLConstants.LOGIN, HttpMethod.POST, null, responseType, vars);
            return response.getBody();
        }
    }

    private class LoginWithFacebook extends AsyncTask<String, Void, WrapperDTO<PersonDTO>> {
        @Override
        protected WrapperDTO<PersonDTO> doInBackground(String... facebookLogin) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("facebookLogin", facebookLogin[0]);
            ParameterizedTypeReference<WrapperDTO<PersonDTO>> responseType = new ParameterizedTypeReference<WrapperDTO<PersonDTO>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<PersonDTO>> response = (ResponseEntity<WrapperDTO<PersonDTO>>) restTemplate
                    .exchange(URLConstants.LOGIN_WITH_FACEBOOK, HttpMethod.POST, null, responseType, vars);
            return response.getBody();
        }
    }

    private class VerifyExistingUser extends AsyncTask<String, Void, WrapperDTO<Boolean>> {
        @Override
        protected WrapperDTO<Boolean> doInBackground(String... login) {
            // PREPARE
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("login", login[0]);
            ParameterizedTypeReference<WrapperDTO<Boolean>> responseType = new ParameterizedTypeReference<WrapperDTO<Boolean>>(){};

            // EXECUTE
            ResponseEntity<WrapperDTO<Boolean>> response = (ResponseEntity<WrapperDTO<Boolean>>) restTemplate
                    .exchange(URLConstants.LOGIN_VERIFY_EXISTING_USER, HttpMethod.GET, null, responseType, vars);
            return response.getBody();
        }
    }
}
