package bhoomii.max.larcata.services;

import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Bhoomii on 12/9/2017.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("message_receive",remoteMessage.getNotification().toString());
    }
}
