package com.example.c868capstone_raftingguideschedulingapplication.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c868capstone_raftingguideschedulingapplication.dao.CustomersDAO;
import com.example.c868capstone_raftingguideschedulingapplication.dao.EquipmentDAO;
import com.example.c868capstone_raftingguideschedulingapplication.dao.GuidesDAO;
import com.example.c868capstone_raftingguideschedulingapplication.dao.TripsDAO;
import com.example.c868capstone_raftingguideschedulingapplication.dao.UsersDAO;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Customers;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Equipment;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Guides;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Trips;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Users;

@Database(entities = {Customers.class, Equipment.class, Guides.class, Trips.class, Users.class}, version = 7, exportSchema = false)
public abstract class RaftSchedulerDatabaseBuilder extends RoomDatabase {

    public abstract CustomersDAO customersDAO();
    public abstract EquipmentDAO equipmentDAO();
    public abstract GuidesDAO guidesDAO();
    public abstract TripsDAO tripsDAO();
    public abstract UsersDAO usersDAO();



    private static volatile RaftSchedulerDatabaseBuilder INSTANCE;

    static RaftSchedulerDatabaseBuilder getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (RaftSchedulerDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RaftSchedulerDatabaseBuilder.class, "RaftSchedulerDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
