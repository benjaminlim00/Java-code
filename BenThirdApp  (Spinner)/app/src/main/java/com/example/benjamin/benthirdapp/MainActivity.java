package com.example.benjamin.benthirdapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button mButton = null;
    private Button buttonTwo = null;
    private Button buttonThree = null;
    private Spinner spinner = null;

    TextView menu = null;
    String myValue = null;



    public static final int DETAIL_REQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.myButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButton.setText("woot!");

            }
        });

        //buttonTwo = findViewById(R.id.buttonTwo);

        menu = findViewById(R.id.menu);

        spinner = findViewById(R.id.spinner);




        buttonThree = findViewById(R.id.buttonThree);

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailActivity.class);
                i.putExtra("KeyForSending", "some data from main Activity");
                startActivityForResult(i, DETAIL_REQUEST);
            }
        });

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == DETAIL_REQUEST) {
            if (data.hasExtra("KeyForReturning")) {
                myValue = data.getExtras().getString("KeyForReturning");
                menu.setText(myValue);
            }
        }
    }

}
