package com.example.desktop.stechno;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.support.constraint.Constraints.TAG;

public class MyFirebaseInstantIDService extends FirebaseInstanceIdService {
    private static final String TAG = "RegIntentService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token is" +token);

    }
}
