package com.example.c868capstone_raftingguideschedulingapplication.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c868capstone_raftingguideschedulingapplication.entities.Equipment;

import java.util.List;

@Dao
public interface EquipmentDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Equipment equipment);

    @Update
    void update(Equipment equipment);

    @Delete
    void delete(Equipment equipment);

    @Query("SELECT * FROM equipment ORDER BY equipmentID ASC")
    List<Equipment> getAllEquipment();
}
