package com.automsg.sss.automsg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

/**
 * Created by SSS on 3/19/2018.
 */

public class ReceiveMessage extends BroadcastReceiver {
    SQLiteDatabase db;
    String otext;
    @Override
    public void onReceive(Context context, Intent intent)
    {
        db = context.openOrCreateDatabase("automessage",Context.MODE_PRIVATE,null);
        Bundle bundle = intent.getExtras();
        Object[] pdusObj = (Object[])bundle.get("pdus");
        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[])pdusObj[0]);
        String phoneNumber = currentMessage.getDisplayOriginatingAddress();
        String message = currentMessage.getDisplayMessageBody();
        Cursor cur = db.rawQuery("select * from message where itext='"+message+"'",null);
        if(cur.getCount()>0)
        {
            cur.moveToFirst();
            otext = cur.getString(1);
            cur.close();
        }

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber,null,otext,null,null);
    }
}
