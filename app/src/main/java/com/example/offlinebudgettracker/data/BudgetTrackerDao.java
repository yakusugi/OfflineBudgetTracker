package com.example.offlinebudgettracker.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM budget_tracker_table WHERE storeName = :storeQuery")
    LiveData<List<BudgetTrackerDto>> getAllBudgetTrackerStoreName(String storeQuery);

    @Query("SELECT * FROM budget_tracker_table WHERE budget_tracker_table.id == :id")
    LiveData<BudgetTrackerDto> get(int id);

    @Update
    void update(BudgetTrackerDto budgetTrackerDto);

    @Delete
    void delete(BudgetTrackerDto budgetTrackerDto);
}
