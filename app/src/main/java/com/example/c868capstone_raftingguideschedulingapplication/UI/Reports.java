package com.example.c868capstone_raftingguideschedulingapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.c868capstone_raftingguideschedulingapplication.R;
import com.example.c868capstone_raftingguideschedulingapplication.database.Repository;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Trips;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Reports extends BaseActivity {

    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        Button tripsTodayBtn = findViewById(R.id.tripsTodayBtn);
        tripsTodayBtn.setOnClickListener(view -> {
            repository = new Repository(getApplication());
            List<Trips> allTrips = repository.getAllTrips();
            RecyclerView recyclerView = findViewById(R.id.todaytripsRecView);
            final TripListAdapter tripListAdapter = new TripListAdapter(this);
            recyclerView.setAdapter(tripListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            List<Trips> filteredTrips = new ArrayList<>();
            tripListAdapter.setTrips(filteredTrips);
            String currentDate = getCurrentDate();

            for (Trips t : allTrips) {
                if (t.getTripStart().equals(currentDate) || t.getTripEnd().equals(currentDate)) {
                    filteredTrips.add(t);
                }
            }
            tripListAdapter.notifyDataSetChanged();

        });

        Button tripsWeekBtn = findViewById(R.id.tripsWeekBtn);
        tripsWeekBtn.setOnClickListener(view -> {
            repository = new Repository(getApplication());
            List<Trips> allTrips = repository.getAllTrips();
            RecyclerView recyclerView = findViewById(R.id.weektripsRecView);
            final TripListAdapter tripListAdapter = new TripListAdapter(this);
            recyclerView.setAdapter(tripListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            List<Trips> filteredTrips = new ArrayList<>();
            tripListAdapter.setTrips(filteredTrips);
            String currentDate = getCurrentDate();

            for (Trips t : allTrips) {
                long diff = getDateDiff(currentDate, t.getTripStart(), TimeUnit.DAYS);
                if (diff >= 0 && diff <= 7) {
                    filteredTrips.add(t);
                }
            }
            tripListAdapter.notifyDataSetChanged();
        });

        Button allTripsBtn = findViewById(R.id.allTripsBtn);
        allTripsBtn.setOnClickListener(view -> {
            repository = new Repository(getApplication());
            List<Trips> allTrips = repository.getAllTrips();
            RecyclerView recyclerView = findViewById(R.id.alltripsRecView);
            final TripListAdapter tripListAdapter = new TripListAdapter(this);
            recyclerView.setAdapter(tripListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            tripListAdapter.setTrips(allTrips);
            tripListAdapter.notifyDataSetChanged();

        });




    }

    private String getCurrentDate() {
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

    private String getCurrentTime() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
        return timeFormat.format(currentTime);
    }

    private long getDateDiff(String date1, String date2, TimeUnit timeUnit) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.US);
        try {
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);
            long diffInMs = d2.getTime() - d1.getTime();
            return timeUnit.convert(diffInMs, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }



}