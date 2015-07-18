package com.example.henrique.list.Service.registration;

import android.content.Intent;

import com.example.henrique.list.Service.local.LocalLoginService;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;
import com.google.android.gms.iid.InstanceIDListenerService;

import br.com.motiserver.dto.CustomerDTO;

/**
 * Created by michael on 25/06/2015.
 */
public class InstanceIDListenerServiceImpl extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        // CALL A INTENT TO REGISTER A TOKEN CLOUD MESSAGING
        LocalLoginService localLoginService = new LocalLoginService(getApplicationContext());
        CustomerDTO customerDTO = localLoginService.login();
        if (customerDTO != null) {
            Intent intent = new Intent(this, TokenRegistrationIntentService.class);
            intent.putExtra(SessionAttributes.CUSTOMER, customerDTO);
            startService(intent);
        }
    }
}
