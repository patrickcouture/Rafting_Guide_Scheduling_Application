package com.example.c868capstone_raftingguideschedulingapplication.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c868capstone_raftingguideschedulingapplication.entities.Trips;

import java.util.List;

@Dao
public interface TripsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Trips trips);

    @Update
    void update(Trips trips);

    @Delete
    void delete(Trips trips);

    @Query("SELECT * FROM trips ORDER BY tripID ASC")
    List<Trips> getAllTrips();

}
