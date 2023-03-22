package com.example.c868capstone_raftingguideschedulingapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c868capstone_raftingguideschedulingapplication.R;

public class MainActivity extends AppCompatActivity {

    Button loginBtn,cancelBtn;
    EditText editTextUsername,editTextPassword;
    TextView usernameLabel, passwordLabel;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextUsername.getText().toString().equals("admin") &&
                        editTextPassword.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, ApplicationOptions.class);
                    startActivity(i);
                }else {
                    Toast.makeText(getApplicationContext(),"Login unsuccessful, you have " + counter + " attempts left, please try again", Toast.LENGTH_LONG).show();
                    editTextUsername.setText("");
                    editTextPassword.setText("");
                    editTextUsername.requestFocus();
                    counter--;
                    if(counter == 0) {
                        loginBtn.setEnabled(false);
                    }
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }






}