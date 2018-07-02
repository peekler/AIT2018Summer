package hu.aut.android.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirPlaneModeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean mode = intent.getBooleanExtra("state", true);

        Toast.makeText(context, "AIRPLANE: "+mode, Toast.LENGTH_SHORT).show();
    }
}
