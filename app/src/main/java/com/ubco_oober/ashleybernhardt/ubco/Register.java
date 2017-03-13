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


public class Register extends AppCompatActivity {



    public static boolean isEmailValid(String studentEmail){
        String email = "@alumni.ubc.ca";
        if (studentEmail.contains(email)){
            return true;
        }else{
            return false;}
    }

    public static boolean passwordMatch(String e1, String e2){
        if (e1.equals(e2)){
            return true;}
            else {return false;}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etPassword =(EditText) findViewById(R.id.etPassword);
        final EditText etfName =(EditText) findViewById(R.id.etfName);
        final EditText etlName =(EditText) findViewById(R.id.etlName);
        final EditText etEmail =(EditText) findViewById(R.id.etEmail);
        final EditText etcPassword =(EditText) findViewById(R.id.etcPassword);

        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fName = etfName.getText().toString();
                final String lName = etlName.getText().toString();
                final String studentEmail = etEmail.getText().toString();
                final boolean checkEmail = isEmailValid(studentEmail);
                final String password = etPassword.getText().toString();
                final String cPassword = etcPassword.getText().toString();
                final boolean pwMatch = passwordMatch(password, cPassword);

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
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                if (checkEmail && pwMatch ) {

                    RegisterRequest registerRequest = new RegisterRequest(fName, lName, studentEmail, password, cPassword, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Register.this);
                    queue.add(registerRequest);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setMessage("Bad Email or Password Match")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
            }
        });

    }

    //
}
