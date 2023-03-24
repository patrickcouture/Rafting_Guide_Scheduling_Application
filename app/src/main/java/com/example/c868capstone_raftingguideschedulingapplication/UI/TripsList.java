package com.example.c868capstone_raftingguideschedulingapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.c868capstone_raftingguideschedulingapplication.R;
import com.example.c868capstone_raftingguideschedulingapplication.database.Repository;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Equipment;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Trips;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.stream.Collectors;

public class TripsList extends BaseActivity {

    private Repository repository;
    private EditText searchTripEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_river_trips_list);

        searchTripEditText = findViewById(R.id.searchTripEditText);
        searchTripEditText.addTextChangedListener(searchTripsTextWatcher);

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

    private TextWatcher searchTripsTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Filter the list of equipment items based on the searchInput variable
            List<Trips> filteredTrips = repository.getAllTrips().stream()
                    .filter(t -> t.getTripName().toLowerCase().contains(s.toString().toLowerCase()))
                    .collect(Collectors.toList());

            // Update the recycler view with the filtered list
            RecyclerView recyclerView = findViewById(R.id.tripListRecView);
            final TripListAdapter tripListAdapter = new TripListAdapter(TripsList.this);
            recyclerView.setAdapter(tripListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(TripsList.this));
            tripListAdapter.setTrips(filteredTrips);
            tripListAdapter.notifyDataSetChanged();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
