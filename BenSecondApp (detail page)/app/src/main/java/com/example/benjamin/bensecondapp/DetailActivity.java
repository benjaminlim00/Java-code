package com.example.benjamin.bensecondapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {
    TextView text = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();

        text = findViewById(R.id.textView);


        if (extras != null) {
            String detailValue = extras.getString("Key for sending");
            if (detailValue != null) {
                Toast.makeText(this,detailValue,Toast.LENGTH_LONG).show();
                // first is this, then what to show, and duration to display for. we always end it with a show method.

                text.setText(detailValue);
            }
        }




    }
}
