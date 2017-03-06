package com.ubco_oober.ashleybernhardt.ubco;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import static com.ubco_oober.ashleybernhardt.ubco.R.id.etlName;


public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etPassword =(EditText) findViewById(R.id.etPassword);
        final EditText etfName =(EditText) findViewById(R.id.etfName);
        final EditText etlName =(EditText) findViewById(R.id.etlName);
        final EditText etEmail =(EditText) findViewById(R.id.etEmail);
       // final EditText etcPassword =(EditText) findViewById(R.id.etcPassword);

        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fName = etfName.getText().toString();
                final String lName = etlName.getText().toString();
                final String studentEmail = etEmail.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                       try {
                           JSONObject jsonResponse = new JSONObject(response);
                           boolean success = jsonResponse.getBoolean("success");

                           if (success) {
                               Intent intent = new Intent(Register.this, LoginActivity.class);
                               Register.this.startActivity(intent);
                           } else {
                               AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                               builder.setMessage("Failed to Register")
                                       .setNegativeButton("Retry", null)
                                       .create()
                                       .show();

                           }
                       } catch(JSONException e){
                           e.printStackTrace();
                       }

                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(fName, lName, studentEmail, password, responseListener );
                RequestQueue queue = Volley.newRequestQueue(Register.this);
                    queue.add(registerRequest);
            }
        });

    }

    //
}
