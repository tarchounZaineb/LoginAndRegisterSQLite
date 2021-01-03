package com.example.loginandregistersqlite;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText email, password;
    Button login;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e1 = email.getText().toString();
                String e2 = password.getText().toString();
                Boolean emailpass = db.chkemailpass(e1, e2);
                if (emailpass == true) {
                    Toast.makeText(getApplicationContext(), "successfully login", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "wrong email or password", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}

