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
import com.example.c868capstone_raftingguideschedulingapplication.entities.Customers;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Equipment;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Guides;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.stream.Collectors;

public class GuideList extends BaseActivity {

    private Repository repository;

    private EditText searchGuideEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_list);

        searchGuideEditText = findViewById(R.id.searchGuideEditText);
        searchGuideEditText.addTextChangedListener(searchGuideTextWatcher);

        FloatingActionButton fab = findViewById(R.id.addNewGuideBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideList.this, GuideDetails.class);
                startActivity(intent);
            }
        });

        repository = new Repository(getApplication());
        List<Guides> allGuides = repository.getAllGuides();
        RecyclerView recyclerView = findViewById(R.id.guideListRecView);
        final GuideListAdapter guideListAdapter = new GuideListAdapter(this);
        recyclerView.setAdapter(guideListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        guideListAdapter.setGuides(allGuides);
        guideListAdapter.notifyDataSetChanged();



    }

    @Override
    protected void onResume(){
        super.onResume();
        List<Guides> allGuides = repository.getAllGuides();
        RecyclerView recyclerView = findViewById(R.id.guideListRecView);
        final GuideListAdapter guideListAdapter = new GuideListAdapter(this);
        recyclerView.setAdapter(guideListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        guideListAdapter.setGuides(allGuides);
        guideListAdapter.notifyDataSetChanged();
    }

    private TextWatcher searchGuideTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Filter the list of guides items based on the searchInput variable
            List<Guides> filteredGuides = repository.getAllGuides().stream()
                    .filter(g -> g.getGuideName().toLowerCase().contains(s.toString().toLowerCase()))
                    .collect(Collectors.toList());

            // Update the recycler view with the filtered list
            RecyclerView recyclerView = findViewById(R.id.guideListRecView);
            final GuideListAdapter guideListAdapter = new GuideListAdapter(GuideList.this);
            recyclerView.setAdapter(guideListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(GuideList.this));
            guideListAdapter.setGuides(filteredGuides);
            guideListAdapter.notifyDataSetChanged();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}