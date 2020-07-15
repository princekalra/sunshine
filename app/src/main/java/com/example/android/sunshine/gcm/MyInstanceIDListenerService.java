package com.example.android.sunshine.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * @author George Spiridakis <george@codeburrow.com>
 * @since May/24/2016.
 * ===================================================
 * ---------->    http://codeburrow.com    <----------
 * ===================================================
 */

/**
 * This service is what checks the validity of a registration token and
 * requests a new one if a token is not present or is expired.
 *
 * Google Play Services will automatically start the service and
 * invoke on token refresh when it detects that a new token is needed.
 */
public class MyInstanceIDListenerService extends InstanceIDListenerService {

    private static final String LOG_TAG = MyInstanceIDListenerService.class.getSimpleName();


    /**
     * Called if InstanceID token is updated.
     * This may occur if the security of the previous token had been compromised.
     * This call is initiated by the InstanceID provider.
     */
    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token.
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
