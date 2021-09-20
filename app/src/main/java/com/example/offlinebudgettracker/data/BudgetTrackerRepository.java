package com.example.offlinebudgettracker.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.offlinebudgettracker.model.BudgetTrackerDto;
import com.example.offlinebudgettracker.util.BudgetTrackerRoomDatabase;

import java.util.List;

public class BudgetTrackerRepository {
    private BudgetTrackerDao budgetTrackerDao;
    private LiveData<List<BudgetTrackerDto>> allBudgetInfoContent;
    private LiveData<List<BudgetTrackerDto>> storeNameBudgetInfoContent;
    String query = null;

    public BudgetTrackerRepository(Application application) {
        BudgetTrackerRoomDatabase db = BudgetTrackerRoomDatabase.getDatabase(application);
            budgetTrackerDao = db.budgetTrackerDao();

            allBudgetInfoContent = budgetTrackerDao.getAllBudgetTrackerInfo();
            storeNameBudgetInfoContent = budgetTrackerDao.getAllBudgetTrackerStoreName(query);
    }

    public LiveData<List<BudgetTrackerDto>> getAllBudgetData() {return allBudgetInfoContent;}

    public LiveData<List<BudgetTrackerDto>> getStoreNameBudgetData(String storeName) {
        return this.budgetTrackerDao.getAllBudgetTrackerStoreName(storeName);
    }

    public void insert(BudgetTrackerDto budgetTrackerDto) {
        BudgetTrackerRoomDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerDao.insert(budgetTrackerDto);
        });
    }

    public LiveData<BudgetTrackerDto> get(int id) {
        return budgetTrackerDao.get(id);
    }

    public void update(BudgetTrackerDto budgetTrackerDto) {
        BudgetTrackerRoomDatabase.dataWritableExecutor.execute(() -> budgetTrackerDao.update(budgetTrackerDto));
    }

    public void delete(BudgetTrackerDto budgetTrackerDto) {
        BudgetTrackerRoomDatabase.dataWritableExecutor.execute(() -> budgetTrackerDao.delete(budgetTrackerDto));
    }
}
