package com.example.offlinebudgettracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.offlinebudgettracker.adapter.RecycleViewAdapter;
import com.example.offlinebudgettracker.model.BudgetTrackerDto;
import com.example.offlinebudgettracker.model.BudgetTrackerViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements RecycleViewAdapter.OnBudgetTrackerClickListener {

    private static final int NEW_BUDGET_ENTRY_ACTIVITY_REQUEST_CODE = 1;
    private static final String TAG = "Clicked";
    public static final String BUDGET_TRACKER_ID = "budget_tracker_id";
    private BudgetTrackerViewModel budgetTrackerViewModel;
    private TextView textView;
    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private LiveData<List<BudgetTrackerDto>> budgetTrackerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        budgetTrackerViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(BudgetTrackerViewModel.class);

        budgetTrackerViewModel.getAllBudgetTrackingContents().observe(this, budgetTrackerDtos -> {
            recycleViewAdapter = new RecycleViewAdapter(budgetTrackerDtos, MainActivity.this, this);
            recyclerView.setAdapter(recycleViewAdapter);


        });

        FloatingActionButton fab = findViewById(R.id.add_budget_info_fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewBudgetEntry.class);
            startActivityForResult(intent, NEW_BUDGET_ENTRY_ACTIVITY_REQUEST_CODE);
        });

        Button storeNameQueryBtn = findViewById(R.id.storeNameQueryButton);
        storeNameQueryBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StoreNameQuery.class);
            startActivityForResult(intent, NEW_BUDGET_ENTRY_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_BUDGET_ENTRY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            assert data != null;
            String date = data.getStringExtra(NewBudgetEntry.DATE_REPLY);
            String storeName = data.getStringExtra(NewBudgetEntry.STORE_NAME_REPLY);
            String productName = data.getStringExtra(NewBudgetEntry.PRODUCT_NAME_REPLY);
            String productType = data.getStringExtra(NewBudgetEntry.PRODUCT_TYPE_REPLY);
            int price = Integer.parseInt(data.getStringExtra(NewBudgetEntry.PRICE_REPLY));

            assert date != null;
            assert storeName != null;
            assert productName != null;
            assert productType != null;
            BudgetTrackerDto budgetTrackerDto = new BudgetTrackerDto(date, storeName, productName, productType, price);

            BudgetTrackerViewModel.insert(budgetTrackerDto);

        }
    }

    @Override
    public void onBudgetTrackerClick(int position) {
        BudgetTrackerDto budgetTrackerDto = Objects.requireNonNull(budgetTrackerViewModel.allBudgetInfoContents.getValue().get(position));
        Log.d(TAG, "onBudgetTrackerClick: " + position + budgetTrackerDto.getId());
        Intent intent = new Intent(MainActivity.this, NewBudgetEntry.class);
        intent.putExtra(BUDGET_TRACKER_ID, budgetTrackerDto.getId());
        startActivity(intent);
    }
}