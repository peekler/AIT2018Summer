package hu.ait.android.locationdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyProxyListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Boolean entering = intent.getBooleanExtra("KEY_ENTER", false);

        Toast.makeText(context,
                "PROXY ENTER: "+entering,
                Toast.LENGTH_SHORT).show();
    }
}
