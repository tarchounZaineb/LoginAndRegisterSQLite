package com.example.loginandregistersqlite;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText email , password,confpass;
    Button register,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new DatabaseHelper(this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
        confpass = findViewById(R.id.cpass);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1= email.getText().toString();
                String s2= password.getText().toString();
                String s= confpass.getText().toString();

                if (s1.equals("")||s2.equals("")||s.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (s2.equals(s)) {
                        Boolean chkemailuser = db.chkemail(s1);
                        if(chkemailuser == true) {
                            Boolean insertuser = db.insert(s1,s2);
                            if(insertuser == true) {
                                Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "passwords do not match", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}