package com.ubco_oober.ashleybernhardt.ubco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class congratsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        Intent intent = getIntent();
        Form mFormData = intent.getParcelableExtra(FormActivity.EXTRA_MESSAGE);
        String[] message = intent.getStringArrayExtra(FormActivity.EXTRA_MESSAGE);

        TextView etDestination = (TextView) findViewById(R.id.etDestination);
        TextView etSpace = (TextView) findViewById(R.id.etSpace);
        TextView etTime = (TextView) findViewById(R.id.etTime);


        //Working String array
//        etDestination.setText(message[0]);
//        etSpace.setText(message[1]);
//        etTime.setText(message[2]);

        //Testing Parcelable
        etDestination.setText(mFormData.getDestination());
        etSpace.setText(mFormData.getSpace());
        etTime.setText(mFormData.getTime());

        etDestination.setFocusable(false);
        etSpace.setFocusable(false);
        etTime.setFocusable(false);

        setTitle("Completed Form");
    }
}
