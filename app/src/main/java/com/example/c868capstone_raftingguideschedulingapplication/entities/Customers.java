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

    public Customers(int customerID, String customerName, String customerEmail, String customerPhone, int customerGroupTotal) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerGroupTotal = customerGroupTotal;
    }

    public Customers() {
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getCustomerGroupTotal() {
        return customerGroupTotal;
    }

    public void setCustomerGroupTotal(int customerGroupTotal) {
        this.customerGroupTotal = customerGroupTotal;
    }
}
