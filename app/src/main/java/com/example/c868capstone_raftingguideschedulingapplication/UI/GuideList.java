package com.example.c868capstone_raftingguideschedulingapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c868capstone_raftingguideschedulingapplication.R;
import com.example.c868capstone_raftingguideschedulingapplication.database.Repository;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Customers;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Guides;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class GuideList extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_list);

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

}