package com.example.henrique.list.Service;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import br.com.motiserver.dto.PersonDTO;

public class LoginService {

    public PersonDTO login(String login, String password) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("login", login);
        vars.put("password", password);
        PersonDTO personDTO = restTemplate.postForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.LOGIN, null, PersonDTO.class, vars);
        return personDTO;
    }

    public PersonDTO loginWithFacebook(String facebookLogin) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("facebookLogin", facebookLogin);
        PersonDTO personDTO = restTemplate.postForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.LOGIN_WITH_FACEBOOK, null, PersonDTO.class, vars);
        return personDTO;
    }

    public boolean verifyExistingUser(String login) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("login", login);
        Boolean bool = restTemplate.getForObject(URLConstants.JSON_SERVER_URL +
            URLConstants.LOGIN_VERIFY_EXISTING_USER, Boolean.class, vars);
        return bool;
    }
}
