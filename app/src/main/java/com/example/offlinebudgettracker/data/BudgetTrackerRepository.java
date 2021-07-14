package com.example.offlinebudgettracker.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.offlinebudgettracker.model.BudgetTrackerDto;
import com.example.offlinebudgettracker.util.BudgetTrackerRoomDatabase;

import java.util.List;

public class BudgetTrackerRepository {
    private BudgetTrackerDao budgetTrackerDao;
    private LiveData<List<BudgetTrackerDto>> allBudgetInfoContent;

    public BudgetTrackerRepository(Application application) {
        BudgetTrackerRoomDatabase db = BudgetTrackerRoomDatabase.getDatabase(application);
            budgetTrackerDao = db.budgetTrackerDao();

            allBudgetInfoContent = budgetTrackerDao.getAllBudgetTrackerInfo();


    }
    public LiveData<List<BudgetTrackerDto>> getAllBudgetData() {return allBudgetInfoContent;}
    public void insert(BudgetTrackerDto budgetTrackerDto) {
        BudgetTrackerRoomDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerDao.insert(budgetTrackerDto);
        });
    }
}
