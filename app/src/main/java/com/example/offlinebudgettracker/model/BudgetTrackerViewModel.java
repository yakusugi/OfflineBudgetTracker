package com.example.offlinebudgettracker.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.offlinebudgettracker.data.BudgetTrackerRepository;

import java.util.List;

public class BudgetTrackerViewModel extends AndroidViewModel {

    public static BudgetTrackerRepository repository;
    public final LiveData<List<BudgetTrackerDto>> allBudgetInfoContents;
    public final LiveData<List<BudgetTrackerDto>> storeNameBudgetInfoContents;
    private String storeQuery;
    private String mOwner;


    public BudgetTrackerViewModel(@NonNull Application application) {
        super(application);
        repository = new BudgetTrackerRepository(application);
        allBudgetInfoContents = repository.getAllBudgetData();

        String query = null;
        storeNameBudgetInfoContents = repository.getStoreNameBudgetData(query);
    }


    public LiveData<List<BudgetTrackerDto>> getAllBudgetTrackingContents() {return allBudgetInfoContents;}
//    public LiveData<List<BudgetTrackerDto>> getStoreNameBudgetTrackingContents(String stringOne) {return storeNameBudgetInfoContents;}
//    public void getStoreNameBudgetTrackingContents(String storeQuery) {
//        repository.getStoreNameBudgetData(storeQuery).observe( mOwner, new android.arch.lifecycle.Observer<List<BudgetTrackerDto>>() {
//            @Override
//            public void onChanged(@Nullable List<BudgetTrackerDto> myRoomEntities) {
//                if(myRoomEntities != null) {
//                    for(BudgetTrackerDto item: myRoomEntities) {
//                        Log.d("TAG ROOM ", "Input Name: " + item.toString());
//                    }
//                }
//            }
//        } );
//}
    public static void insert(BudgetTrackerDto budgetTrackerDto) {repository.insert(budgetTrackerDto);}

    public LiveData<BudgetTrackerDto> get(int id) {return repository.get(id);}
    public static void update(BudgetTrackerDto budgetTrackerDto) {repository.update(budgetTrackerDto);}
    public static void delete(BudgetTrackerDto budgetTrackerDto) {repository.delete(budgetTrackerDto);}
}
