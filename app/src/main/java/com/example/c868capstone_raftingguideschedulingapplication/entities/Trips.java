package com.example.c868capstone_raftingguideschedulingapplication.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
/**
 The Trips class represents a trip in the Rafting Guide Scheduling Application.
 A trip object contains information about the trip, such as its name, location, start and end time,
 and notes, as well as information about the guide, customer, and equipment associated with the trip.
 */
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

    /**
     * Constructs a new Trip object with the given parameters.
     *
     * @param tripID        the unique ID of the trip
     * @param tripName      the name of the trip
     * @param location      the location of the trip
     * @param guideID       the ID of the guide assigned to the trip
     * @param customerID    the ID of the customer associated with the trip
     * @param equipmentID   the ID of the equipment assigned to the trip
     * @param tripStart     the start time of the trip
     * @param tripEnd       the end time of the trip
     * @param tripNotes     the notes associated with the trip
     */
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
    /**
     * Constructs a new Trip object.
     */
    public Trips() {
    }
    /**
     * Returns the unique ID of the trip.
     * @return the unique ID of the trip
     */
    public int getTripID() {
        return tripID;
    }
    /**
     * Sets the unique ID of the trip.
     * @param tripID the unique ID of the trip
     */
    public void setTripID(int tripID) {
        this.tripID = tripID;
    }
    /**
     * Returns the name of the trip.
     * @return the name of the trip
     */
    public String getTripName() {
        return tripName;
    }
    /**
     * Sets the name of the trip.
     * @param tripName the name of the trip
     */
    public void setTripName(String tripName) {
        this.tripName = tripName;
    }
    /**
     * Returns the location of the trip.
     * @return the location of the trip
     */
    public String getLocation() {
        return location;
    }
    /**
     * Sets the location of the trip.
     * @param location the location of the trip
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * Returns the ID of the guide assigned to the trip.
     * @return the ID of the guide assigned to the trip
     */
    public int getGuideID() {
        return guideID;
    }

    /**
     * Set the ID of the guide
     * @param guideID the ID of the guide
     */
    public void setGuideID(int guideID) {
        this.guideID = guideID;
    }

    /**
     * Returns the ID of the customer assigned to the trip
     * @return the ID of the customer
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Set the ID of the customer assigned to the trip
     * @param customerID the ID of the customer
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Gets the ID of the equipment assigned to the trip
     * @return the ID of the equipment
     */
    public int getEquipmentID() {
        return equipmentID;
    }

    /**
     * Sets the ID of the equipment assigned to the trip
     * @param equipmentID the ID of the equipment
     */
    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    /**
     * Gets the date of the trip start in a String format
     * @return the start date of the trip
     */
    public String getTripStart() {
        return tripStart;
    }

    /**
     * Sets the start date of the trip
     * @param tripStart the start date of the trip
     */
    public void setTripStart(String tripStart) {
        this.tripStart = tripStart;
    }

    /**
     * Gets the end date of the trip
     * @return the end date of the trip
     */
    public String getTripEnd() {
        return tripEnd;
    }

    /**
     * Sets the end date of the trip
     * @param tripEnd end date of the trip
     */
    public void setTripEnd(String tripEnd) {
        this.tripEnd = tripEnd;
    }

    /**
     * Gets the trip notes for the assigned trip
     * @return the trip notes for the assigned trip
     */
    public String getTripNotes() {
        return tripNotes;
    }

    /**
     * Sets the trip notes for the assigned trip
     * @param tripNotes the trip notes for the assigned trip
     */
    public void setTripNotes(String tripNotes) {
        this.tripNotes = tripNotes;
    }
}
