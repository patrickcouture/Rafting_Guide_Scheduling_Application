package com.example.c868capstone_raftingguideschedulingapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c868capstone_raftingguideschedulingapplication.entities.Equipment;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Guides;

import java.util.List;

@Dao
public interface GuidesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Guides guides);

    @Update
    void update(Guides guides);

    @Delete
    void delete(Guides guides);

    @Query("SELECT * FROM guides ORDER BY guideID ASC")
    List<Guides> getAllGuides();
}
