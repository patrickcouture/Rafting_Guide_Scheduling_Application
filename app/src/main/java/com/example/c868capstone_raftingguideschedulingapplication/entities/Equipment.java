package com.example.c868capstone_raftingguideschedulingapplication.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "equipment")
public class Equipment {

    @PrimaryKey(autoGenerate = true)
    private int equipmentID;
    private String equipmentName;
    private int equipmentMaxCapacity;

    public Equipment(int equipmentID, String equipmentName, int equipmentMaxCapacity) {
        this.equipmentID = equipmentID;
        this.equipmentName = equipmentName;
        this.equipmentMaxCapacity = equipmentMaxCapacity;
    }

    public Equipment() {
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getEquipmentMaxCapacity() {
        return equipmentMaxCapacity;
    }

    public void setEquipmentMaxCapacity(int equipmentMaxCapacity) {
        this.equipmentMaxCapacity = equipmentMaxCapacity;
    }
}
