package com.example.c868capstone_raftingguideschedulingapplication.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**

 The Equipment class represents an entity in the rafting guide scheduling application that stores information about equipment.
 It is used to create and access equipment objects in the application.
 */
@Entity(tableName = "equipment")
public class Equipment {

    @PrimaryKey(autoGenerate = true)
    private int equipmentID;
    private String equipmentName;
    private int equipmentMaxCapacity;
    /**
     * Constructs a new Equipment object with the specified equipment ID, name, and maximum capacity.
     * @param equipmentID an int representing the unique ID of the equipment
     * @param equipmentName a String representing the name of the equipment
     * @param equipmentMaxCapacity an int representing the maximum capacity of the equipment
     */
    public Equipment(int equipmentID, String equipmentName, int equipmentMaxCapacity) {
        this.equipmentID = equipmentID;
        this.equipmentName = equipmentName;
        this.equipmentMaxCapacity = equipmentMaxCapacity;
    }
    /**
     * Constructs a new Equipment object with default values for its fields.
     */
    public Equipment() {
    }
    /**
     * Returns the ID of the equipment.
     * @return an int representing the unique ID of the equipment
     */
    public int getEquipmentID() {
        return equipmentID;
    }
    /**
     * Sets the ID of the equipment.
     * @param equipmentID an int representing the unique ID of the equipment
     */
    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }
    /**
     * Returns the name of the equipment.
     * @return a String representing the name of the equipment
     */
    public String getEquipmentName() {
        return equipmentName;
    }
    /**
     * Sets the name of the equipment.
     * @param equipmentName a String representing the name of the equipment
     */
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
    /**
     * Returns the maximum capacity of the equipment.
     * @return an int representing the maximum capacity of the equipment
     */
    public int getEquipmentMaxCapacity() {
        return equipmentMaxCapacity;
    }
    /**
     * Sets the maximum capacity of the equipment.
     * @param equipmentMaxCapacity an int representing the maximum capacity of the equipment
     */
    public void setEquipmentMaxCapacity(int equipmentMaxCapacity) {
        this.equipmentMaxCapacity = equipmentMaxCapacity;
    }
}
