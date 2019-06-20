package com.example.benjamin.benthirdapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class DetailActivity extends Activity {

    Spinner spinner = null;
    Button goBack = null;
    Button performSel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        //display the data sent to us.
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String detailValue = extras.getString("KeyForSending");
            if (detailValue != null) {
                Toast.makeText(this, detailValue, Toast.LENGTH_LONG).show();
            }
        }

        spinner = findViewById(R.id.spinner);


        goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                String mySelection = spinner.getSelectedItem().toString();
                i.putExtra("KeyForReturning", mySelection);
                setResult(RESULT_OK, i);
                finish();
            }
        });




        performSel = findViewById(R.id.performSel);
        performSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = spinner.getSelectedItemPosition();
                Intent implicitIntent = null;
                switch(position) {
                    case 0:
                        //nothing selected
                        break;
                    case 1:
                        //take picture
                        implicitIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                        break;
                    case 2:
                        //go to Google
                        implicitIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("www.google.com.sg"));
                        break;
                    case 3:
                        //edit contact
                        implicitIntent = new Intent(Intent.ACTION_EDIT,
                                Uri.parse("content://contacts/people/1"));
                        break;
                    case 4:
                        //make a call
                        implicitIntent = new Intent(Intent.ACTION_DIAL,
                                Uri.parse("tel:(+065)321312312"));
                        break;
                }

                if (implicitIntent != null) {
                    if (isIntentAvailable(implicitIntent)) {
                        startActivity(implicitIntent);
                    } else {
                        Toast.makeText(view.getContext(), "No Application Available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public boolean isIntentAvailable(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activites = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activites.size() > 0;
        return isIntentSafe;
    }
}
