package com.example.offlinebudgettracker.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "budget_tracker_table")
public class BudgetTrackerDto {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "date")
    private Date date;

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

    public BudgetTrackerDto(@NonNull Date date, String storeName, String productName, String productType, int price) {
        this.id = id;
        this.date = date;
        this.storeName = storeName;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
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
