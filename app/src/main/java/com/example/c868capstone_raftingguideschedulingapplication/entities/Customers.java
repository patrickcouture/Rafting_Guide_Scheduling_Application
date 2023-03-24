package com.example.c868capstone_raftingguideschedulingapplication.entities;


import android.provider.ContactsContract;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customers")
public class Customers {
    @PrimaryKey(autoGenerate = true)
    private int customerID;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private int customerGroupTotal;

    /**
     * Constructs a customer object with the specified ID, name, email, phone number and group size.
     * @param customerID the unique ID of the customer.
     * @param customerName the name of the customer.
     * @param customerEmail the email of the customer.
     * @param customerPhone the phone number of the customer.
     * @param customerGroupTotal the group size of the customer.
     */
    public Customers(int customerID, String customerName, String customerEmail, String customerPhone, int customerGroupTotal) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerGroupTotal = customerGroupTotal;
    }

    /**
     * Constructs a customer object with no arguments.
     */
    public Customers() {
    }
    /**
     * Returns the unique ID of the customer.
     * @return the unique ID of the customer.
     */
    public int getCustomerID() {
        return customerID;
    }
    /**
     * Sets the unique ID of the customer.
     * @param customerID the unique ID of the customer.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    /**
     * Returns the name of the customer.
     * @return the name of the customer.
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * Sets the name of the customer.
     * @param customerName the name of the customer.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     * Returns the email of the customer.
     * @return the email of the customer.
     */
    public String getCustomerEmail() {
        return customerEmail;
    }
    /**
     * Sets the email of the customer.
     * @param customerEmail the email of the customer.
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    /**
     * Returns the phone number of the customer.
     * @return the phone number of the customer.
     */
    public String getCustomerPhone() {
        return customerPhone;
    }
    /**
     * Sets the phone number of the customer.
     * @param customerPhone the phone number of the customer.
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    /**
     * Returns the group size of the customer.
     * @return the group size of the customer.
     */
    public int getCustomerGroupTotal() {
        return customerGroupTotal;
    }
    /**
     * Sets the group size of the customer.
     * @param customerGroupTotal the group size of the customer.
     */
    public void setCustomerGroupTotal(int customerGroupTotal) {
        this.customerGroupTotal = customerGroupTotal;
    }
}
