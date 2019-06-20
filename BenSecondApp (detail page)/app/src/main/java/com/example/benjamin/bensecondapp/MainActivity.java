package com.example.benjamin.bensecondapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mButton = null;
    private Button buttonTwo = null;
    private Button buttonThree = null;

    public static final int DETAIL_REQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_relative_layout);

        //String TAG = "logcat";
        //Log.i(TAG, "testing log");





        mButton = findViewById(R.id.myButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButton.setText("woot!");

            }
        });

        buttonTwo = findViewById(R.id.buttonTwo);

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SecondActivity.class);
                startActivity(i);
            }
        });

        buttonThree = findViewById(R.id.buttonThree);

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailActivity.class);
                i.putExtra("Key for sending", "some data from main Activity");
                startActivityForResult(i, DETAIL_REQUEST);
            }
        });
    }
}
