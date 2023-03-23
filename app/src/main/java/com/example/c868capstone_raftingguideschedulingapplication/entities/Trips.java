package com.example.c868capstone_raftingguideschedulingapplication.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "trips")
public class Trips {

    @PrimaryKey(autoGenerate = true)
    private int tripID;

    private String tripName;

    private String location;
    private int guideID;
    private int customerID;
    private int equipmentID;
    private String tripStart;
    private String tripEnd;
    private String tripNotes;


    public Trips(int tripID, String tripName, String location, int guideID, int customerID, int equipmentID, String tripStart, String tripEnd, String tripNotes) {
        this.tripID = tripID;
        this.tripName = tripName;
        this.location = location;
        this.guideID = guideID;
        this.customerID = customerID;
        this.equipmentID = equipmentID;
        this.tripStart = tripStart;
        this.tripEnd = tripEnd;
        this.tripNotes = tripNotes;
    }

    public Trips() {
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getGuideID() {
        return guideID;
    }

    public void setGuideID(int guideID) {
        this.guideID = guideID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getTripStart() {
        return tripStart;
    }

    public void setTripStart(String tripStart) {
        this.tripStart = tripStart;
    }

    public String getTripEnd() {
        return tripEnd;
    }

    public void setTripEnd(String tripEnd) {
        this.tripEnd = tripEnd;
    }

    public String getTripNotes() {
        return tripNotes;
    }

    public void setTripNotes(String tripNotes) {
        this.tripNotes = tripNotes;
    }
}
