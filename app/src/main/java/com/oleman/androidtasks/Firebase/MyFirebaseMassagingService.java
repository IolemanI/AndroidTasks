package com.oleman.androidtasks.Firebase;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.oleman.androidtasks.R;
import com.oleman.androidtasks.tasks.Task4Activity;

public class MyFirebaseMassagingService extends FirebaseMessagingService{

    /**
     * Метод onMessageReceived выполняется при получении сообщения с сервера
     * @param remoteMessage хранит в себе всю информацию о полученом сообщении.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        sendNotifycation(remoteMessage.getNotification().getBody());
    }

    /**
     * Метод создает уведомление для отображения на устройстве.
     * @param messageBody
     */
    private void sendNotifycation(String messageBody) {
        Intent intent = new Intent(this, Task4Activity.class); // будет открываться MainActivity, но можно поставить любое
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);      // другое активити или даже ссылку.

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);  // звук уведомления: можно поставить звуки будильника, звонка и т.д.

        NotificationCompat.Builder notifycationBuilder = new NotificationCompat.Builder(this)   // notifycationBuilder занимается созданием уведомления.
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
            .setContentTitle(this.getString(R.string.app_name))
            .setContentText(messageBody)
            .setAutoCancel(true)    // автоматическое закрытие уведомления
            .setSound(defaultSoundUri);   // звук уведомления

        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); // используется для отправки сообщения
        // создание экземпляра NotificationManager

        notifyManager.notify(0, notifycationBuilder.build()); // показ уведомления
    }
}
