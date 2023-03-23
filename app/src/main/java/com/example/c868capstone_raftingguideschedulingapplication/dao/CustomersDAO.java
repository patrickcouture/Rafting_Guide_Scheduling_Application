package com.example.c868capstone_raftingguideschedulingapplication.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c868capstone_raftingguideschedulingapplication.entities.Customers;

import java.util.List;

@Dao
public interface CustomersDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Customers customers);

    @Update
    void update(Customers customers);

    @Delete
    void delete(Customers customers);

    @Query("SELECT * FROM Customers ORDER BY customerID ASC")
    List<Customers> getAllCustomers();
}
