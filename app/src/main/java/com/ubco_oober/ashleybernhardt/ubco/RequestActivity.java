package com.ubco_oober.ashleybernhardt.ubco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RequestActivity extends AppCompatActivity {

    EditText txtDestination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        txtDestination = (EditText) findViewById(R.id.etDestinationRequest);
    }

    public void onClickRequestFinish(View view){
        Intent intent = new Intent(this,RSS.class);

        final String Destination = txtDestination.getText().toString();
        //Do things





        startActivity(intent);

    }
}
