package com.automsg.sss.automsg;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etIncoming,etOutgoing;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("automessage", MODE_PRIVATE, null);
        db.execSQL("create table if not exists message(itext varchar(100),otext varchar(100))");
        etIncoming = (EditText) findViewById(R.id.etIncoming);
        etOutgoing = (EditText) findViewById(R.id.etoutgoing);
        Button btnShow = (Button) findViewById(R.id.btnShow);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnShow.setOnClickListener(new MyClick());
        btnSave.setOnClickListener(new MyClick());
    }

        class MyClick implements View.OnClickListener
        {
            @Override
            public void onClick(View v)
            {
                if(v.getId() == R.id.btnSave)
                {
                    String itext = etIncoming.getText().toString();
                    String otext = etOutgoing.getText().toString();
                    if(itext == ""||otext == "")
                    {
                        String result = "Field must be filled";
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        db.execSQL("insert into message values('"+itext+"','"+otext+"')");
                        Toast.makeText(MainActivity.this,"Message Saved", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Intent i = new Intent(MainActivity.this,Show.class);
                    startActivity(i);
                }
            }
        }
}
