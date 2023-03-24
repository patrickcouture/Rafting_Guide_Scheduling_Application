package com.example.c868capstone_raftingguideschedulingapplication.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 The Guides class represents an entity in the rafting guide scheduling application that stores information about guides.
 It is used to create and access guide objects in the application.
 */
@Entity(tableName = "guides")
public class Guides {

    @PrimaryKey(autoGenerate = true)
    private int guideID;
    private String guideName;
    private String guideEmail;
    private String guidePhone;
    /**
     * Constructs a new Guides object with the specified guide ID, name, email, and phone number.
     * @param guideID an int representing the unique ID of the guide
     * @param guideName a String representing the name of the guide
     * @param guideEmail a String representing the email of the guide
     * @param guidePhone a String representing the phone number of the guide
     */
    public Guides(int guideID, String guideName, String guideEmail, String guidePhone) {
        this.guideID = guideID;
        this.guideName = guideName;
        this.guideEmail = guideEmail;
        this.guidePhone = guidePhone;
    }
    /**
     * Constructs a new Guides object with default values for its fields.
     */
    public Guides() {
    }
    /**
     * Returns the ID of the guide.
     * @return an int representing the unique ID of the guide
     */
    public int getGuideID() {
        return guideID;
    }
    /**
     * Sets the ID of the guide.
     * @param guideID an int representing the unique ID of the guide
     */
    public void setGuideID(int guideID) {
        this.guideID = guideID;
    }
    /**
     * Returns the name of the guide.
     * @return a String representing the name of the guide
     */
    public String getGuideName() {
        return guideName;
    }
    /**
     * Sets the name of the guide.
     * @param guideName a String representing the name of the guide
     */
    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }
    /**
     * Returns the email of the guide.
     * @return a String representing the email of the guide
     */
    public String getGuideEmail() {
        return guideEmail;
    }
    /**
     * Sets the email of the guide.
     * @param guideEmail a String representing the email of the guide
     */
    public void setGuideEmail(String guideEmail) {
        this.guideEmail = guideEmail;
    }
    /**
     * Returns the phone number of the guide.
     * @return a String representing the phone number of the guide
     */
    public String getGuidePhone() {
        return guidePhone;
    }
    /**
     * Sets the phone number of the guide.
     * @param guidePhone a String representing the phone number of the guide
     */
    public void setGuidePhone(String guidePhone) {
        this.guidePhone = guidePhone;
    }
}
