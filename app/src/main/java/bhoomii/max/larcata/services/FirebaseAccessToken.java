package bhoomii.max.larcata.services;

import android.content.Intent;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseAccessToken extends FirebaseInstanceIdService {

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d("access_token", FirebaseInstanceId.getInstance().getToken());
    }
}
