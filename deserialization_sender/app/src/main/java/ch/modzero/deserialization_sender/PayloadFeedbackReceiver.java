package ch.modzero.deserialization_sender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ch.modzero.deserialization_sender.exploits.ExploitListSingleton;

/**
 * Created by work on 27.11.17.
 */

public class PayloadFeedbackReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String app_name = "IntentReceiver";

        String received_name = intent.getStringExtra(Intent.EXTRA_TEXT);
        Log.d(app_name, "receiving PayloadFeedback intent: " + received_name);

        ExploitListSingleton.getInstance().markPayloadSuccessful(received_name);
    }
}
