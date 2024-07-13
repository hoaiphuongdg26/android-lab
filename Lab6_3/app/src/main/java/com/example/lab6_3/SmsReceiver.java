package com.example.lab6_3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.ArrayList;

public class SmsReceiver extends BroadcastReceiver {
    public static final String SMS_FORWARD_BROADCAST_RECEIVER = "sms_forward_broadcast_receiver";
    public static final String SMS_MESSAGE_ADDRESS_KEY = "sms_messages_key";

    @Override
    public void onReceive(Context context, Intent intent) {
        String queryString = "Are you OK?".toLowerCase();

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                if (android.os.Build.VERSION.SDK_INT >= 23) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], "");
                } else {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            // Create ArrayList of OriginatingAddress of messa
            ArrayList<String> addresses = new ArrayList<>();

            for (SmsMessage message : messages) {
                if (message.getMessageBody().toLowerCase().contains(queryString)) {
                    addresses.add(message.getOriginatingAddress());
                }
            }
            if (addresses.size() > 0) {
                if (!MainActivity.isRunning) {
                    Intent iMain = new Intent(context,MainActivity.class);
                    iMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    iMain.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                    context.startActivity(iMain);
                    context.sendBroadcast(iMain);
                } else {
                    Intent iForwardBroadcastReceiver = new Intent(SMS_FORWARD_BROADCAST_RECEIVER);
                    iForwardBroadcastReceiver.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                    context.sendBroadcast(iForwardBroadcastReceiver);
                }
            }
        }
    }
}
