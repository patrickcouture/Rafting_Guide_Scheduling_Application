package com.example.c868capstone_raftingguideschedulingapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.c868capstone_raftingguideschedulingapplication.R;
import com.example.c868capstone_raftingguideschedulingapplication.database.Repository;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Customers;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Guides;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Trips;

import java.util.ArrayList;
import java.util.List;

public class GuideDetails extends AppCompatActivity {

    EditText editGuideName;
    EditText editGuideEmail;
    EditText editGuidePhone;

    int guideID;
    String guideName;
    String guideEmail;
    String guidePhone;

    Repository repository;
    int numTrips;
    Guides guide;
    Guides currentGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_details);

        editGuideName = findViewById(R.id.guideNameEditText);
        editGuideEmail = findViewById(R.id.guideEmailEditText);
        editGuidePhone = findViewById(R.id.guidePhoneEditText);


        guideID = getIntent().getIntExtra("guideID", -1);
        guideName = getIntent().getStringExtra("guideName");
        guideEmail = getIntent().getStringExtra("guideEmail");
        guidePhone = getIntent().getStringExtra("guidePhone");

        editGuideName.setText(guideName);
        editGuideEmail.setText(guideEmail);
        editGuidePhone.setText(guidePhone);

        repository = new Repository(getApplication());

        RecyclerView recyclerView = findViewById(R.id.guideAssignRiverTrips);
        repository = new Repository(getApplication());
        final GuideAssignTripsAdapter guideAssignTripsAdapter = new GuideAssignTripsAdapter(this);
        recyclerView.setAdapter(guideAssignTripsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Trips> filteredTrips = new ArrayList<>();
        guideAssignTripsAdapter.setTrips(filteredTrips);
        for (Trips t : repository.getAllTrips()) {
            if (t.getGuideID() == guideID) filteredTrips.add(t);
        }

        Button contactCustBtn = findViewById(R.id.guideEmailBtn);
        contactCustBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get customer email address
                String guideEmail = editGuideEmail.getText().toString();
                // Launch email intent with customer email address populated
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{guideEmail});
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

        Button textCustBtn = findViewById(R.id.guideTextBtn);
        textCustBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get customer phone number
                String guidePhone = editGuidePhone.getText().toString();
                // Launch text message intent with customer phone number populated
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:" + guidePhone));
                startActivity(intent);
            }


        });

        Button button = findViewById(R.id.guideSaveBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editGuideName.getText().toString().isEmpty() || editGuideEmail.getText().toString().isEmpty() || editGuidePhone.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }else {

                    String phoneNumber = editGuidePhone.getText().toString();
                    String pattern = "\\d{10}";
                    if (!phoneNumber.matches(pattern)) {
                        editGuidePhone.setError("Please enter a valid 10-digit phone number");
                    } else {
                        if (guideID == -1) {

                            guide = new Guides(0, editGuideName.getText().toString(), editGuideEmail.getText().toString(),
                                    editGuidePhone.getText().toString());
                            repository.insert(guide);
                            Toast.makeText(getApplicationContext(), "New Guide added!", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            guide = new Guides(guideID, editGuideName.getText().toString(), editGuideEmail.getText().toString(),
                                    editGuidePhone.getText().toString());
                            repository.update(guide);
                            Toast.makeText(getApplicationContext(), "Guide updated!", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }
                }
            }
        });

        Button deleteButton = findViewById(R.id.guideDeleteBtn);
        deleteButton.setOnClickListener(view -> {
            for (Guides gui : repository.getAllGuides()) {
                if (gui.getGuideID() == guideID) currentGuide = gui;
            }
            numTrips = 0;
            for( Trips trips: repository.getAllTrips()) {
                if(trips.getGuideID() == guideID)   ++numTrips;
            }
            if(numTrips == 0) {
                repository.delete(currentGuide);
                Toast.makeText(GuideDetails.this, currentGuide.getGuideName() + " was deleted", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(GuideDetails.this, "Can't Delete a guide that has a scheduled trip", Toast.LENGTH_LONG).show();

            }
        });
    }
}