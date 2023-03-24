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
import com.example.c868capstone_raftingguideschedulingapplication.entities.Guides;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.stream.Collectors;

public class EquipmentList extends BaseActivity {

    private Repository repository;
    private EditText searchEquipmentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_list);

        searchEquipmentEditText = findViewById(R.id.searchEquipmentEditText);
        searchEquipmentEditText.addTextChangedListener(searchEquipmentTextWatcher);

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
        equipmentListAdapter.setEquipment(allEquipment);
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
        equipmentListAdapter.setEquipment(allEquipment);
        equipmentListAdapter.notifyDataSetChanged();
    }

    private TextWatcher searchEquipmentTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Filter the list of equipment items based on the searchInput variable
            List<Equipment> filteredEquipment = repository.getAllEquipment().stream()
                    .filter(e -> e.getEquipmentName().toLowerCase().contains(s.toString().toLowerCase()))
                    .collect(Collectors.toList());

            // Update the recycler view with the filtered list
            RecyclerView recyclerView = findViewById(R.id.equipListRecView);
            final EquipmentListAdapter equipmentListAdapter = new EquipmentListAdapter(EquipmentList.this);
            recyclerView.setAdapter(equipmentListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(EquipmentList.this));
            equipmentListAdapter.setEquipment(filteredEquipment);
            equipmentListAdapter.notifyDataSetChanged();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}