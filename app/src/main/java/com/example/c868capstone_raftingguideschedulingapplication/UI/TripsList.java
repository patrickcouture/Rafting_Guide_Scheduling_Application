package com.example.c868capstone_raftingguideschedulingapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c868capstone_raftingguideschedulingapplication.R;
import com.example.c868capstone_raftingguideschedulingapplication.database.Repository;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Equipment;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Trips;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TripsList extends AppCompatActivity {

    private Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_river_trips_list);

        FloatingActionButton fab = findViewById(R.id.addTripBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TripsList.this, TripDetails.class);
                startActivity(intent);
            }
        });

        repository = new Repository(getApplication());
        List<Trips> allTrips = repository.getAllTrips();
        RecyclerView recyclerView = findViewById(R.id.tripListRecView);
        final TripListAdapter tripListAdapter = new TripListAdapter(this);
        recyclerView.setAdapter(tripListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripListAdapter.setTrips(allTrips);
        tripListAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Trips> allTrips = repository.getAllTrips();
        RecyclerView recyclerView = findViewById(R.id.tripListRecView);
        final TripListAdapter tripListAdapter = new TripListAdapter(this);
        recyclerView.setAdapter(tripListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripListAdapter.setTrips(allTrips);
        tripListAdapter.notifyDataSetChanged();
    }
}
