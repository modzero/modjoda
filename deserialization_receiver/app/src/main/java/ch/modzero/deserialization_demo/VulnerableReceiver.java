package ch.modzero.deserialization_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by work on 15.06.17.
 */

public class VulnerableReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String app_name = "IntentReceiver";
        Log.d(app_name, "received an intent");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Log.d(app_name, "intent has extra data");
            for (String extra : extras.keySet()) {
                Log.d(app_name, "processing extra " + extra);
                if (extra.equals("decoy")) {
                    Log.d(app_name, "sucessfully deserialized " + intent.getStringExtra(extra));
                }
            }
        }
    }
}
