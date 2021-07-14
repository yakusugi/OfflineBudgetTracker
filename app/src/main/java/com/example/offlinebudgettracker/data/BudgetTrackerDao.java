package com.example.offlinebudgettracker.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.offlinebudgettracker.model.BudgetTrackerDto;

import java.util.List;

@Dao
public interface BudgetTrackerDao {
    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BudgetTrackerDto budgetTrackerDto);

    @Query("DELETE FROM budget_tracker_table")
    void deleteAll();

    @Query("SELECT * FROM budget_tracker_table ORDER BY date ASC")
    LiveData<List<BudgetTrackerDto>> getAllBudgetTrackerInfo();
}
