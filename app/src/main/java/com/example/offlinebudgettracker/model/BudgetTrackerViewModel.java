package com.example.offlinebudgettracker.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.offlinebudgettracker.data.BudgetTrackerRepository;

import java.util.List;

public class BudgetTrackerViewModel extends AndroidViewModel {

    public static BudgetTrackerRepository repository;
    public final LiveData<List<BudgetTrackerDto>> allBudgetInfoContents;

    public BudgetTrackerViewModel(@NonNull Application application) {
        super(application);
        repository = new BudgetTrackerRepository(application);
        allBudgetInfoContents = repository.getAllBudgetData();
    }

    public LiveData<List<BudgetTrackerDto>> getAllBudgetTrackingContents() {return allBudgetInfoContents;}
    public static void insert(BudgetTrackerDto budgetTrackerDto) {repository.insert(budgetTrackerDto);}

    public LiveData<BudgetTrackerDto> get(int id) {return repository.get(id);}
    public static void update(BudgetTrackerDto budgetTrackerDto) {repository.update(budgetTrackerDto);}
    public static void delete(BudgetTrackerDto budgetTrackerDto) {repository.delete(budgetTrackerDto);}
}
