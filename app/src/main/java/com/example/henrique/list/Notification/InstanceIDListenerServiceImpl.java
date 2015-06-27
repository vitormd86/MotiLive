package com.example.henrique.list.Notification;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by michael on 25/06/2015.
 */
public class InstanceIDListenerServiceImpl extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        // TODO
        System.out.println("chega aqui");
        System.out.println("chega aqui");
    }
}
