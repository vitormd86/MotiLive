package com.example.henrique.list.Service.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.example.henrique.list.R;
import com.example.henrique.list.Service.local.LocalLoginService;
import com.google.android.gms.gcm.GcmListenerService;

import br.com.motiserver.dto.CustomerDTO;

public class GcmListenerServiceImpl extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        // INIT VARIABLES
        LocalLoginService localLoginService = new LocalLoginService(getApplicationContext());
        CustomerDTO customerDTO = localLoginService.login();
        Long userId = new Long(data.getString("user_id"));

        // VERIFIES THAT THE MESSAGE IS FOR THE USER LOGGED IN
        if (customerDTO != null && customerDTO.getId().equals(userId)) {
            // GET THE SOUND NOTIFICATION
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            // CREATE THE NOTIFICATION MESSAGE
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.img_logo_small)
                    .setContentTitle(data.getString("title"))
                    .setContentText(data.getString("message"))
                    .setSound(soundUri);
            // SEND THE NOTIFICATION MESSAGE TO THE USER
            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notificationBuilder.build());
        }
    }
}
