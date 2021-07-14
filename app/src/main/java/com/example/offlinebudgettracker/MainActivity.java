package com.example.offlinebudgettracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.offlinebudgettracker.model.BudgetTrackerDto;
import com.example.offlinebudgettracker.model.BudgetTrackerViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BudgetTrackerViewModel budgetTrackerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        budgetTrackerViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(BudgetTrackerViewModel.class);

        budgetTrackerViewModel.getAllBudgetTrackingContents().observe(this, budgetTrackerDtos -> {
            Log.d("TAG", "onCreate: " + budgetTrackerDtos.get(0).getProductName());
        });
    }
}