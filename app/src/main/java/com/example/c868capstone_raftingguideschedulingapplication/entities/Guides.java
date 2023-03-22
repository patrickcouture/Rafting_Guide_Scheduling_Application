package com.example.c868capstone_raftingguideschedulingapplication.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "guides")
public class Guides {

    @PrimaryKey(autoGenerate = true)
    private int guideID;
    private String guideName;
    private String guideEmail;
    private String guidePhone;

    public Guides(int guideID, String guideName, String guideEmail, String guidePhone) {
        this.guideID = guideID;
        this.guideName = guideName;
        this.guideEmail = guideEmail;
        this.guidePhone = guidePhone;
    }

    public Guides() {
    }

    public int getGuideID() {
        return guideID;
    }

    public void setGuideID(int guideID) {
        this.guideID = guideID;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getGuideEmail() {
        return guideEmail;
    }

    public void setGuideEmail(String guideEmail) {
        this.guideEmail = guideEmail;
    }

    public String getGuidePhone() {
        return guidePhone;
    }

    public void setGuidePhone(String guidePhone) {
        this.guidePhone = guidePhone;
    }
}
