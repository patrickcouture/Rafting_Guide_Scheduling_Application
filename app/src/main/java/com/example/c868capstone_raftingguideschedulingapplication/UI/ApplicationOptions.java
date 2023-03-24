package com.example.c868capstone_raftingguideschedulingapplication.UI;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.c868capstone_raftingguideschedulingapplication.R;

public class ApplicationOptions extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_options);
    }

    public void launchCustomers (View view) {
        Intent intent = new Intent(this, CustomerList.class);
        startActivity(intent);
    }

    public void launchRiverGuides (View view) {
        Intent intent = new Intent(this, GuideList.class);
        startActivity(intent);
    }

    public void launchEquipment (View view) {
        Intent intent = new Intent(this, EquipmentList.class);
        startActivity(intent);
    }

    public void launchTrips (View view) {
        Intent intent = new Intent(this, TripsList.class);
        startActivity(intent);
    }

    public void launchReports (View view) {
        Intent intent = new Intent(this, Reports.class);
        startActivity(intent);
    }



}