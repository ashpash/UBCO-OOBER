package com.ubco_oober.ashleybernhardt.ubco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ashley on 3/26/2017.
 */

public class RideInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        final String studentEmail = (String) b.get("studentEmail");

        AlertDialog.Builder builder = new AlertDialog.Builder(RideInfo.this);
        builder.setMessage("this is on the right track" + studentEmail)
                .setNegativeButton("Retry", null)
                .create()
                .show();
    }
}
