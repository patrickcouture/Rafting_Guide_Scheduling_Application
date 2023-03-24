package com.example.c868capstone_raftingguideschedulingapplication.database;

import android.app.Application;

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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private CustomersDAO mCustomersDAO;
    private EquipmentDAO mEquipmentDAO;
    private GuidesDAO mGuidesDAO;
    private TripsDAO mTripsDAO;

    private UsersDAO mUsersDAO;
    private List<Customers> mAllCustomers;
    private List<Equipment> mAllEquipment;
    private List<Guides> mAllGuides;
    private List<Trips> mAllTrips;
    private List<Users> mAllUsers;


    private static int NUMBER_OF_THREADS=12;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        RaftSchedulerDatabaseBuilder db = RaftSchedulerDatabaseBuilder.getDatabase(application);
        mCustomersDAO = db.customersDAO();
        mEquipmentDAO = db.equipmentDAO();
        mGuidesDAO = db.guidesDAO();
        mTripsDAO = db.tripsDAO();
        mUsersDAO = db.usersDAO();
    }

    public List<Customers> getAllCustomers(){
        databaseExecutor.execute(()-> {
            mAllCustomers = mCustomersDAO.getAllCustomers();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllCustomers;
    }

    public void insert(Customers customers) {
        databaseExecutor.execute(()->{
            mCustomersDAO.insert(customers);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Customers customers) {
        databaseExecutor.execute(()->{
            mCustomersDAO.update(customers);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Customers customers) {
        databaseExecutor.execute(()->{
            mCustomersDAO.delete(customers);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Equipment> getAllEquipment(){
        databaseExecutor.execute(()-> {
            mAllEquipment = mEquipmentDAO.getAllEquipment();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllEquipment;
    }

    public void insert(Equipment equipment) {
        databaseExecutor.execute(()->{
            mEquipmentDAO.insert(equipment);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Equipment equipment) {
        databaseExecutor.execute(()->{
            mEquipmentDAO.update(equipment);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Equipment equipment) {
        databaseExecutor.execute(()->{
            mEquipmentDAO.delete(equipment);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Guides> getAllGuides(){
        databaseExecutor.execute(()-> {
            mAllGuides = mGuidesDAO.getAllGuides();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllGuides;
    }

    public void insert(Guides guides) {
        databaseExecutor.execute(()->{
            mGuidesDAO.insert(guides);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Guides guides) {
        databaseExecutor.execute(()->{
            mGuidesDAO.update(guides);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Guides guides) {
        databaseExecutor.execute(()->{
            mGuidesDAO.delete(guides);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Trips> getAllTrips(){
        databaseExecutor.execute(()-> {
            mAllTrips = mTripsDAO.getAllTrips();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllTrips;
    }
    public void insert(Trips trips) {
        databaseExecutor.execute(()->{
            mTripsDAO.insert(trips);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Trips trips) {
        databaseExecutor.execute(()->{
            mTripsDAO.update(trips);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Trips trips) {
        databaseExecutor.execute(()->{
            mTripsDAO.delete(trips);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Users> getAllUsers(){
        databaseExecutor.execute(()-> {
            mAllUsers = mUsersDAO.getAllUsers();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllUsers;
    }


    public void insert(Users users) {
        databaseExecutor.execute(()->{
            mUsersDAO.insert(users);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Users users) {
        databaseExecutor.execute(()->{
            mUsersDAO.update(users);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Users users) {
        databaseExecutor.execute(()->{
            mUsersDAO.delete(users);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }



}
