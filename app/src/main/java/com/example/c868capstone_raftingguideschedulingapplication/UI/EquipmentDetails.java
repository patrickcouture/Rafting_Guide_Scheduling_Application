package com.example.c868capstone_raftingguideschedulingapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.c868capstone_raftingguideschedulingapplication.R;
import com.example.c868capstone_raftingguideschedulingapplication.database.Repository;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Equipment;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Guides;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Trips;

import java.util.ArrayList;
import java.util.List;

public class EquipmentDetails extends AppCompatActivity {

    EditText editEquipName;
    NumberPicker editEquipCapacity;


    int equipmentID;
    String equipmentName;
    int equipmentCapacity;

    Repository repository;
    int numTrips;
    Equipment equipment;
    Equipment currentEquipment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_details);

        editEquipName = findViewById(R.id.equipNameEditText);
        editEquipCapacity = findViewById(R.id.equipCapacityNumPick);

        equipmentID = getIntent().getIntExtra("equipmentID", -1);
        equipmentName = getIntent().getStringExtra("equipmentName");
        equipmentCapacity = getIntent().getIntExtra("equipmentMaxCapacity", -1);

        editEquipName.setText(equipmentName);
        editEquipCapacity.setMinValue(1);
        editEquipCapacity.setMaxValue(11);
        editEquipCapacity.setValue(equipmentCapacity);

        repository = new Repository(getApplication());

        RecyclerView recyclerView = findViewById(R.id.equipAssignRiverTrips);
        repository = new Repository(getApplication());
        final EquipAssignTripsAdapter equipAssignTripsAdapter = new EquipAssignTripsAdapter(this);
        recyclerView.setAdapter(equipAssignTripsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Trips> filteredTrips = new ArrayList<>();
        equipAssignTripsAdapter.setTrips(filteredTrips);
        for (Trips t : repository.getAllTrips()) {
            if (t.getEquipmentID() == equipmentID) filteredTrips.add(t);
        }

        Button button = findViewById(R.id.equipSaveBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editEquipName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (equipmentID == -1) {

                        equipment = new Equipment(0, editEquipName.getText().toString(), editEquipCapacity.getValue());
                        repository.insert(equipment);
                        Toast.makeText(getApplicationContext(), "New Equipment added!", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {
                        equipment = new Equipment(equipmentID, editEquipName.getText().toString(), editEquipCapacity.getValue());
                        repository.update(equipment);
                        Toast.makeText(getApplicationContext(), "Equipment updated!", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                }

            }
        });

        Button deleteButton = findViewById(R.id.equipDeleteBtn);
        deleteButton.setOnClickListener(view -> {
            for (Equipment equip : repository.getAllEquipment()) {
                if (equip.getEquipmentID() == equipmentID) currentEquipment = equip;
            }
            numTrips = 0;
            for (Trips trips : repository.getAllTrips()) {
                if (trips.getEquipmentID() == equipmentID) ++numTrips;
            }
            if (numTrips == 0) {
                repository.delete(currentEquipment);
                Toast.makeText(EquipmentDetails.this, currentEquipment.getEquipmentName() + " was deleted", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(EquipmentDetails.this, "Can't Delete equipment that is assigned to a trip", Toast.LENGTH_LONG).show();

            }
        });
    }
}