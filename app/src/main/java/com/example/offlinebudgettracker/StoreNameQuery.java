package com.example.offlinebudgettracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.offlinebudgettracker.adapter.RecycleViewAdapter;
import com.example.offlinebudgettracker.adapter.StoreQueryRecycleViewAdapter;
import com.example.offlinebudgettracker.model.BudgetTrackerDto;
import com.example.offlinebudgettracker.model.BudgetTrackerViewModel;
import com.example.offlinebudgettracker.adapter.RecycleViewAdapter;

import java.util.List;

public class StoreNameQuery extends AppCompatActivity {

    public static final String STORE_NAME_QUERY = "store_name_query";
    private BudgetTrackerViewModel budgetTrackerViewModel;
    private EditText enterStoreNameQuery;
    private Button storeNameQueryButton;
    private int budgetItemId = 0;
    private RecyclerView recyclerView;
    private StoreQueryRecycleViewAdapter storeQueryRecycleViewAdapter;
    private LiveData<List<BudgetTrackerDto>> storeQueryBudgetTrackerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_name_query);

        recyclerView = findViewById(R.id.store_query_recycle_view);

        enterStoreNameQuery = findViewById(R.id.storeNameQueryEntry);

        budgetTrackerViewModel = new ViewModelProvider.AndroidViewModelFactory(StoreNameQuery.this
        .getApplication())
        .create(BudgetTrackerViewModel.class);

        storeNameQueryButton = findViewById(R.id.store_name_query_button);
        storeNameQueryButton.setOnClickListener(v -> {

        });

//        budgetTrackerViewModel.get(budgetItemId).observe(this, budgetTrackerDtos -> {
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            storeQueryRecycleViewAdapter = new StoreQueryRecycleViewAdapter((List<BudgetTrackerDto>) budgetTrackerDtos, StoreNameQuery.this);
//            recyclerView.setAdapter(storeQueryRecycleViewAdapter);
//
//        });

    }
}