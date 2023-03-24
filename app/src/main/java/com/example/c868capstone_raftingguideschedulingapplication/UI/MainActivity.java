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
import com.example.c868capstone_raftingguideschedulingapplication.database.Repository;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Customers;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Equipment;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Guides;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Trips;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Users;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Users;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button loginBtn, cancelBtn;
    EditText editTextUsername, editTextPassword;
    TextView usernameLabel, passwordLabel;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Users user = new Users(1, "admin", "admin");
        Repository repository = new Repository(getApplication());
        repository.insert(user);

        Customers customers = new Customers(1, "Jackson Tourist", "mr.patrickcouture@gmail.com", "4065819049", 8);
        repository.insert(customers);

        Equipment equipment = new Equipment(1, "Otter 15", 12);
        repository.insert(equipment);

        Guides guides = new Guides(1, "Joe Rafter", "joerafter@gmail.com", "5554441212");
        repository.insert(guides);

        Trips trips = new Trips(1, "The OverNighter", "Upper Shoshone", 1, 1, 1, "06/01/23", "06/05/23", "Group would like to stop for a picnic");
        repository.insert(trips);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login(editTextUsername.getText().toString(), editTextPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, ApplicationOptions.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Login unsuccessful, you have " + counter + " attempts left, please try again", Toast.LENGTH_LONG).show();
                    editTextUsername.setText("");
                    editTextPassword.setText("");
                    editTextUsername.requestFocus();
                    counter--;
                    if (counter == 0) {
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

    private boolean login(String username, String password) {
        Repository repository = new Repository(getApplication());
        List<Users> userList = repository.getAllUsers();
        for (Users user : userList) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }

}
