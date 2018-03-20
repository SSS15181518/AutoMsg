package com.automsg.sss.automsg;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Show extends AppCompatActivity {
    ListView lv;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        db = openOrCreateDatabase("automessage",MODE_PRIVATE,null);
        lv = (ListView)findViewById(R.id.lv);

        ArrayList a1 = new ArrayList();
        Cursor cur = db.rawQuery("select * from message",null);
        while(cur.moveToNext())
        {
            a1.add(cur.getString(0)+"\n"+cur.getString(1));
        }
        cur.close();
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1,a1);
        lv.setAdapter(aa);
    }
}
