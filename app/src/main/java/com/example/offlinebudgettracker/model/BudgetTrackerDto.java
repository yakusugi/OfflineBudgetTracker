package com.example.offlinebudgettracker.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(tableName = "budget_tracker_table")
public class BudgetTrackerDto {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "storeName")
    private String storeName;

    @ColumnInfo(name = "productName")
    private String productName;

    @ColumnInfo(name = "productType")
    private String productType;

    @ColumnInfo(name = "price")
    private int price;

    public BudgetTrackerDto() {
    }

    public BudgetTrackerDto(@NonNull String date, String storeName, String productName, String productType, int price) {
        this.id = id;
        this.date = date;
        this.storeName = storeName;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    public int getPrice() {
        return price;
    }
}
