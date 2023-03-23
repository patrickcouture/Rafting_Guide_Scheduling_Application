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
import com.example.c868capstone_raftingguideschedulingapplication.entities.Guides;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class EquipmentList extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_list);

        FloatingActionButton fab = findViewById(R.id.addNewEquipmentBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EquipmentList.this, EquipmentDetails.class);
                startActivity(intent);
            }
        });

        repository = new Repository(getApplication());
        List<Equipment> allEquipment = repository.getAllEquipment();
        RecyclerView recyclerView = findViewById(R.id.equipListRecView);
        final EquipmentListAdapter equipmentListAdapter = new EquipmentListAdapter(this);
        recyclerView.setAdapter(equipmentListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        equipmentListAdapter.setGuides(allEquipment);
        equipmentListAdapter.notifyDataSetChanged();



    }

    @Override
    protected void onResume(){
        super.onResume();
        List<Equipment> allEquipment = repository.getAllEquipment();
        RecyclerView recyclerView = findViewById(R.id.equipListRecView);
        final EquipmentListAdapter equipmentListAdapter = new EquipmentListAdapter(this);
        recyclerView.setAdapter(equipmentListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        equipmentListAdapter.setGuides(allEquipment);
        equipmentListAdapter.notifyDataSetChanged();
    }


}