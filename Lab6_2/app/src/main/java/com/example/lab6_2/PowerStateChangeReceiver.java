package com.example.lab6_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerStateChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (context == null) return;
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, R.string.power_connected, Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().endsWith(Intent.ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, R.string.power_disconnected, Toast.LENGTH_LONG).show();
        }
    }
}
