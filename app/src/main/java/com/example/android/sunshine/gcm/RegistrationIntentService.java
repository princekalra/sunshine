package com.example.android.sunshine.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.android.sunshine.MainActivity;
import com.example.android.sunshine.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * @author George Spiridakis <george@codeburrow.com>
 * @since May/24/2016.
 * ===================================================
 * ---------->    http://codeburrow.com    <----------
 * ===================================================
 */

/**
 * To get a new token, we have a registration intent service
 * which will communicate with the GCM servers off of the main thread.
 *
 * Keeping the registration work in its own service instead of the instanceIDListenerService
 * means that it can be invoked only when needed,
 * which mean not always overlap with a token refresh
 * such as the initial registration when the app starts.
 *
 * It also makes for cleaner code so that you can easily track buys and make future changes.
 */
public class RegistrationIntentService extends IntentService {

    private static final String LOG_TAG = RegistrationIntentService.class.getSimpleName();

    public RegistrationIntentService(){
        super(LOG_TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            // In the (unlikely) event that multiple refresh operations occur simultaneously.
            // Ensure that they are processed sequentially.
            synchronized (LOG_TAG){
                // Initially this call goes out to the network to retrieve the token,
                // subsequent calls are local.
                InstanceID instanceID = InstanceID.getInstance(this);

                // ToDo: gcm_default sender ID comes from the API console
                String senderId = getString(R.string.gcm_defaultSenderId);
                if (senderId.length() != 0){
                    String token = instanceID.getToken(senderId,
                            GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                    sendRegistrationToServer(token);
                }

                // You should store a boolean that indicates whether the generated token
                // has been sent to you server.
                // If the boolean is false, send the token to your server,
                // otherwise your server should have already received the token.
                sharedPreferences.edit().putBoolean(MainActivity.SENT_TOKEN_TO_SERVER, true).apply();
            }
        }  catch (Exception e){
            Log.e(LOG_TAG, "Failed to complete token refresh", e);

            // If an exception happens while fetching the new token or updating our registration
            // data on a third-party server, this ensure that we'll attempt the update at a
            // later time.
            sharedPreferences.edit().putBoolean(MainActivity.SENT_TOKEN_TO_SERVER, false).apply();
        }
    }

    /**
     * Normally, you would want to persist the registration to third-party servers.
     * Because we do not have a server, and are faking it with a website,
     * you'll want to log the token instead.
     * That way you can see the value in logcat, and note it for future use in the website.
     *
     * @param token the new Token
     */
    private void sendRegistrationToServer(String token){
        Log.e(LOG_TAG, "GCM Registration Token: " + token);
    }
}
