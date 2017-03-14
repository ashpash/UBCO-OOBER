package com.example.nick.myfirstapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Button button = (Button) findViewById(R.id.bFinish);

    }


    //final Button bFinish = (Button) findViewById(R.id.bRegister);

    public void sendInfo(View view) {
        Intent intent = new Intent(this, congratsActivity.class);

        //place inputted text into variables
        final EditText etDestination = (EditText) findViewById(R.id.etDestination);
        final EditText etSpace = (EditText) findViewById(R.id.etSpace);
        final EditText etTime = (EditText) findViewById(R.id.etTime);
        String[] message = new String[3];

        Form exampleForm = new Form(etDestination.getText().toString(),etSpace.getText().toString(),etTime.getText().toString());

        message[0] = etDestination.getText().toString();
        message[1] = etSpace.getText().toString();
        message[2] = etTime.getText().toString();


        intent.putExtra(EXTRA_MESSAGE, (Parcelable) exampleForm);
        startActivity(intent);

    }
}

