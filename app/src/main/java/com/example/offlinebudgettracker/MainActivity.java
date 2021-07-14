package com.example.offlinebudgettracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.offlinebudgettracker.model.BudgetTrackerDto;
import com.example.offlinebudgettracker.model.BudgetTrackerViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BudgetTrackerViewModel budgetTrackerViewModel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textSample);

        budgetTrackerViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(BudgetTrackerViewModel.class);

        budgetTrackerViewModel.getAllBudgetTrackingContents().observe(this, budgetTrackerDtos -> {
            StringBuilder builder = new StringBuilder();
            for (BudgetTrackerDto budgetTrackerDto : budgetTrackerDtos) {
                builder.append(" - ").append(budgetTrackerDto.getDate()).append(" ").append(budgetTrackerDto.getProductName());
                Log.d("TAG", "onCreate: " + budgetTrackerDto.getProductName());
            }
            textView.setText(builder.toString());

        });
    }
}