package com.example.c868capstone_raftingguideschedulingapplication.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c868capstone_raftingguideschedulingapplication.entities.Users;

import java.util.List;

@Dao
public interface UsersDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Users users);

    @Update
    void update(Users users);

    @Delete
    void delete(Users users);

    @Query("SELECT * FROM users ORDER BY userID ASC")
    List<Users> getAllUsers();


}
