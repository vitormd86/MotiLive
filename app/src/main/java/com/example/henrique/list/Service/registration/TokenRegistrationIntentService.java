package com.example.henrique.list.Service.registration;

import android.app.IntentService;
import android.content.Intent;

import com.example.henrique.list.R;
import com.example.henrique.list.Service.LoginService;
import com.example.henrique.list.Service.local.LocalLoginService;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import br.com.motiserver.dto.CustomerDTO;

public class TokenRegistrationIntentService extends IntentService {

    private LoginService loginService = new LoginService();

    public TokenRegistrationIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // REGISTER TOKEN TO LOGGED USER
        try {
            CustomerDTO customerDTO = customerDTO = (CustomerDTO) intent.getSerializableExtra(SessionAttributes.CUSTOMER);
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE);
            loginService.registerTokenGCM(customerDTO.getId(), token);
        } catch(Exception e) {};
    }
}
