package com.example.c868capstone_raftingguideschedulingapplication.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 This class represents a User entity, which contains information about users of the application.
 */
@Entity(tableName = "users")
public class Users {

    @PrimaryKey(autoGenerate = true)
    private int userID;
    private String username;
    private String password;
    /**
     * Constructor for the Users class that initializes a new instance of the class with the given parameters.
     * @param userID the user ID.
     * @param username the username of the user.
     * @param password the password of the user.
     */
    public Users(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }
    /**
     * Default constructor for the Users class.
     */
    public Users() {
    }
    /**
     * Returns the ID of the user.
     * @return the user ID.
     */
    public int getUserID() {
        return userID;
    }
    /**
     * Sets the ID of the user.
     * @param userID the user ID to set.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    /**
     * Returns the username of the user.
     * @return the username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username of the user.
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Returns the password of the user.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password of the user.
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
