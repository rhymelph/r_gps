package com.rhyme.r_gps;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;

public class RGpsHelper extends ContextWrapper {
    public static RGpsHelper instance;

    public RGpsHelper(Context base) {
        super(base);
    }

    static void initial(Context context) {
        instance = new RGpsHelper(context);
    }


    public boolean isOpen() {
        if (Build.VERSION.SDK_INT < 19) {
            LocationManager myLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            return myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } else {
            int state = Settings.Secure.getInt(this.getContentResolver(), Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_OFF);
            if (state == Settings.Secure.LOCATION_MODE_OFF) {
                return false;
            } else {
                return true;
            }
        }
    }

    public void openGPSSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            // The Android SDK doc says that the location settings activity
            // may not be found. In that case show the general settings.
            // General settings activity
            intent.setAction(Settings.ACTION_SETTINGS);
            try {
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
