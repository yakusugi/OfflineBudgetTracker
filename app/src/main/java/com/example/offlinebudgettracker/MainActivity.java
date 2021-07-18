package com.example.offlinebudgettracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.offlinebudgettracker.model.BudgetTrackerDto;
import com.example.offlinebudgettracker.model.BudgetTrackerViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_BUDGET_ENTRY_ACTIVITY_REQUEST_CODE = 1;
    private BudgetTrackerViewModel budgetTrackerViewModel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        budgetTrackerViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(BudgetTrackerViewModel.class);

        budgetTrackerViewModel.getAllBudgetTrackingContents().observe(this, budgetTrackerDtos -> {
            StringBuilder builder = new StringBuilder();
            for (BudgetTrackerDto budgetTrackerDto : budgetTrackerDtos) {
                builder.append(" - ").append(budgetTrackerDto.getDate()).append(" ").append(budgetTrackerDto.getProductName());
                Log.d("TAG", "onCreate: " + budgetTrackerDto.getProductName());
            }

        });

        FloatingActionButton fab = findViewById(R.id.add_budget_info_fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewBudgetEntry.class);
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
}